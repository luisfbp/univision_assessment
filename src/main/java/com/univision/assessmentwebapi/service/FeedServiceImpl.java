package com.univision.assessmentwebapi.service;

import com.univision.assessmentwebapi.client.GenericRESTClient;
import com.univision.assessmentwebapi.dto.*;
import com.univision.assessmentwebapi.exception.APIThrowable;
import com.univision.assessmentwebapi.mapper.FeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private GenericRESTClient genericClient;

    /**
     * Creates a summary of the provided feed
     * @param feedURL feed URL
     * @return DTO object with the feed organized
     */
    public FeedSummaryResponseDTO getFeedSummary(final String feedURL) {

        final FeedSummaryResponseDTO feedSummary = new FeedSummaryResponseDTO();

        FeedResponseDTO feedResponse = genericClient.getRequest(feedURL, FeedResponseDTO.class);
        if (Objects.isNull(feedResponse) || Objects.isNull(feedResponse.getData())) {
            throw new APIThrowable(HttpStatus.NOT_FOUND, "No data returned with given feed URL");
        }

        Map<String, List<ContentDTO>> orderedFeed = groupFeedsByType(feedResponse.getData());

        feedSummary.setSummary(FeedMapper.contentDTOMapToFeedSummaryDTOList(orderedFeed));

        return feedSummary;

    }

    /**
     * groups all the feeds by type in a single map
     * @param feedData Feed data
     * @return map with a type as a key
     */
    private Map<String, List<ContentDTO>> groupFeedsByType(final FeedDataDTO feedData) {

        if (Objects.nonNull(feedData) && !CollectionUtils.isEmpty(feedData.getWidgets())) {
            // Flats the lists to one from every widgets list
            List<ContentDTO> contents = feedData.getWidgets().stream()
                    // Filter the lists that are empty
                    .filter(widget -> !CollectionUtils.isEmpty(widget.getContents()))
                    .map(WidgetDTO::getContents)
                    .flatMap(List::stream).collect(Collectors.toList());

            return contents.stream().collect(Collectors.groupingBy(ContentDTO::getType));
        }
        return Collections.emptyMap();
    }

}
