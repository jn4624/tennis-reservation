package com.jnlim.init;

import com.jnlim.api.seoul.dto.RowDTO;
import com.jnlim.api.seoul.service.SeoulTennisSearchService;
import com.jnlim.tennis.repository.TennisMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.jnlim.tennis.entity.Tennis.ofSave;

@Slf4j
@Component
public class InitHandler {
    private static final String START_INDEX = "1";
    private static final String MIN_CLASS_NM = "테니스장";

    private final SeoulTennisSearchService seoulTennisSearchService;
    private final TennisMapper tennisMapper;

    public InitHandler(SeoulTennisSearchService seoulTennisSearchService, TennisMapper tennisMapper) {
        this.seoulTennisSearchService = seoulTennisSearchService;
        this.tennisMapper = tennisMapper;
    }

    @PostConstruct
    public void init() {
        tennisMapper.deleteAll();

        log.info("[InitHandler init] deleted");

        List<RowDTO> rowList = seoulTennisSearchService.requestSeoulTennisSearch(START_INDEX, MIN_CLASS_NM);

        log.info("[InitHandler init] seoul tennis reservation row list size: {}", rowList.size());

        for (RowDTO rowDto : rowList) {
            tennisMapper.save(ofSave(rowDto));
        }
    }
}
