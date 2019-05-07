package com.univision.assessmentwebapi.dto;

public class ContentDTO {

    private String type;
    private String title;

    public ContentDTO(){}

    public ContentDTO (String type, String tittle) {
        this.title = tittle;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
