package com.univision.univisionTest.service;

import com.univision.univisionTest.client.FeedRESTClient;
import com.univision.univisionTest.dto.*;
import com.univision.univisionTest.mapper.FeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedRESTClient feedClient;

    public FeedSummaryResponseDTO getFeedSummary() {

        final FeedSummaryResponseDTO feedSummary = new FeedSummaryResponseDTO();
        List<FeedSummaryDTO> feedsSummary = FeedMapper.contentDTOMapToFeedSummaryDTO(sortFeeds(feedClient.getFeeds().getData()));
        feedSummary.setSummary(feedsSummary);

        return feedSummary;

    }

    private Map<String, List<ContentDTO>> sortFeeds(final FeedDataDTO feedData) {

        List<ContentDTO> contents = feedData.getWidgets().stream()
                .filter(widget -> !CollectionUtils.isEmpty(widget.getContents()))
                .map(WidgetDTO::getContents)
                .flatMap(List::stream).collect(Collectors.toList());

        return contents.stream().collect(Collectors.groupingBy(ContentDTO::getType));

    }




}
