package de.sebastian.wien.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Geo extends BaseModel {

    private String lat, lon;

}
