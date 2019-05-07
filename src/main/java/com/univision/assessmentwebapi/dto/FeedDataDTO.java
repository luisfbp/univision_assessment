package com.univision.assessmentwebapi.dto;

import java.util.List;

public class FeedDataDTO {


    private List<WidgetDTO> widgets;

    public FeedDataDTO() {}

    public FeedDataDTO (List<WidgetDTO> widgets) {
        this.widgets = widgets;
    }

    public List<WidgetDTO> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<WidgetDTO> widgets) {
        this.widgets = widgets;
    }

}
