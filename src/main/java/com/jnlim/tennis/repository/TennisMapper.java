package com.jnlim.tennis.repository;

import com.jnlim.tennis.dto.TennisDTO;
import com.jnlim.tennis.dto.TennisDetailDTO;
import com.jnlim.tennis.dto.TennisQueryDTO;
import com.jnlim.tennis.entity.Tennis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TennisMapper {
    void save(Tennis tennis);

    void update(Tennis tennis);

    void deleteAll();

    List<TennisDTO> findAllByOrderByIdAsc(TennisQueryDTO tennisQueryDto);

    TennisDetailDTO findById(TennisQueryDTO tennisQueryDto);
}
