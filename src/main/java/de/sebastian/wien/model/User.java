package de.sebastian.wien.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class User extends BaseModel {

    private String name, email, username, phone, website;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    private List<UserPost> posts;

    @OneToOne
    private Company company;

}