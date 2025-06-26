package com.logic.springjwt.controllers;

import com.logic.springjwt.DTO.FromToWhomDto;
import com.logic.springjwt.models.AirFieldDataModel;
import com.logic.springjwt.services.FromWhomToWhomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/airfield/fromToWhom")
public class FromWhomToWhomController {

    @Autowired
    private FromWhomToWhomService fromWhomToWhomService;

    @PostMapping("/{sinNumber}")
    public ResponseEntity<AirFieldDataModel> addFromToWhom(
            @PathVariable Long sinNumber,
            @RequestBody FromToWhomDto dto) {
        AirFieldDataModel result = fromWhomToWhomService.addFromToWhomData(sinNumber, dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{sinNumber}")
    public ResponseEntity<AirFieldDataModel> editFromToWhom(
            @PathVariable Long sinNumber,
            @RequestBody FromToWhomDto dto) {
        AirFieldDataModel result = fromWhomToWhomService.editFromToWhomData(sinNumber, dto);
        return ResponseEntity.ok(result);
    }
}