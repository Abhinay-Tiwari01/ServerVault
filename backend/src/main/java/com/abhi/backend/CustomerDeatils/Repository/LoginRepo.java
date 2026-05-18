package com.abhi.backend.CustomerDeatils.Repository;


import com.abhi.backend.CustomerDeatils.Models_Entites.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo extends JpaRepository<LoginEntity, Integer> {
//    LoginEntity findByUserNameAndPassword(String userName, String password);
//        Optional<LoginEntity> findById(Integer id);
    Optional<LoginEntity> findBymobile(String mobile);
    Optional<LoginEntity> findByUserName(String username);
    boolean existsBymobile(String mobile);
    boolean existsByUserName(String username);

}
