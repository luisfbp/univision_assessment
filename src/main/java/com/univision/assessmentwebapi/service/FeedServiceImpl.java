package com.univision.assessmentwebapi.service;

import com.univision.assessmentwebapi.client.FeedRESTClient;
import com.univision.assessmentwebapi.dto.*;
import com.univision.assessmentwebapi.mapper.FeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedRESTClient feedClient;

    /**
     * Creates a summary of the provided feed
     * @param feedURL feed URL
     * @return DTO object with the feed organized
     */
    public FeedSummaryResponseDTO getFeedSummary(final String feedURL) {

        final FeedSummaryResponseDTO feedSummary = new FeedSummaryResponseDTO();

        Map<String, List<ContentDTO>> orderedFeed = groupFeedsByType(feedClient.getFeeds(feedURL).getData());
        List<FeedSummaryDTO> feedsSummary = FeedMapper.contentDTOMapToFeedSummaryDTOList(orderedFeed);
        feedSummary.setSummary(feedsSummary);

        return feedSummary;

    }

    /**
     * groups all the feeds by type in a single map
     * @param feedData Feed data
     * @return map with a type as a key
     */
    private Map<String, List<ContentDTO>> groupFeedsByType(final FeedDataDTO feedData) {

        // Flats the lists to one from every widgets list
        List<ContentDTO> contents = feedData.getWidgets().stream()
                // Filter the lists that are empty
                .filter(widget -> !CollectionUtils.isEmpty(widget.getContents()))
                .map(WidgetDTO::getContents)
                .flatMap(List::stream).collect(Collectors.toList());

        return contents.stream().collect(Collectors.groupingBy(ContentDTO::getType));

    }

}
