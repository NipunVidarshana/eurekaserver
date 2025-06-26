package com.logic.springjwt.services;


import com.logic.springjwt.DTO.InstituteSectorDTO;
import com.logic.springjwt.models.InstituteSectorModel;
import com.logic.springjwt.repository.InstitutionSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionSectorService {

    @Autowired
    private InstitutionSectorRepository institutionSectorRepository;
    @Autowired
    private TransactionLogServices loginLogServices;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("Current User: " + authentication.getName());
            return authentication.getName();
        }
        return null;
    }

    public void save(InstituteSectorDTO instituteName) {
        InstituteSectorModel institute = new InstituteSectorModel();
        institute.setName(instituteName.getName());

        InstituteSectorModel saved = institutionSectorRepository.save(institute);
        loginLogServices.TransactionLog("institute_name", "save institute name " + saved.getId(), getCurrentUsername(), String.valueOf(saved.getId()));
        System.out.println("Saved with ID: " + saved.getId());

    }

    public List<InstituteSectorModel> getAll() {
        return (List<InstituteSectorModel>) institutionSectorRepository.findAll();
    }

    public void deleteById(Long id) {
        institutionSectorRepository.deleteById(id);
    }

    public void edit(Long id, InstituteSectorDTO instituteSectorDTO) {
        InstituteSectorModel institute = institutionSectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institute not found with id: " + id));
        institute.setName(instituteSectorDTO.getName());
        institutionSectorRepository.save(institute);
    }
}