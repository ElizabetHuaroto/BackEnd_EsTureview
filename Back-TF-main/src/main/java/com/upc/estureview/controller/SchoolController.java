package com.upc.estureview.controller;

import com.upc.estureview.business.SchoolBusiness;
import com.upc.estureview.dtos.SchoolDTO;
import com.upc.estureview.dtos.UserDTO;
import com.upc.estureview.entitys.School;
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
public class SchoolController {
    @Autowired
    public SchoolBusiness business;
    Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @GetMapping("/schools")
    public ResponseEntity<List<SchoolDTO>> obtener(){
        List<School> list = business.CompletList();
        List<SchoolDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<SchoolDTO>>(listDto, HttpStatus.OK);
    }
    @GetMapping("/schoolObtener/{nombre}")
    public ResponseEntity<List<SchoolDTO>>  obtenerNombre(@PathVariable(value = "nombre") String nombre){
        List<School> list = business.findbyName(nombre);
        List<SchoolDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<SchoolDTO>>(listDto,HttpStatus.OK);
    }
    private SchoolDTO convertToDto(School school) {
        ModelMapper modelMapper = new ModelMapper();
        SchoolDTO schoolDTO = modelMapper.map(school, SchoolDTO.class);
        return schoolDTO;
    }
    //listId
    @GetMapping("/schoolEntidad/{codigo}")
    public ResponseEntity<SchoolDTO> obtenerEntidad(@PathVariable(value = "codigo") Long codigo){
        School school;
        SchoolDTO schoolDTO;
        try {
            logger.debug("Buscando entidad");
            school = business.buscar(codigo);
            schoolDTO = convertToDto(school);
        }catch(Exception e){
            logger.error("Error de Obtener Entidad");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mi mensaje");
        }
        return new ResponseEntity<SchoolDTO>(schoolDTO, HttpStatus.OK);
    }
   /* @PostMapping("/school")
    public ResponseEntity<SchoolDTO> crear(@RequestBody SchoolDTO schoolDTO) {
        School school;
        try {
            logger.debug("Creando objeto");
            school = convertToEntity(schoolDTO);
            schoolDTO = convertToDto(business.register(school));
        }catch(Exception e){
            logger.error("Error de creación",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<SchoolDTO>(schoolDTO, HttpStatus.OK);
    }*/
   @PostMapping("/schoolcrear")
   public SchoolDTO crear(@RequestBody SchoolDTO schoolDTO) {
       School school;
       try {
           school = convertToEntity(schoolDTO);
           school = business.register(school);

       }catch(Exception e){
           logger.error("Error de creación",e);
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
       }
       return convertToDto(school);
   }
    @DeleteMapping("/schoolborrar/{codigo}")
    public ResponseEntity<SchoolDTO> borrarr(@PathVariable(value = "codigo") Long codigo){
        School school;
        SchoolDTO schoolDTO;
        try {
            school = business.deleteElement(codigo);
            logger.debug("Eliminando objeto");
            schoolDTO = convertToDto(school);
            return new ResponseEntity<SchoolDTO>(schoolDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de Eliminación ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }
    @PutMapping("/schoolActualizar")
    public ResponseEntity<SchoolDTO> actualizar(@RequestBody SchoolDTO Detalle) {
        SchoolDTO schoolDTO;
        School school;
        try {
            school = convertToEntity(Detalle);
            logger.debug("Actualizando producto");
            school = business.actualization(school);
            logger.debug("Producto Actualizado");
            schoolDTO = convertToDto(school);
            return new ResponseEntity<SchoolDTO>(schoolDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de Actualización ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }
    private School convertToEntity(SchoolDTO schoolDTO) {
        ModelMapper modelMapper = new ModelMapper();
        School post = modelMapper.map(schoolDTO, School.class);
        return post;
    }

    private List<SchoolDTO> convertToLisDto(List<School> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
