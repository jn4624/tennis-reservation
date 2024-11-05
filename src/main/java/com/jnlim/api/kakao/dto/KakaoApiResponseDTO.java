package com.jnlim.api.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@ToString
public class KakaoApiResponseDTO {
    @JsonProperty("documents")
    private List<DocumentDTO> documentList = new ArrayList<>();

    @JsonProperty("meta")
    private MetaDTO metaDto;

    public Optional<DocumentDTO> getFirstDocumentDto() {
        return documentList.isEmpty() ? Optional.empty() : Optional.of(documentList.get(0));
    }
}
