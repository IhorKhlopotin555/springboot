package com.example.springboot.Controllers;

import com.example.springboot.dao.PassportDAO;
import com.example.springboot.models.Passport;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/passport")
public class PassportController {
    private PassportDAO passportDAO;
    @GetMapping("")
    public ResponseEntity<List<Passport>> getPassports (){
        return new ResponseEntity<List<Passport>>(passportDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passport> getPassport(@PathVariable int id){
        return new ResponseEntity<>(passportDAO.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void savePassport(@RequestParam String series, @RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();// gif.gif
        File dest = new File(System.getProperty("user.home") + File.separator + "someImages" + File.separator + originalFilename);
        file.transferTo(dest);
        Passport passport = new Passport(series, originalFilename);
        passportDAO.save(passport);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePassport(@PathVariable int id){
        passportDAO.deleteById(id);
    }

    @PatchMapping("")
    public ResponseEntity<Passport> patchPassport(@RequestBody Passport passport){
       Passport save = passportDAO.save(passport);
        return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
    }
}
