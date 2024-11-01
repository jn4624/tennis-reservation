package com.jnlim.tennis.dto;

import lombok.Getter;

@Getter
public class TennisDTO {
    private Long id;
    private String region;
    private String serviceId;
    private String serviceStatus;
    private String serviceName;
    private String payAt;
    private String placeName;
    private String useTarget;
    private String serviceUrl;
    private double longitude;
    private double latitude;
    private String receiptBeginDateTime;
    private String receiptEndDateTime;
    private String areaName;
    private String imageUrl;
    private String detailContent;
    private String telNo;
    private String serviceBeginTime;
    private String serviceEndTime;
    private String cancelStandard;
    private String cancelStandardDay;
}
