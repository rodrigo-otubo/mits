package com.mitsburguer.app.repository;

import com.mitsburguer.app.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {

    ArrayList<Client> findByName(String name);
    Optional<Client> findByCellPhone(String cellPhone);
}
