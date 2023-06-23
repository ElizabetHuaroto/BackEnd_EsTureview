package com.upc.estureview.controller;

import com.upc.estureview.business.ReviewTeacherBusiness;
import com.upc.estureview.business.SchoolBusiness;
import com.upc.estureview.dtos.ReviewTeacherDTO;
import com.upc.estureview.dtos.SchoolDTO;
import com.upc.estureview.dtos.UserDTO;
import com.upc.estureview.entitys.ReviewTeacher;
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
public class ReviewTeacherController {

    @Autowired
    public ReviewTeacherBusiness business;
    Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @GetMapping("/reviewTeachers")
    public ResponseEntity<List<ReviewTeacherDTO>> obtener(){
        List<ReviewTeacher> list = business.CompletList();
        List<ReviewTeacherDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<ReviewTeacherDTO>>(listDto, HttpStatus.OK);
    }

    @PostMapping("/reviewTeacherCrear")
    public ReviewTeacherDTO crear(@RequestBody ReviewTeacherDTO reviewTeacherDTO) {
        ReviewTeacher reviewTeacher;
        try {
            reviewTeacher = convertToEntity(reviewTeacherDTO);
            reviewTeacher = business.register(reviewTeacher);

        }catch(Exception e){
            logger.error("Error de creación",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return convertToDto(reviewTeacher);
    }

    /*@PostMapping("/reviewTeacher")
    public ResponseEntity<ReviewTeacherDTO> crear(@RequestBody ReviewTeacherDTO reviewTeacherDTO) {
        ReviewTeacher reviewTeacher;
        try {
            logger.debug("Creando objeto");
            reviewTeacher = convertToEntity(reviewTeacherDTO);
            reviewTeacherDTO = convertToDto(business.register(reviewTeacher));
        }catch(Exception e){
            logger.error("Error de creación",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<ReviewTeacherDTO>(reviewTeacherDTO, HttpStatus.OK);
    }*/

    @GetMapping("/reviewTeacherCodigo/{codigo}")
    public ResponseEntity<ReviewTeacherDTO> obtenerEntidad(@PathVariable(value = "codigo") Long codigo){
        ReviewTeacher reviewTeacher;
        ReviewTeacherDTO reviewTeacherDTO;
        try {
            logger.debug("Buscando entidad");
            reviewTeacher = business.buscar(codigo);
            reviewTeacherDTO = convertToDto(reviewTeacher);
        }catch(Exception e){
            logger.error("Error de Obtener Entidad");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mi mensaje");
        }
        return new ResponseEntity<ReviewTeacherDTO>(reviewTeacherDTO, HttpStatus.OK);
    }
    @GetMapping("/reviewTeacherslISTA/{codigo}")
    public ResponseEntity<List<ReviewTeacherDTO>> ListaParaperfil(@PathVariable(value = "codigo") Long codigo){

        List<ReviewTeacher> list = business.CompletListbyIdProfesor(codigo);
        List<ReviewTeacherDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<ReviewTeacherDTO>>(listDto, HttpStatus.OK);
    }


    @PutMapping("/reviewTeacherActualizar")
    public ResponseEntity<ReviewTeacherDTO> actualizar(@RequestBody ReviewTeacherDTO Detalle) {
        ReviewTeacherDTO reviewTeacherDTO;
        ReviewTeacher reviewTeacher;
        try {
            reviewTeacher = convertToEntity(Detalle);
            logger.debug("Actualizando producto");
            reviewTeacher = business.actualization(reviewTeacher);
            logger.debug("Producto Actualizado");
            reviewTeacherDTO = convertToDto(reviewTeacher);
            return new ResponseEntity<ReviewTeacherDTO>(reviewTeacherDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de Actualización ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }
    private ReviewTeacherDTO convertToDto(ReviewTeacher reviewTeacher) {
        ModelMapper modelMapper = new ModelMapper();
        ReviewTeacherDTO reviewTeacherDTO  = modelMapper.map(reviewTeacher, ReviewTeacherDTO.class);
        return reviewTeacherDTO;
    }

    private ReviewTeacher convertToEntity(ReviewTeacherDTO reviewTeacherDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ReviewTeacher post = modelMapper.map(reviewTeacherDTO, ReviewTeacher.class);
        return post;
    }

    private List<ReviewTeacherDTO> convertToLisDto(List<ReviewTeacher> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
