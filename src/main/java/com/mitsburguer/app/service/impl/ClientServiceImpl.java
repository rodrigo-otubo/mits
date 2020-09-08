package com.mitsburguer.app.service.impl;

import com.mitsburguer.app.dto.AddressDTO;
import com.mitsburguer.app.dto.ClientDTO;
import com.mitsburguer.app.error.exception.ClientExistsException;
import com.mitsburguer.app.error.exception.ClientNotFoundException;
import com.mitsburguer.app.model.Client;
import com.mitsburguer.app.repository.ClientRepository;
import com.mitsburguer.app.service.ClientService;
import com.mitsburguer.app.util.ClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientUtil clientUtil;

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        checkClientExists(clientDTO.getCellPhone());

        return clientUtil.convertToClientDTO(clientRepository.save(clientUtil.convertToClient(clientDTO)));
    }

    @Override
    public ClientDTO findByCellPhone(String cellPhone) {
        return clientUtil.convertToClientDTO(getClientByCellPhone(cellPhone));
    }

    @Override
    public ArrayList<ClientDTO> findByName(String name) {
        return clientUtil.convertToListClientDTO(clientRepository.findByName(name));
    }

    @Override
    public ArrayList<ClientDTO> findAll() {
        return clientUtil.convertToListClientDTO(clientRepository.findAll());
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO, String cellPhone) {
        Client client = getClientByCellPhone(cellPhone);
        if (!client.getCellPhone().equals(clientDTO.getCellPhone())){
            checkClientExists(clientDTO.getCellPhone());
        }
        BeanUtils.copyProperties(clientDTO, client, "id");

        return clientUtil.convertToClientDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateAddressClient(AddressDTO addressDTO, String cellPhone) {
        Client client = getClientByCellPhone(cellPhone);
        BeanUtils.copyProperties(addressDTO, client.getAddress());

        return clientUtil.convertToClientDTO(clientRepository.save(client));
    }

    @Override
    public void delete(String cellPhone) {
        clientRepository.delete(getClientByCellPhone(cellPhone));
    }

    private void checkClientExists(String cellPhone){
        clientRepository.findByCellPhone(cellPhone).ifPresent(client -> {throw new ClientExistsException(); });
    }

    private Client getClientByCellPhone(String cellPhone){
        Optional<Client> client = clientRepository.findByCellPhone(cellPhone);
        if (client.isEmpty()){
            throw new ClientNotFoundException();
        }

        return client.get();
    }

}
