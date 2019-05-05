package com.univision.univisionTest.dto;

import java.util.List;

public class FeedSummaryResponseDTO {

    private List<FeedSummaryDTO> summary;

    public List<FeedSummaryDTO> getSummary() {
        return summary;
    }

    public void setSummary(List<FeedSummaryDTO> summary) {
        this.summary = summary;
    }

}
