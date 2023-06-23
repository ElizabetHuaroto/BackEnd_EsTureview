package com.upc.estureview.repository;

import com.upc.estureview.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByEmailAndPassword(String email, String password);

}
