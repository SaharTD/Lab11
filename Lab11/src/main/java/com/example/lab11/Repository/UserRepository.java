package com.example.lab11.Repository;


import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);

@Query ("select u from User u where u.username like ?1")
List<User> getUserWithSpecifLetter(String letter);

    User findUserByEmail( String email);


    @Query("select  u from User u  where u.registration_date>?1 ")
    List<User>  getAllUsersAfterData(LocalDate date);

}
