package de.sebastian.wien.model;

import lombok.Data;

@Data
public class UserPost extends BaseModel {

    private long userId;

    private String title, body;
}
