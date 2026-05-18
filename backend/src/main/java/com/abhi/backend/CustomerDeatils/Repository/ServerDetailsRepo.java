package com.abhi.backend.CustomerDeatils.Repository;

import com.abhi.backend.CustomerDeatils.Models_Entites.ServerDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServerDetailsRepo extends JpaRepository<ServerDetailsEntity,Integer> {
    Optional<ServerDetailsEntity> findByCompanyName(String companyName);
}