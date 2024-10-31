package com.jnlim.tennis.entity;

import com.jnlim.api.seoul.dto.RowDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.jnlim.util.DateTimeUtils.parseToLocalDateTime;

@Getter
@Builder
public class Tennis {
    private Long id;
    private String region;
    private String serviceId;
    private String serviceStatus;
    private String serviceName;
    private String payAt;
    private String useTarget;
    private String serviceUrl;
    private double longitude;
    private double latitude;
    private LocalDateTime receiptBeginDateTime;
    private LocalDateTime receiptEndDateTime;
    private String areaName;
    private String imageUrl;
    private String detailContent;
    private String telNo;
    private String serviceBeginTime;
    private String serviceEndTime;
    private String cancelStandard;
    private String cancelStandardDay;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Tennis of(RowDTO rowDto) {
        return Tennis.builder()
                .region("SEOUL")
                .serviceId(rowDto.getSvcId())
                .serviceStatus(rowDto.getSvcStatNm())
                .serviceName(rowDto.getSvcNm())
                .payAt(rowDto.getPayAtNm())
                .useTarget(rowDto.getUseTgtInfo())
                .serviceUrl(rowDto.getSvcUrl())
                .longitude(Double.parseDouble(rowDto.getX()))
                .latitude(Double.parseDouble(rowDto.getY()))
                .receiptBeginDateTime(parseToLocalDateTime(rowDto.getRcptBgnDt()))
                .receiptEndDateTime(parseToLocalDateTime(rowDto.getRcptEndDt()))
                .areaName(rowDto.getAreaNm())
                .imageUrl(rowDto.getImgUrl())
                .detailContent(rowDto.getDtlCont())
                .telNo(rowDto.getTelNo())
                .serviceBeginTime(rowDto.getVMin())
                .serviceEndTime(rowDto.getVMax())
                .cancelStandard(rowDto.getRevStdDayNm())
                .cancelStandardDay(rowDto.getRevStdDay())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
