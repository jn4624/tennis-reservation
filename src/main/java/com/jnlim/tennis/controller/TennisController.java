package com.jnlim.tennis.controller;

import com.jnlim.common.response.ApiResponse;
import com.jnlim.tennis.dto.TennisDTO;
import com.jnlim.tennis.service.TennisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jnlim.common.response.ApiResponse.success;

@Slf4j
@RestController
@RequestMapping("tennis")
@Tag(name = "Response Tennis", description = "Response Tennis API")
public class TennisController {
    private final TennisService tennisService;

    public TennisController(TennisService tennisService) {
        this.tennisService = tennisService;
    }

    @GetMapping
    @Operation(summary = "테니스 예약 리스트 정보 조회", description = "테니스 예약 리스트 정보를 조회할 때 사용하는 API")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호", example = "10"),
            @Parameter(name = "length", description = "게시글 개수", example = "10")
    })
    public ApiResponse<List<TennisDTO>> getTennisList(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int length) {
        List<TennisDTO> tennisList = tennisService.getTennisList(page, length);
        return success(tennisList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "테니스 예약 단건 정보 조회", description = "테니스 예약 단건 정보를 조회할 때 사용하는 API")
    @Parameter(name = "id", description = "고유 아이디", example = "7")
    public ApiResponse<TennisDTO> getTennis(@PathVariable(name = "id") Long id) {
        return success(tennisService.getTennis(id));
    }
}
