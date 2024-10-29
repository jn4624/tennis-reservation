package com.tennis.api.repository;

import com.tennis.http.seoul.dto.RowDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TennisMapper {
    void deleteAll();

    void save(RowDTO rowDto);

    void update(RowDTO rowDto);
}
