package com.univision.univisionTest.mapper;

import com.univision.univisionTest.dto.ContentDTO;
import com.univision.univisionTest.dto.FeedSummaryDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class FeedMapper {

    /**
     * FeedMapper is not meant to have instances
     */
    private FeedMapper() {}

    public static final List<FeedSummaryDTO> contentDTOMapToFeedSummaryDTO(Map<String, List<ContentDTO>> contentDTOMap) {

        return contentDTOMap.entrySet().stream()
                .map(e -> FeedMapper.contentDTOListToFeedSummaryDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

    }

    private static FeedSummaryDTO contentDTOListToFeedSummaryDTO (String type, List<ContentDTO> contentList) {

        final FeedSummaryDTO feedSummary = new FeedSummaryDTO();
        feedSummary.setType(type);
        feedSummary.setTotal(contentList.size());
        feedSummary.setTitles(contentList.stream().map(content -> content.getTitle()).collect(Collectors.toList()));

        return feedSummary;

    }

}
