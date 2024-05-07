package de.sebastian.wien.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "service_user")
public class User extends BaseModel{

    private String name, email, username, phone, website;

    @Data
    public class Company {

        private String name, catchPhrase, bs;
    }

}

/**
 *
 *
 * {
 * "id": 1,
 * "name": "Leanne Graham",
 * "username": "Bret",
 * "email": "Sincere@april.biz",
 * "address": {
 * "street": "Kulas Light",
 * "suite": "Apt. 556",
 * "city": "Gwenborough",
 * "zipcode": "92998-3874",
 * "geo": {
 * "lat": "-37.3159",
 * "lng": "81.1496"
 * }
 * },
 * "phone": "1-770-736-8031 x56442",
 * "website": "hildegard.org",
 * "company": {
 * "name": "Romaguera-Crona",
 * "catchPhrase": "Multi-layered client-server neural-net",
 * "bs": "harness real-time e-markets"
 * }
 * }
 *
 *
 *
 */
