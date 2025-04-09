package com.example.lab11.Controller;


import com.example.lab11.Api.ApiResponse;
import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;



    @GetMapping("get")
    public ResponseEntity getAllUsers (){
        return ResponseEntity.status(200).body(userService.getAllUsers()) ;
    }

    @PostMapping("add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors e ){

        if (e.hasErrors()){
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());

        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("The user is added successfully"));
    }


    @PutMapping("update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user, Errors e, @PathVariable Integer id){

        if(e.hasErrors()){
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
        }


        if(userService.updateUser(user,id)){
            return ResponseEntity.status(200).body(new ApiResponse("The user is added successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The user is not found"));


    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){

        if(userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("The user is deleted successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The user is not found"));

    }



    @GetMapping("get-letter/{letter}")
    public ResponseEntity getUserWithSpecifLetter (@PathVariable String  letter){
        if (userService.getUserWithSpecifLetter(letter)==null){
            return ResponseEntity.status(400).body(new ApiResponse("no user name start with: "+letter)) ;
        }
        return ResponseEntity.status(200).body(userService.getUserWithSpecifLetter(letter)) ;
    }

    @GetMapping("get-email/{email}")
    public ResponseEntity findUserByEmail (@PathVariable String  email){
        if (userService.findUserByEmail(email).equals(null)){
            return ResponseEntity.status(400).body(new ApiResponse("no user name with this email : "+email)) ;
        }
        return ResponseEntity.status(200).body(userService.findUserByEmail(email)) ;
    }

    @GetMapping("get-date/{date}")
    public ResponseEntity findUserByEmail (@PathVariable LocalDate date){
//       User u= userService.findUserByEmail(email);
        if (userService.getAllUsersAfterData(date).equals(null)){
            return ResponseEntity.status(400).body(new ApiResponse("no users where register after this date ")) ;
        }
        return ResponseEntity.status(200).body(userService.getAllUsersAfterData(date)) ;
    }




}
