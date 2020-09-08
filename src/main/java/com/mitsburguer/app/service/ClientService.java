package com.mitsburguer.app.service;

import com.mitsburguer.app.dto.AddressDTO;
import com.mitsburguer.app.dto.ClientDTO;

import java.util.ArrayList;

public interface ClientService {

    ClientDTO save(ClientDTO clientDTO);

    ClientDTO findByCellPhone(String cellPhone);

    ArrayList<ClientDTO> findByName(String name);

    ArrayList<ClientDTO>  findAll();

    ClientDTO updateClient(ClientDTO clientDTO, String cellPhone);

    ClientDTO updateAddressClient(AddressDTO addressDTO, String cellPhone);

    void delete(String cellPhone);
}