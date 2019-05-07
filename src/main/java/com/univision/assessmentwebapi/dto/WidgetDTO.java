package com.univision.assessmentwebapi.dto;

import java.util.List;

public class WidgetDTO {

    private List<ContentDTO> contents;

    public WidgetDTO() {}

    public WidgetDTO(List<ContentDTO> contents) {
        this.contents = contents;
    }

    public List<ContentDTO> getContents() {
        return contents;
    }

    public void setContents(List<ContentDTO> contents) {
        this.contents = contents;
    }
}
