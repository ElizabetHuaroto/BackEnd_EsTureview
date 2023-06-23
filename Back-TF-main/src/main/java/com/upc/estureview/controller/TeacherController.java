package com.upc.estureview.controller;

import com.upc.estureview.business.TeacherBusiness;
import com.upc.estureview.dtos.TeacherDTO;
import com.upc.estureview.dtos.UserDTO;
import com.upc.estureview.entitys.Teacher;
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
public class TeacherController {
    @Autowired
    public TeacherBusiness business;
    Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @GetMapping("/teacherObtener")
    public ResponseEntity<List<TeacherDTO>> obtener(){
        List<Teacher> list = business.CompletList();
        List<TeacherDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<TeacherDTO>>(listDto, HttpStatus.OK);
    }
    @GetMapping("/teacherObtener/{nombre}") //CAMBIARLE LA RUTA A LOS METODOS DE BUSCAR!!
    public ResponseEntity<List<TeacherDTO>>  obtenerNombreAutores(@PathVariable(value = "nombre") String nombre){
        List<Teacher> list = business.findbyName(nombre);
        List<TeacherDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<TeacherDTO>>(listDto,HttpStatus.OK);
    }
    private TeacherDTO convertToDto(Teacher teacher) {
        ModelMapper modelMapper = new ModelMapper();
        TeacherDTO teacherDTO = modelMapper.map(teacher, TeacherDTO.class);
        return teacherDTO;
    }
    //listId
    @GetMapping("/teachers/{codigo}")
    public ResponseEntity<TeacherDTO> obtenerEntidad(@PathVariable(value = "codigo") Long codigo){
        Teacher teacher;
        TeacherDTO authorDTO;
        try {
            logger.debug("Buscando entidad");
            teacher = business.buscar(codigo);
            authorDTO = convertToDto(teacher);
        }catch(Exception e){
            logger.error("Error de Obtener Entidad");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mi mensaje");
        }
        return new ResponseEntity<TeacherDTO>(authorDTO, HttpStatus.OK);
    }

    @PostMapping("/teacherCrear")
    public TeacherDTO crear(@RequestBody TeacherDTO teacherDTO) {
        Teacher teacher;
        try {
            teacher = convertToEntity(teacherDTO);
            teacher = business.register(teacher);

        }catch(Exception e){
            logger.error("Error de creación",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return convertToDto(teacher);
    }
    /*@PostMapping("/teacher")
    public ResponseEntity<TeacherDTO> crearAutor(@RequestBody TeacherDTO teacherDTO) {
        Teacher teacher;
        try {
            logger.debug("Creando objeto");
            teacher = convertToEntity(teacherDTO);
            teacherDTO = convertToDto(business.register(teacher));
        }catch(Exception e){
            logger.error("Error de creación",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<TeacherDTO>(teacherDTO, HttpStatus.OK);
    }*/
    @DeleteMapping("/teacherBorrar/{codigo}")
    public ResponseEntity<TeacherDTO> borrarAutor(@PathVariable(value = "codigo") Long codigo){
        Teacher teacher;
        TeacherDTO authorDTO;
        try {
            teacher = business.deleteElement(codigo);
            logger.debug("Eliminando objeto");
            authorDTO = convertToDto(teacher);
            return new ResponseEntity<TeacherDTO>(authorDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de Eliminación ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo eliminar, sorry");
        }
    }
    @PutMapping("/teacherActualizar")
    public ResponseEntity<TeacherDTO> actualizarAutor(@RequestBody TeacherDTO teacherDetalle) {
        TeacherDTO teacherDTO;
        Teacher teacher;
        try {
            teacher = convertToEntity(teacherDetalle);
            logger.debug("Actualizando producto");
            teacher = business.actualization(teacher);
            logger.debug("Producto Actualizado");
            teacherDTO = convertToDto(teacher);
            return new ResponseEntity<TeacherDTO>(teacherDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de Actualización ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }
    private Teacher convertToEntity(TeacherDTO authorDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Teacher post = modelMapper.map(authorDTO, Teacher.class);
        return post;
    }

    private List<TeacherDTO> convertToLisDto(List<Teacher> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
