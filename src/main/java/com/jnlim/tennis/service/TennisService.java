package com.jnlim.tennis.service;

import com.jnlim.tennis.dto.TennisDTO;

import java.util.List;

public interface TennisService {
    List<TennisDTO> getTennisList(int page, int size);

    TennisDTO getTennis(Long id);

    String getTennisLocationURL(Long id);
}
