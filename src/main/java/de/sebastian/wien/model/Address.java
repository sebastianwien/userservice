package de.sebastian.wien.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Address extends BaseModel {

    private String street, suite, city, zipcode;

    @Data
    public class Geo {

        private String lat, lon;

    }

}