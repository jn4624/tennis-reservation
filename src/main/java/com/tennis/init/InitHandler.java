package com.tennis.init;

import com.tennis.api.repository.TennisMapper;
import com.tennis.http.seoul.SeoulHttpClient;
import com.tennis.http.seoul.dto.RowDTO;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class InitHandler {
    private static final String START_INDEX = "1";
    private static final String MIN_CLASS_NM = "테니스장";

    private final SeoulHttpClient seoulHttpClient;
    private final TennisMapper schedulerMapper;

    public InitHandler(SeoulHttpClient seoulHttpClient, TennisMapper tennisMapper) {
        this.seoulHttpClient = seoulHttpClient;
        this.schedulerMapper = tennisMapper;
    }

    @PostConstruct
    public void init() {
        schedulerMapper.deleteAll();

        log.info("[InitHandler init] deleted");

        List<RowDTO> rowList = seoulHttpClient
                .requestSeoulTennisSearch(START_INDEX, MIN_CLASS_NM);

        log.info("[InitHandler init] seoul tennis reservation row list size: {}", rowList.size());

        for (RowDTO rowDto : rowList) {
            rowDto.setRegion("SEOUL");
            schedulerMapper.save(rowDto);
        }
    }
}
