package com.abhi.backend.CustomerDeatils.Controller;

import com.abhi.backend.CustomerDeatils.DTOS.ServerDetailDTO.ShowServerDetailsDTO;
import com.abhi.backend.CustomerDeatils.DTOS.ServerDetailsDTO;
import com.abhi.backend.CustomerDeatils.Services.ServerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/server")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class ServerDetailsController {

    @Autowired
    ServerDetailsService service;
    @PostMapping("/createCompany")
    public ServerDetailsDTO createCompany(@RequestBody ServerDetailsDTO detailsDTO)
    {
        ServerDetailsDTO serverDetailsDTO = service.createCompany(detailsDTO);
        return serverDetailsDTO;
    }
    @PutMapping("/updateCompany/{id}")
    public ServerDetailsDTO updateCompany(@PathVariable int id , @RequestBody ServerDetailsDTO detailsDTO)
    {
        ServerDetailsDTO serverDetailsDTO = service.updateCompanyInfo(id,detailsDTO);
        return serverDetailsDTO;
    }
    @GetMapping("/getCompanyById/{id}")
    public ShowServerDetailsDTO getCompanyById(@PathVariable int id)
    {
        ShowServerDetailsDTO serverDetailsDTO = service.getServerInfoById(id);
        return serverDetailsDTO;
    }
    @GetMapping("/getAllCompanyInfo")
    public ResponseEntity<List<ShowServerDetailsDTO>> getAllCompanyInfo()
    {
        List<ShowServerDetailsDTO> detailsDTO = service.getAllDetails();
        return ResponseEntity.ok(detailsDTO);
    }
    @GetMapping("/getInfoByCompanyName/{name}")
    public ServerDetailsDTO getInfoByName(@PathVariable String name)
    {
        ServerDetailsDTO getAllInfoByName = service.getServerInfoByCompanyName(name);
        return getAllInfoByName;
    }
    @DeleteMapping("/deleteById/{serverId}")
    public ShowServerDetailsDTO deleteCompany(@PathVariable int serverId)
    {
        ShowServerDetailsDTO detailsDTO = service.deleteServerInfoById(serverId);
        return detailsDTO;
    }
    @DeleteMapping("/deleteAllInfo")
    public String deleteAllCompanyInfo()
    {
        service.deleteAllDetails();
        return "All users Innfo Deleted SuccessFully";
    }

}
