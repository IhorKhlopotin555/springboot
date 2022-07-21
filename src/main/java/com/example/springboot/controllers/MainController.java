package com.example.springboot.controllers;

import com.example.springboot.dao.CustomerDAO;
import com.example.springboot.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {

    private CustomerDAO customerDAO;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home(){
        return "anya";
    }
    @PostMapping("/")
    public String homePost(){
        return "anchous";
    }
    @GetMapping("customers")
    public String users(){
        return "users array";
    }
    @PostMapping("/customers")
    public void saveUser(@RequestBody Customer customer){
        String encode = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encode);
        customerDAO.save(customer);
    }

}
