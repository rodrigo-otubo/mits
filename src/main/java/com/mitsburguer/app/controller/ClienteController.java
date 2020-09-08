package com.mitsburguer.app.controller;

import com.mitsburguer.app.dto.AddressDTO;
import com.mitsburguer.app.dto.ClientDTO;
import com.mitsburguer.app.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ClienteController {

    private final ClientService clientService;

    @PostMapping("client")
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO){
        return new ResponseEntity(clientService.save(clientDTO), HttpStatus.CREATED);
    }

    @GetMapping("client/cellphone/{cellPhone}")
    public ResponseEntity<ClientDTO> findByCellPhone(@PathVariable("cellPhone") String cellPhone){
        return new ResponseEntity(clientService.findByCellPhone(cellPhone), HttpStatus.OK);
    }

    @GetMapping("client/name/{name}")
    public ResponseEntity<ArrayList<ClientDTO>> findByName(@PathVariable("name") String name){
        return new ResponseEntity(clientService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("client")
    public ResponseEntity<ArrayList<ClientDTO>> findAll(){
        return new ResponseEntity(clientService.findAll(), HttpStatus.OK);
    }

    @PutMapping("client/{cellPhone}")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO, @PathVariable("cellPhone") String cellPhone){
        return new ResponseEntity(clientService.updateClient(clientDTO, cellPhone), HttpStatus.OK);
    }

    @PatchMapping("client/{cellPhone}")
    public ResponseEntity<ClientDTO> updateAddressClient(@RequestBody AddressDTO addressDTO, @PathVariable("cellPhone") String cellPhone){
        return new ResponseEntity(clientService.updateAddressClient(addressDTO, cellPhone), HttpStatus.OK);
    }

    @DeleteMapping("client/{cellPhone}")
    public ResponseEntity deleteClient(@PathVariable("cellPhone") String cellPhone){
        clientService.delete(cellPhone);
        return ResponseEntity.noContent().build();
    }


}
