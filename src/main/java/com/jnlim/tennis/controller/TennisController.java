package com.jnlim.tennis.controller;

import com.jnlim.common.response.ApiResponse;
import com.jnlim.tennis.dto.TennisDTO;
import com.jnlim.tennis.service.TennisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jnlim.common.response.ApiResponse.success;

@Slf4j
@RestController
@RequestMapping("tennis")
public class TennisController {
    private final TennisService tennisService;

    public TennisController(TennisService tennisService) {
        this.tennisService = tennisService;
    }

    @GetMapping
    public ApiResponse<List<TennisDTO>> getTennisList(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int length) {
        List<TennisDTO> tennisList = tennisService.getTennisList(page, length);
        System.out.println("tennisList = " + tennisList);
        return success(tennisList);
    }

    @GetMapping("/{id}")
    public ApiResponse<TennisDTO> getTennis(@PathVariable(name = "id") Long id) {
        return success(tennisService.getTennis(id));
    }
}
