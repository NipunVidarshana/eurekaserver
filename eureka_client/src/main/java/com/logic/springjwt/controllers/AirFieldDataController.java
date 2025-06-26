package com.logic.springjwt.controllers;

import com.logic.springjwt.DTO.AirFieldDataDto;
import com.logic.springjwt.models.AirFieldDataModel;
import com.logic.springjwt.services.AirFieldDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/airfield")
public class AirFieldDataController {

    @Autowired
    private AirFieldDataService dataService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody AirFieldDataDto dto) {
        try {
            AirFieldDataModel savedData = dataService.saveData(dto);
            return ResponseEntity.ok(savedData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save data: " + e.getMessage());
        }
    }

    @PostMapping("/edit/{sinNumber}")
    public ResponseEntity<?> edit(@PathVariable long sinNumber,@Valid @RequestBody AirFieldDataDto dto) {
        try {
            AirFieldDataModel updatedData = dataService.editData(sinNumber, dto);
            return ResponseEntity.ok(updatedData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to edit data: " + e.getMessage());
        }
    }

    @GetMapping("/{sinNumber}")
    public ResponseEntity<?> getBySinNumber(@PathVariable long sinNumber) {
        try {
            AirFieldDataModel data = dataService.getBySinNumber(sinNumber);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Data not found: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            List<AirFieldDataModel> allData = dataService.getAll();
            return ResponseEntity.ok(allData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to fetch all data.");
        }
    }

//    @DeleteMapping("/delete/{sinNumber}")
//    public ResponseEntity<?> delete(@PathVariable long sinNumber) {
//        try {
//           AirFieldDataModel deleteddata= dataService.deleteBySinNumber(sinNumber);
//            return ResponseEntity.status(200).body(deleteddata);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Failed to delete data: " + e.getMessage());
//        }
//    }
}
