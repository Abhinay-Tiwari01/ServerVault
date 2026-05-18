package com.abhi.backend.CustomerDeatils.Services;

import com.abhi.backend.CustomerDeatils.DTOS.ServerDetailDTO.ShowServerDetailsDTO;
import com.abhi.backend.CustomerDeatils.DTOS.ServerDetailsDTO;
import com.abhi.backend.CustomerDeatils.Models_Entites.ServerDetailsEntity;
import com.abhi.backend.CustomerDeatils.Repository.ServerDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerDetailsService {

    @Autowired
    ServerDetailsRepo serverDetailsRepo;
    //ServerDetailsDTO
    public ServerDetailsDTO createCompany(ServerDetailsDTO detailsDTO)
    {
        ServerDetailsEntity detailsEntity = new ServerDetailsEntity();
        detailsEntity.setCompanyName(detailsDTO.getCompanyName());
        detailsEntity.setCompanyAddress(detailsDTO.getCompanyAddress());
        detailsEntity.setPaymentAmount(detailsDTO.getPaymentAmount());
        detailsEntity.setPaymentYear(detailsDTO.getPaymentYear());
        detailsEntity.setServerIpName(detailsDTO.getServerIpName());
        detailsEntity.setMcompName(detailsDTO.getMcompName());
        detailsEntity.setUploadDate(detailsDTO.getUploadDate());
        detailsEntity.setLoginUserName(detailsDTO.getLoginUserName());
        detailsEntity.setPassword(detailsDTO.getPassword());

        ServerDetailsEntity saveInfo = serverDetailsRepo.save(detailsEntity);
        return new ServerDetailsDTO(
                saveInfo.getCompanyName(),
                saveInfo.getCompanyAddress(),saveInfo.getPaymentAmount(),
                saveInfo.getPaymentYear(),saveInfo.getServerIpName(),
                saveInfo.getMcompName(),saveInfo.getUploadDate(),
                saveInfo.getLoginUserName(), saveInfo.getPassword()
        );
    }

    public ServerDetailsDTO updateCompanyInfo(int id , ServerDetailsDTO serverDetailsDTO)
    {
        ServerDetailsEntity detailsEntity = serverDetailsRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Cannot find at the time of Update"));
        detailsEntity.setCompanyName(serverDetailsDTO.getCompanyName());
        detailsEntity.setCompanyAddress(serverDetailsDTO.getCompanyAddress());
        detailsEntity.setPaymentAmount(serverDetailsDTO.getPaymentAmount());
        detailsEntity.setPaymentYear(serverDetailsDTO.getPaymentYear());
        detailsEntity.setServerIpName(serverDetailsDTO.getServerIpName());
        detailsEntity.setMcompName(serverDetailsDTO.getMcompName());
        detailsEntity.setUploadDate(serverDetailsDTO.getUploadDate());
        detailsEntity.setLoginUserName(serverDetailsDTO.getLoginUserName());
        detailsEntity.setPassword(serverDetailsDTO.getPassword());

        ServerDetailsEntity updateCompanyInfo = serverDetailsRepo.save(detailsEntity);
        return new ServerDetailsDTO(
                updateCompanyInfo.getCompanyName(),
                updateCompanyInfo.getCompanyAddress(),updateCompanyInfo.getPaymentAmount(),
                updateCompanyInfo.getPaymentYear(),updateCompanyInfo.getServerIpName(),
                updateCompanyInfo.getMcompName(),updateCompanyInfo.getUploadDate(),
                updateCompanyInfo.getLoginUserName(), updateCompanyInfo.getPassword()
        );
    }
    public ShowServerDetailsDTO getServerInfoById(int id)
    {
        ServerDetailsEntity getDetailsById = serverDetailsRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Cannot find at the time of Get"));
        return new ShowServerDetailsDTO(
                getDetailsById.getServerId(),
                getDetailsById.getCompanyName(),
                getDetailsById.getCompanyAddress(), getDetailsById.getPaymentAmount(),
                getDetailsById.getPaymentYear(), getDetailsById.getServerIpName(),
                getDetailsById.getMcompName(), getDetailsById.getUploadDate(),
                getDetailsById.getLoginUserName(), getDetailsById.getPassword()
        );
    }
    public List<ShowServerDetailsDTO> getAllDetails()
    {
        return serverDetailsRepo.findAll().stream().map(
                serverDetails -> new ShowServerDetailsDTO(
                        serverDetails.getServerId(),
                        serverDetails.getCompanyName(),
                        serverDetails.getCompanyAddress(),serverDetails.getPaymentAmount(),
                        serverDetails.getPaymentYear(),serverDetails.getServerIpName(),
                        serverDetails.getMcompName(),serverDetails.getUploadDate(),
                        serverDetails.getLoginUserName(), serverDetails.getPassword()
                ))
                .toList();
    }
    public ServerDetailsDTO getServerInfoByCompanyName(String companyName)
    {
        ServerDetailsEntity getCompanyName = serverDetailsRepo.findByCompanyName(companyName)
                .orElseThrow(()->new RuntimeException("Cannot find Company Name at the time of Get"));
        return new ServerDetailsDTO(
                getCompanyName.getCompanyName(),
                getCompanyName.getCompanyAddress(), getCompanyName.getPaymentAmount(),
                getCompanyName.getPaymentYear(), getCompanyName.getServerIpName(),
                getCompanyName.getMcompName(), getCompanyName.getUploadDate(),
                getCompanyName.getLoginUserName(), getCompanyName.getPassword()
        );
    }
    public ShowServerDetailsDTO deleteServerInfoById(int id)
    {
        ServerDetailsEntity deleteDetail = serverDetailsRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Cannot find at the time of Get"));
        ShowServerDetailsDTO deleteDetailsById = new ShowServerDetailsDTO(
                deleteDetail.getServerId(),
                deleteDetail.getCompanyName(),
                deleteDetail.getCompanyAddress(),deleteDetail.getPaymentAmount(),
                deleteDetail.getPaymentYear(),deleteDetail.getServerIpName(),
                deleteDetail.getMcompName(),deleteDetail.getUploadDate(),
                deleteDetail.getLoginUserName(), deleteDetail.getPassword()
        );
        serverDetailsRepo.delete(deleteDetail);
        return deleteDetailsById;
    }
    public String deleteAllDetails()
    {
        serverDetailsRepo.deleteAll();
        return "All Server Info  Deleted Successfully";
    }

}
