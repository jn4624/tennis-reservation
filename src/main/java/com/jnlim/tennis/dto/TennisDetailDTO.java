package com.jnlim.tennis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TennisDetailDTO {
    private Long id;
    private String serviceStatus;
    private String serviceName;
    private String payAt;
    private String useTarget;
    private String serviceUrl;
    private double longitude;
    private double latitude;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiptBeginDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiptEndDateTime;

    private String areaName;
    private String imageUrl;
    private String detailContent;
    private String telNo;
    private String serviceBeginTime;
    private String serviceEndTime;
    private String cancelStandard;
    private String cancelStandardDay;
}
