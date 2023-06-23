package com.upc.estureview.business;

import com.upc.estureview.entitys.Teacher;
import com.upc.estureview.entitys.User;
import com.upc.estureview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserBusiness {
    @Autowired
    private UserRepository userRepository;

    public User registrar(User user){
        return userRepository.save(user);
    }
    public User buscar(Long codigo) throws Exception {
        return userRepository.findById(codigo).
                orElseThrow(() -> new Exception("No se encontró entidad"));
    }
    public List<User> listado(){
        return userRepository.findAll();
    }
    public User actualization(User user) throws Exception {
        userRepository.findById(user.getId()).orElseThrow(() -> new Exception("No se encontró entidad"));
        return userRepository.save(user);
    }
    public User deleteElement(Long code) throws Exception{
        User user = userRepository.findById(code).orElseThrow(() -> new Exception("No se encontró entidad"));
        userRepository.delete(user);
        return user;
    }
    public User login (String email, String password){
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}
