package com.jnlim.tennis.repository;

import com.jnlim.tennis.entity.Tennis;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TennisMapper {
    void save(Tennis tennis);

    void update(Tennis tennis);

    void deleteAll();
}
