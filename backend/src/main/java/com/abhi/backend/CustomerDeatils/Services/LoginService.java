package com.abhi.backend.CustomerDeatils.Services;

import com.abhi.backend.CustomerDeatils.DTOS.LoginDTOs.*;
import com.abhi.backend.CustomerDeatils.Models_Entites.LoginEntity;
import com.abhi.backend.CustomerDeatils.Repository.LoginRepo;
import com.abhi.backend.CustomerDeatils.Validations.AESUtil;
import com.abhi.backend.CustomerDeatils.Validations.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    LoginRepo loginRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    Validations validations;

public LoginResponseDTO login(LoginRequestDTO loginDTO) {
    String userName = loginDTO.getUserName().toLowerCase();
    String password = loginDTO.getPassword();

    // Admin check
    if (userName.equalsIgnoreCase("admin") && password.equals("Admin@123")) {
        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(0);
        response.setUserName("Admin");
        response.setMobile(null);
        response.setMessage("Login Successful");
        response.setRole("ADMIN");
        return response;
    }
    LoginEntity user = loginRepo.findByUserName(userName)
            .orElseThrow(() -> new RuntimeException("Username not found"));
    System.out.println("Typed password: " + password);
    System.out.println("DB hash: " + user.getPassword());
    System.out.println("Match result: " + passwordEncoder.matches(password, user.getPassword()));

    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Wrong password");
    }
    // Regular user
    LoginResponseDTO response = new LoginResponseDTO();
    response.setId(user.getUserId());
    response.setUserName(user.getUserName());
    response.setMobile(user.getMobile());
    response.setMessage("Login Successful");
    response.setRole("USER");
    return response;
}
    public LoginDTO createLogin(LoginDTO loginDTO)     //create Login user
    {
        String password = loginDTO.getPassword();
        String userName = loginDTO.getUserName();
        if(!Validations.isValidPassword(password)){
            throw new RuntimeException("Wrong password");
        }
        if(loginRepo.existsByUserName(loginDTO.getUserName())){
            throw new RuntimeException("Username already exists");
        }
        if (loginRepo.existsBymobile(loginDTO.getMobile())) {
            throw new RuntimeException("Mobile already exists");
        }

        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUserName(userName.toLowerCase());
        loginEntity.setPassword(passwordEncoder.encode(password));
        try {
            loginEntity.setEncryptedPassword(AESUtil.encrypt(password));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Encryption failed: " + e.getMessage());
        }
        System.out.println("Saving user: " + userName);
        System.out.println("Raw password being encoded: " + password);
        loginEntity.setMobile(loginDTO.getMobile());

        LoginEntity savedLogin = loginRepo.save(loginEntity);

        return new LoginDTO(
                savedLogin.getUserName(),
                savedLogin.getPassword(),
                savedLogin.getMobile()
        );
    }

    public LoginDTO updateLogin (int id , LoginDTO loginDTO)    //Update Login user
    {
        LoginEntity update = loginRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Id Cannot be Blank"));
        update.setUserName(loginDTO.getUserName().toLowerCase());
        update.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        update.setMobile(loginDTO.getMobile());

        loginRepo.save(update);

        return new LoginDTO(update.getUserName(),
                update.getPassword(),
                update.getMobile()
        );
    }

    public LoginEntity findByMobile(String mobile)      //Find by mobile number
    {
        return loginRepo.findBymobile(mobile).
                orElseThrow(()-> new RuntimeException("Cannot find mobile Number"));
    }

    public ResetPasswordResponseDTO forgetPassword(Integer userId , String newPassword)       //Reset Password using mobile number
    {
        LoginEntity user = loginRepo.findById(userId)
                .orElseThrow(()->new RuntimeException("cannot find user"));
        user.setPassword(passwordEncoder.encode(newPassword));
        try {
            user.setEncryptedPassword(AESUtil.encrypt(newPassword));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Encryption failed: " + e.getMessage());
        }
        loginRepo.save(user);
        return new ResetPasswordResponseDTO("Your Password is reset SuccessFully",true);
    }

    public ShowLoginUserInfoDTO findLoginById(int id)           //Fetch All data By Id but dont show password
    {
            LoginEntity getLoginId = loginRepo.findById(id)
                    .orElseThrow(()-> new RuntimeException("Cannot find id"));
            return new ShowLoginUserInfoDTO(
                    getLoginId.getUserId(),
                    getLoginId.getUserName(),
                    getLoginId.getMobile()
            );
    }

    public List<ShowLoginUserInfoDTO> findAllUser()     //get all users but dont show password
    {
        return loginRepo.findAll().stream().map(user -> {
            String decrypted;
            try {
                decrypted = AESUtil.decrypt(user.getEncryptedPassword());
            } catch (Exception e) {
                e.printStackTrace();
                decrypted = "••••••";  // fallback if decrypt fails
            }
            return new ShowLoginUserInfoDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getMobile(),
                    decrypted
            );
        }).toList();
    }

    public LoginDTO deleteById(int id )      //delete particular user  from list
    {
        LoginEntity deleteUser = loginRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Cannot find Id At the time of delete"));
        LoginDTO deleteDTO = new LoginDTO(
                deleteUser.getUserName(),
                deleteUser.getPassword(),
                deleteUser.getMobile()
        );
        loginRepo.delete(deleteUser);
        System.out.println("User is deleted SuccessFully");

        return deleteDTO;
    }

    public String deleteAllUser()           //delete all users at once
    {
        loginRepo.deleteAll();
        return loginRepo.count() + " User Are deleted SuccessFully";
    }
}
