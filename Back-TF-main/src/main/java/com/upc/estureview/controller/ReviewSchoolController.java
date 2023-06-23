package com.upc.estureview.controller;

import com.upc.estureview.business.ReviewSchoolBusiness;
import com.upc.estureview.business.ReviewTeacherBusiness;
import com.upc.estureview.business.UserBusiness;
import com.upc.estureview.dtos.ReviewSchoolDTO;
import com.upc.estureview.dtos.ReviewTeacherDTO;
import com.upc.estureview.dtos.UserDTO;
import com.upc.estureview.entitys.ReviewSchool;
import com.upc.estureview.entitys.ReviewTeacher;
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
public class ReviewSchoolController {
    @Autowired
    public ReviewSchoolBusiness business;
    @GetMapping("/message")
    public String Mensaje(){
        return "si funciona :D";
    }
    Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @GetMapping("/reviewSchools")
    public ResponseEntity<List<ReviewSchoolDTO>> obtener(){
        List<ReviewSchool> list = business.CompletList();
        List<ReviewSchoolDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<ReviewSchoolDTO>>(listDto, HttpStatus.OK);
    }

    /*@PostMapping("/reviewSchoolCrear")
    public ReviewSchoolDTO crear(@RequestBody ReviewSchoolDTO reviewSchoolDTO) {
        ReviewSchool reviewSchool;
        try {
            reviewSchool = convertToEntity(reviewSchoolDTO);
            reviewSchool = business.register(reviewSchool);

        }catch(Exception e){
            logger.error("Error de creación",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return convertToDto(reviewSchool);
    }*/
    @PostMapping("/reviewSchoolCrear")
    public ResponseEntity<ReviewSchoolDTO> crear(@RequestBody ReviewSchoolDTO reviewSchoolDTO) {
        ReviewSchool reviewSchool;
        try {
            logger.debug("Creando objeto");
            reviewSchool = convertToEntity(reviewSchoolDTO);
            reviewSchoolDTO = convertToDto(business.register(reviewSchool));
        }catch(Exception e){
            logger.error("Error de creación",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);
        }
        return new ResponseEntity<ReviewSchoolDTO>(reviewSchoolDTO, HttpStatus.OK);
    }

    @GetMapping("/reviewSchoolCodigo/{codigo}")
    public ResponseEntity<ReviewSchoolDTO> obtenerEntidad(@PathVariable(value = "codigo") Long codigo){
        ReviewSchool reviewSchool;
        ReviewSchoolDTO reviewSchoolDTO;
        try {
            logger.debug("Buscando entidad");
            reviewSchool = business.buscar(codigo);
            reviewSchoolDTO = convertToDto(reviewSchool);
        }catch(Exception e){
            logger.error("Error de Obtener Entidad");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mi mensaje");
        }
        return new ResponseEntity<ReviewSchoolDTO>(reviewSchoolDTO, HttpStatus.OK);
    }

    @GetMapping("/reviewSchoollISTA/{codigo}")
    public ResponseEntity<List<ReviewSchoolDTO>> ListaParaperfil(@PathVariable(value = "codigo") Long codigo){

        List<ReviewSchool> list = business.CompletListbyIdSchool(codigo);
        List<ReviewSchoolDTO> listDto = convertToLisDto(list);
        return new ResponseEntity<List<ReviewSchoolDTO>>(listDto, HttpStatus.OK);
    }

    @PutMapping("/reviewSchoolActualizar")
    public ResponseEntity<ReviewSchoolDTO> actualizar(@RequestBody ReviewSchoolDTO Detalle) {
        ReviewSchoolDTO reviewSchoolDTO;
        ReviewSchool reviewSchool;
        try {
            reviewSchool = convertToEntity(Detalle);
            logger.debug("Actualizando producto");
            reviewSchool = business.actualization(reviewSchool);
            logger.debug("Producto Actualizado");
            reviewSchoolDTO = convertToDto(reviewSchool);
            return new ResponseEntity<ReviewSchoolDTO>(reviewSchoolDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de Actualización ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }
    private ReviewSchoolDTO convertToDto(ReviewSchool reviewSchool) {
        ModelMapper modelMapper = new ModelMapper();
        ReviewSchoolDTO reviewSchoolDTO  = modelMapper.map(reviewSchool, ReviewSchoolDTO.class);
        return reviewSchoolDTO;
    }

    private ReviewSchool convertToEntity(ReviewSchoolDTO reviewSchoolDTO) {
        ModelMapper modelMapper = new ModelMapper();
        ReviewSchool post = modelMapper.map(reviewSchoolDTO, ReviewSchool.class);
        return post;
    }

    private List<ReviewSchoolDTO> convertToLisDto(List<ReviewSchool> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
