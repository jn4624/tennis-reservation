package com.jnlim.batch.seoul;

import com.jnlim.api.seoul.dto.RowDTO;
import com.jnlim.api.seoul.service.SeoulTennisSearchService;
import com.jnlim.tennis.dto.TennisDTO;
import com.jnlim.tennis.repository.TennisMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jnlim.tennis.entity.Tennis.ofSave;
import static com.jnlim.tennis.entity.Tennis.ofUpdate;

@Slf4j
@Component
public class SeoulTennisDataSyncTask implements Runnable {
    private final static String START_INDEX = "1";
    private final static String MIN_CLASS_NM = "테니스장";

    private final SeoulTennisSearchService seoulTennisSearchService;
    private final TennisMapper tennisMapper;

    public SeoulTennisDataSyncTask(SeoulTennisSearchService seoulTennisSearchService, TennisMapper tennisMapper) {
        this.seoulTennisSearchService = seoulTennisSearchService;
        this.tennisMapper = tennisMapper;
    }

    @Transactional
    @Override
    public void run() {
        // 신규 데이터 취득
        List<RowDTO> newRowList = seoulTennisSearchService
                .requestSeoulTennisSearch(START_INDEX, MIN_CLASS_NM);
        Map<String, RowDTO> newRowMap = newRowList.stream()
                .collect(Collectors.toMap(RowDTO::getSvcId, rowDto -> rowDto));

        log.info("[SeoulTennisDataSyncTask run] newRowList: {}", newRowList.size());

        // 기존 데이터 취득
        List<TennisDTO> existingDataList = tennisMapper.findAll();

        log.info("[SeoulTennisDataSyncTask run] existingDataList: {}", existingDataList.size());

        // 데이터 비교 및 동기화
        List<String> toDelete = new ArrayList<>();
        List<RowDTO> toUpdate = new ArrayList<>();

        for (TennisDTO tennisDto : existingDataList) {
            if (!newRowMap.containsKey(tennisDto.getServiceId())) {
                // 삭제된 항목 처리
                toDelete.add(tennisDto.getServiceId());
            } else {
                // 수정된 항목 처리
                toUpdate.add(newRowMap.get(tennisDto.getServiceId()));
            }
            // 처리된 항목 처리
            newRowMap.remove(tennisDto.getServiceId());
        }

        // 추가된 항목 처리
        List<RowDTO> toInsert = new ArrayList<>(newRowMap.values());

        // 데이터베이스 동기화
        if (!toDelete.isEmpty()) {
            for (String serviceId : toDelete) {
                tennisMapper.deleteByServiceId(serviceId);
            }
        }
        if (!toUpdate.isEmpty()) {
            for (RowDTO rowDto : toUpdate) {
                tennisMapper.update(ofUpdate(rowDto));
            }
        }
        if (!toInsert.isEmpty()) {
            for (RowDTO rowDto : toInsert) {
                tennisMapper.save(ofSave(rowDto));
            }
        }

        log.info("[SeoulTennisDataSyncTask run] tennis data sync complete.");
        log.info("[SeoulTennisDataSyncTask run] inserted: {}, updated: {}, deleted: {}",
                toInsert.size(), toUpdate.size(), toDelete.size());
    }
}
