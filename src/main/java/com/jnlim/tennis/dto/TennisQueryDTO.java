package com.jnlim.tennis.dto;

public class TennisQueryDTO {
    private int page;
    private int size;
    private Long id;

    public TennisQueryDTO(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public TennisQueryDTO(Long id) {
        this.id = id;
    }
}
