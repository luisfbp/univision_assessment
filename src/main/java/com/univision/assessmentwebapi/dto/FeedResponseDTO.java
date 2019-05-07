package com.univision.assessmentwebapi.dto;


public class FeedResponseDTO {

    private FeedDataDTO data;

    public FeedResponseDTO() {}

    public FeedResponseDTO(FeedDataDTO data) {
        this.data = data;
    }

    public FeedDataDTO getData() {
        return data;
    }

    public void setData(FeedDataDTO data) {
        this.data = data;
    }
}
