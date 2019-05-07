package com.univision.assessmentwebapi.service;

import com.univision.assessmentwebapi.dto.FeedSummaryResponseDTO;

public interface FeedService {

    FeedSummaryResponseDTO getFeedSummary(final String feedURL);

}
