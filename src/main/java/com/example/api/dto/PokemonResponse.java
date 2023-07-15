package com.example.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {

    private List<PokemonDto> content;
    private int PageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
