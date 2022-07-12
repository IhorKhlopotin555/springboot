package com.example.springboot.Controllers;

import com.example.springboot.dao.UserDAO;
import com.example.springboot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@RequestMapping("/users")
public class UserController {


    private UserDAO userDAO;


    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public void saveUser(@RequestBody User user){
        userDAO.save(user);
    }

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getUsers(){
//        List<User> users = userDAO.findAll();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> getUserById (@PathVariable int id){
//        Optional<User> byId = userDAO.findById(id);
//        User user = byId.get();
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userDAO.findAll(), HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById (@PathVariable int id){
        return new ResponseEntity<>(userDAO.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/users/byname")
    public ResponseEntity<List<User>> getUserByName(@RequestParam String name) {
       // List<User> users = userDAO.findMeAUserWithName(name);
        List<User> users = userDAO.findByName(name);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PatchMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User save = userDAO.save(user);

        return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
    }
}

