package com.abhi.backend.CustomerDeatils.Controller;


import com.abhi.backend.CustomerDeatils.DTOS.LoginDTOs.*;
import com.abhi.backend.CustomerDeatils.Models_Entites.LoginEntity;
import com.abhi.backend.CustomerDeatils.Services.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class LoginPageController {

    @Autowired
    LoginService loginService;

    @PostMapping("/userLogin")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginDTO) {
        try{
            LoginResponseDTO loginResponseDTO = loginService.login(loginDTO);
            return ResponseEntity.ok(loginResponseDTO);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponseDTO(
                            null,
                            null,
                            null,
                            null,
                            e.getMessage()
                    ));
        }
    }

    @PostMapping("/createLogin")
    public LoginDTO createLogin(@RequestBody LoginDTO loginDTO)
    {
        LoginDTO create = loginService.createLogin(loginDTO);
        return create;
    }
    @PostMapping("/forgetPassword/{userId}")
    public ResetPasswordResponseDTO forgetPassword(@PathVariable Integer userId , @RequestBody String newPassword)
    {
        ResetPasswordResponseDTO resetPasswordResponseDTO = loginService.forgetPassword(userId,newPassword);
        return resetPasswordResponseDTO;
    }

    @PutMapping("/updateLogin/{id}")
    public LoginDTO updateLogin(@PathVariable int id , @RequestBody LoginDTO loginDTO)
    {
        LoginDTO updated = loginService.updateLogin(id,loginDTO);
        return updated;
    }

    @GetMapping("/getUserById/{id}")
    public ShowLoginUserInfoDTO getUser(@PathVariable int id)
    {
        ShowLoginUserInfoDTO getUserById = loginService.findLoginById(id);
        return getUserById;
    }
    @GetMapping("/getAllUsers")
    public List<ShowLoginUserInfoDTO> getAllUsers()
    {
        List<ShowLoginUserInfoDTO> getAllUsers = loginService.findAllUser();
        return getAllUsers;
    }
    @GetMapping("/findUserbyMobile/{mobile}")
    public LoginEntity findByMobile(@PathVariable String mobile)
    {
        LoginEntity findByMobile = loginService.findByMobile(mobile);
        return findByMobile;
    }

    @DeleteMapping("/deleteById/{id}")
    public LoginDTO deleteById(@PathVariable int id )
    {
        LoginDTO deleteById = loginService.deleteById(id);
        return deleteById;
    }
    @DeleteMapping("/deleteAllUsers")
    public String deleteAllUsers()
    {
        loginService.deleteAllUser();
        return "All Users is been deleted ";
    }

}

