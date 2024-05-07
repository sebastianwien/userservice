package de.sebastian.wien.model;

import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class Address extends BaseModel {

    private String street, suite, city, zipcode;

    @OneToOne
    private Geo geo;

}