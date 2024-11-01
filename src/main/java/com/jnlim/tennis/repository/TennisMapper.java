package com.jnlim.tennis.repository;

import com.jnlim.tennis.dto.TennisDTO;
import com.jnlim.tennis.entity.Tennis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TennisMapper {
    void save(Tennis tennis);

    void update(Tennis tennis);

    void deleteAll();

    void deleteByServiceId(String serviceId);

    List<TennisDTO> findAll();

    List<TennisDTO> findAllByPageable(int page, int size);

    TennisDTO findById(Long id);
}
