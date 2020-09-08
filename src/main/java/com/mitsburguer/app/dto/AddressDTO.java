package com.mitsburguer.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @JsonProperty("street")
    private String street;
    @JsonProperty("number")
    private String number;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
}
