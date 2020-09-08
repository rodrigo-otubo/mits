//package com.mitsburguer.app.service.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mitsburguer.app.MockUtils;
//import com.mitsburguer.app.dto.ClientDTO;
//import com.mitsburguer.app.model.Address;
//import com.mitsburguer.app.model.Client;
//import com.mitsburguer.app.repository.ClientRepository;
//import com.mitsburguer.app.service.ClientService;
//import com.mitsburguer.app.util.ClientUtil;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import java.io.IOException;
//import java.util.Optional;
//import java.util.UUID;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class ClientServiceImplTest {
//
//    private ObjectMapper objectMapper;
//    @Mock
//    private ClientRepository clientRepository;
//    private ClientService clientService;
//    private ClientUtil clientUtil;
//    @Mock
//    private ClientUtil clientUtilMock;
//
//    @Before
//    public void setup(){
//        objectMapper = new ObjectMapper();
//        clientUtil = new ClientUtil();
//        clientService = new ClientServiceImpl(clientRepository, clientUtilMock);
//    }
//
//    @Test
//    public void save() throws IOException {
//        ClientDTO expectedClientDTO = objectMapper.readValue(MockUtils.buildMock(ClientDTO.class, "expectedClientSaved.json"), ClientDTO.class);
//        ClientDTO requestClientDTO =  expectedClientDTO;
//        Mockito.when(clientRepository.findByCellPhone(requestClientDTO.getCellPhone())).thenReturn(Optional.empty());
//        Client client = new Client(
//                UUID.randomUUID().toString(),
//                expectedClientDTO.getName(),
//                expectedClientDTO.getPhone(),
//                expectedClientDTO.getCellPhone(),
//                new Address(
//                        expectedClientDTO.getAddressDTO().getStreet(),
//                        expectedClientDTO.getAddressDTO().getNumber(),
//                        expectedClientDTO.getAddressDTO().getPostalCode(),
//                        expectedClientDTO.getAddressDTO().getCity(),
//                        expectedClientDTO.getAddressDTO().getState()));
//        Mockito.when(clientUtilMock.convertToClient(requestClientDTO)).thenReturn(client);
//        Mockito.when(clientRepository.save(client)).thenReturn(client);
//        ClientDTO actualClientDTO = clientService.save(requestClientDTO);
//
//        Assert.assertEquals(requestClientDTO, actualClientDTO);
//    }
//
//    @Test
//    public void findByCellPhone() {
//
//    }
//
//    @Test
//    public void findByName() {
//    }
//
//    @Test
//    public void findAll() {
//    }
//
//    @Test
//    public void updateClient() {
//    }
//
//    @Test
//    public void updateAddressClient() {
//    }
//
//    @Test
//    public void delete() {
//    }
//}