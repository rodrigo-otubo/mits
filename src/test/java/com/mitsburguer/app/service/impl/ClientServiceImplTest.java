package com.mitsburguer.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitsburguer.app.MockUtils;
import com.mitsburguer.app.dto.ClientDTO;
import com.mitsburguer.app.model.Address;
import com.mitsburguer.app.model.Client;
import com.mitsburguer.app.repository.ClientRepository;
import com.mitsburguer.app.service.ClientService;
import com.mitsburguer.app.util.ClientUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    private ObjectMapper objectMapper;
    @Mock
    private ClientRepository clientRepository;
    private ClientService clientService;
    @Mock
    private ClientUtil clientUtil;

    @Before
    public void setup(){
        objectMapper = new ObjectMapper();
        clientService = new ClientServiceImpl(clientRepository, clientUtil);
    }

    @Test
    public void save() throws IOException {
        ClientDTO expectedClientDTO = objectMapper.readValue(MockUtils.buildMock(ClientDTO.class, "expectedClientSaved.json"), ClientDTO.class);
        Client client = new Client(
                UUID.randomUUID().toString(),
                expectedClientDTO.getName(),
                expectedClientDTO.getPhone(),
                expectedClientDTO.getCellPhone(),
                new Address(
                        expectedClientDTO.getAddressDTO().getStreet(),
                        expectedClientDTO.getAddressDTO().getNumber(),
                        expectedClientDTO.getAddressDTO().getPostalCode(),
                        expectedClientDTO.getAddressDTO().getCity(),
                        expectedClientDTO.getAddressDTO().getState()
                )
        );

        Mockito.when(clientRepository.findByCellPhone(expectedClientDTO.getCellPhone())).thenReturn(Optional.empty());
        Mockito.when(clientUtil.convertToClient(expectedClientDTO)).thenReturn(client);
        Mockito.when(clientUtil.convertToClientDTO(client)).thenReturn(expectedClientDTO);
        Mockito.when(clientRepository.save(client)).thenReturn(client);

        ClientDTO actualClientDTO = clientService.save(expectedClientDTO);

        Assert.assertEquals(expectedClientDTO, actualClientDTO);
    }


    @Test
    public void findByCellPhone() throws IOException {
        ClientDTO expectedClientDTO = objectMapper.readValue(MockUtils.buildMock(ClientDTO.class, "expectedFindByCellPhone.json"), ClientDTO.class);
        Client client = new Client(
                UUID.randomUUID().toString(),
                expectedClientDTO.getName(),
                expectedClientDTO.getPhone(),
                expectedClientDTO.getCellPhone(),
                new Address(
                        expectedClientDTO.getAddressDTO().getStreet(),
                        expectedClientDTO.getAddressDTO().getNumber(),
                        expectedClientDTO.getAddressDTO().getPostalCode(),
                        expectedClientDTO.getAddressDTO().getCity(),
                        expectedClientDTO.getAddressDTO().getState()
                )
        );

        Mockito.when(clientRepository.findByCellPhone("31231")).thenReturn(Optional.of(client));
        Mockito.when(clientUtil.convertToClientDTO(client)).thenReturn(expectedClientDTO);

        ClientDTO actualClientDTO = clientService.findByCellPhone("31231");

        Assert.assertEquals(expectedClientDTO, actualClientDTO);

    }

    @Test
    public void findByName() throws IOException {
        ArrayList<ClientDTO> expectedClientDTOArrayList = objectMapper.readValue(MockUtils.buildMock(ClientDTO.class, "expectedFindByNameArrayList.json"), ArrayList.class);
        ArrayList<Client> expectedClientArrayList = new ArrayList<>();
        for (int i=0; i < expectedClientDTOArrayList.size(); i++){
            expectedClientArrayList.add(
                    new Client(
                            UUID.randomUUID().toString(),
                            expectedClientDTOArrayList.get(i).getName(),
                            expectedClientDTOArrayList.get(i).getPhone(),
                            expectedClientDTOArrayList.get(i).getCellPhone(),
                            new Address(
                                    expectedClientDTOArrayList.get(i).getAddressDTO().getStreet(),
                                    expectedClientDTOArrayList.get(i).getAddressDTO().getNumber(),
                                    expectedClientDTOArrayList.get(i).getAddressDTO().getPostalCode(),
                                    expectedClientDTOArrayList.get(i).getAddressDTO().getCity(),
                                    expectedClientDTOArrayList.get(i).getAddressDTO().getState()
                            )
                    )
            );
        }
//        for (ClientDTO clientDTO : expectedClientDTOArrayList) {
//            expectedClientArrayList.add(
//                    new Client(
//                            UUID.randomUUID().toString(),
//                            clientDTO.getName(),
//                            clientDTO.getPhone(),
//                            clientDTO.getCellPhone(),
//                            new Address(
//                                    clientDTO.getAddressDTO().getStreet(),
//                                    clientDTO.getAddressDTO().getNumber(),
//                                    clientDTO.getAddressDTO().getPostalCode(),
//                                    clientDTO.getAddressDTO().getCity(),
//                                    clientDTO.getAddressDTO().getState()
//                            )
//                    )
//            );
//        }

        Mockito.when(clientRepository.findByName("Emo")).thenReturn(expectedClientArrayList);
        Mockito.when(clientUtil.convertToListClientDTO(expectedClientArrayList)).thenReturn(expectedClientDTOArrayList);

        ArrayList<ClientDTO> actualClientDTOArrayList = clientService.findByName("Emo");

        Assert.assertEquals(expectedClientDTOArrayList, actualClientDTOArrayList);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void updateClient() {
    }

    @Test
    public void updateAddressClient() {
    }

    @Test
    public void delete() {
    }
}