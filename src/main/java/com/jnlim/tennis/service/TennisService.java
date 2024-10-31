package com.jnlim.tennis.service;

import com.jnlim.tennis.dto.TennisDTO;
import com.jnlim.tennis.dto.TennisDetailDTO;

import java.util.List;

public interface TennisService {
    List<TennisDTO> getTennisList(int page, int size);

    TennisDetailDTO getTennis(Long id);
}
