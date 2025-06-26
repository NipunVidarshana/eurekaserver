package com.logic.springjwt.controllers;

import com.logic.springjwt.DTO.InstituteSectorDTO;
import com.logic.springjwt.models.InstituteSectorModel;
import com.logic.springjwt.response.MessageResponse;
import com.logic.springjwt.services.InstitutionSectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/InstitutionName")
public class InstitutionSectorController {

    @Autowired
    InstitutionSectorService institutionSectorService;

    @PostMapping("/save")
    public ResponseEntity<MessageResponse> save(@RequestBody InstituteSectorDTO instituteName) {
        System.out.println("Saving Institute Name: " + instituteName.getName());
        institutionSectorService.save(instituteName);
        MessageResponse response = new MessageResponse("Institute Name Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InstituteSectorModel>> getAll() {

        return new ResponseEntity<>(institutionSectorService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long id) {
        institutionSectorService.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("Deleted successfully!"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody InstituteSectorDTO dto) {
        institutionSectorService.edit(id, dto);
    }
}