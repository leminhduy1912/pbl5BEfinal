package com.pbl5.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Promotion extends AbstractModel{
    private String image;
    private String description;
    private String title ;
    private String clause ;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }
}
