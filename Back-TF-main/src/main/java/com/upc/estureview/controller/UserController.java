package com.upc.estureview.controller;

import com.upc.estureview.business.UserBusiness;
import com.upc.estureview.dtos.UserDTO;
import com.upc.estureview.entitys.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserBusiness business;
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> obtener(){
        List<User> list = business.listado();
        List<UserDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<UserDTO>>(listDto, HttpStatus.OK);
    }
    @GetMapping("/usercodigo/{codigo}")
    public ResponseEntity<UserDTO> obtenerEntidad(@PathVariable(value = "codigo") Long codigo){
        User user;
        UserDTO authorDTO;
        try {
            logger.debug("Buscando entidad");
            user = business.buscar(codigo);
            authorDTO = convertToDto(user);
        }catch(Exception e){
            logger.error("Error de Obtener Entidad");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "uwun't");
        }
        return new ResponseEntity<UserDTO>(authorDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO userDTO){

        UserDTO userDTO1;
        userDTO1 = convertToDto(business.login(userDTO.getEmail(), userDTO.getPassword()));
       return userDTO1;
    }

    @PostMapping("/usercrear")
    public UserDTO crear(@RequestBody UserDTO userDTO) {
        User user;
        try {
            user = convertToEntity(userDTO);
            user = business.registrar(user);

        }catch(Exception e){
            logger.error("Error de creación",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return convertToDto(user);
    }
    private UserDTO convertToDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User post = modelMapper.map(userDTO, User.class);
        return post;
    }

    private List<UserDTO> convertToLisDto(List<User> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
