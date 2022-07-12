package com.example.springboot.services;

import com.example.springboot.dao.PassportDAO;
import com.example.springboot.dao.UserDAO;
import com.example.springboot.models.User;
import com.example.springboot.models.dto.UserPassportResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;
    private PassportDAO passportDAO;

    public void save(User user){
        if(user.getName() != null){
            userDAO.save(user);
        }
    }
    public List<User> findAll(){
        return userDAO.findAll();
    }
    public User findById(int id){
        return (userDAO.findById(id).get());
    }
    public List<User> findByName(String name){
        return userDAO.findByName(name);
    }

}
