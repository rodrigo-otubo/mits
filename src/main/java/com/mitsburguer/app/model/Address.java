package com.mitsburguer.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Field("street")
    private String street;
    @Field("number")
    private String number;
    @Field("postal_code")
    private String postalCode;
    @Field("city")
    private String city;
    @Field("state")
    private String state;
}
