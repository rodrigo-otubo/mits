package com.mitsburguer.app.util;

import com.mitsburguer.app.dto.AddressDTO;
import com.mitsburguer.app.dto.ClientDTO;
import com.mitsburguer.app.model.Address;
import com.mitsburguer.app.model.Client;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ClientUtil {

    public Client convertToClient(ClientDTO clientDTO){
        return new Client(
                UUID.randomUUID().toString(),
                clientDTO.getName(),
                clientDTO.getPhone(),
                clientDTO.getCellPhone(),
                convertToAddress(clientDTO.getAddressDTO())
        );
    }

    public Address convertToAddress(AddressDTO addressDTO){
        return new Address(
                addressDTO.getStreet(),
                addressDTO.getNumber(),
                addressDTO.getPostalCode(),
                addressDTO.getCity(),
                addressDTO.getState()
        );
    }

    public ClientDTO convertToClientDTO(Client client){
        return new ClientDTO(
                client.getName(),
                client.getPhone(),
                client.getCellPhone(),
                convertToAddressDTO(client.getAddress())
        );
    }

    public AddressDTO convertToAddressDTO(Address address){
        return new AddressDTO(
                address.getStreet(),
                address.getNumber(),
                address.getPostalCode(),
                address.getCity(),
                address.getState()
        );
    }

    public ArrayList<ClientDTO> convertToListClientDTO(List<Client> clients){
        ArrayList<ClientDTO> clientDTOS = new ArrayList<>();
        clients.forEach(client -> {
            clientDTOS.add(new ClientDTO(
                    client.getName(),
                    client.getPhone(),
                    client.getCellPhone(),
                    new AddressDTO(
                            client.getAddress().getStreet(),
                            client.getAddress().getNumber(),
                            client.getAddress().getPostalCode(),
                            client.getAddress().getCity(),
                            client.getAddress().getState()
                    )
            ));
        });

        return clientDTOS;
    }
}
