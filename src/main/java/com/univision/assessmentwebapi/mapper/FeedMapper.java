package com.univision.assessmentwebapi.mapper;

import com.univision.assessmentwebapi.dto.ContentDTO;
import com.univision.assessmentwebapi.dto.FeedSummaryDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class FeedMapper {

    /**
     * FeedMapper is not meant to have instances
     */
    private FeedMapper() {}

    /**
     * Maps a contentDTO map to a FeedSummaryDTO list
     * @param contentDTOMap grouped contentDTO in a map with type as a key
     * @return list of FeedSummaryDTO
     */
    public static List<FeedSummaryDTO> contentDTOMapToFeedSummaryDTOList(Map<String, List<ContentDTO>> contentDTOMap) {

        return contentDTOMap.entrySet().stream()
                .map(e -> FeedMapper.contentDTOListToFeedSummaryDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

    }

    /**
     * creates an instance of FeedSummaryDTO based on a ContentDTO
     * @param type type
     * @param contentList List of ContentDTO with the same type
     * @return new instanced of a FeedSummaryDTO
     */
    private static FeedSummaryDTO contentDTOListToFeedSummaryDTO (String type, List<ContentDTO> contentList) {

        final FeedSummaryDTO feedSummary = new FeedSummaryDTO();
        feedSummary.setType(type);
        feedSummary.setTotal(contentList.size());
        feedSummary.setTitles(contentList.stream().map(ContentDTO::getTitle).collect(Collectors.toList()));

        return feedSummary;

    }

}
