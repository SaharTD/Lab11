package com.example.lab11.Service;

import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public void addUser(User user){
        user.setRegistration_date(LocalDate.now());
        userRepository.save(user);
    }


    public Boolean updateUser (User user, Integer id)
    {
        User oldUser=userRepository.findUserById(id);

        if(oldUser!=null){
            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());
            oldUser.setEmail(user.getEmail());
            oldUser.setRegistration_date(user.getRegistration_date());

            userRepository.save(oldUser);
            return true;
        }
            return false;
    }



    public Boolean deleteUser (Integer id){

        if(userRepository.findUserById(id)!=null){
           userRepository.delete(userRepository.findUserById(id));
            return true;
        }
        return false;
    }

///6
    public List<User> getUserWithSpecifLetter (String  letter) {
        return userRepository.getUserWithSpecifLetter(letter);


    }


    /// 7

    public User findUserByEmail (String  email) {
        return userRepository.findUserByEmail(email);

    }

    /// 8
    public List<User> getAllUsersAfterData (LocalDate  date) {
        return userRepository.getAllUsersAfterData(date);

    }



}
