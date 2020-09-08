package com.mitsburguer.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Client {
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("phone")
    private String phone;
    @Field("cellphone")
    private String cellPhone;
    @Field("address")
    private Address address;
}
