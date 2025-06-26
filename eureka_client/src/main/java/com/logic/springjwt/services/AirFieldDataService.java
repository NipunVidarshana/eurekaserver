package com.logic.springjwt.services;

import com.logic.springjwt.DTO.AirFieldDataDto;
import com.logic.springjwt.ExceptionHandler.ExceptionDataAlreadyExists;
import com.logic.springjwt.models.AirFieldDataModel;
import com.logic.springjwt.repository.AirFieldDataRepositary;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirFieldDataService {

    @Autowired
    private TransactionLogServices loginLogServices;

    @Autowired
    private AirFieldDataRepositary dataModelRepositary;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("Current User: " + authentication.getName());
            return authentication.getName();
        }
        return null;
    }

    public AirFieldDataModel saveData(AirFieldDataDto dto) {
        try {
            validateAirFieldData(dto);
            if (dto.getSinNumber() != null && dataModelRepositary.existsById(dto.getSinNumber())) {
                throw new ExceptionDataAlreadyExists("SIN number " + dto.getSinNumber() + " is already taken.");
            }
            AirFieldDataModel entity = new AirFieldDataModel();
            BeanUtils.copyProperties(dto, entity);
            loginLogServices.TransactionLog("Air_Field_Runway_Data", "save airfield " + entity.getSinNumber(), getCurrentUsername(), String.valueOf(entity.getSinNumber()));
            return dataModelRepositary.save(entity);
        }catch (ExceptionDataAlreadyExists e) {
            throw e; // Re-throw custom exception for specific handling
        } catch (Exception e) {
            throw new RuntimeException("Failed to save data. Please check the input and try again.");
        }
    }

    public AirFieldDataModel editData(long sinNumber, AirFieldDataDto dto) {
        try {
            validateAirFieldData(dto);
            AirFieldDataModel entity = dataModelRepositary.findById(sinNumber)
                    .orElseThrow(() -> new RuntimeException("AirFieldData not found with sinNumber: " + sinNumber));
            BeanUtils.copyProperties(dto, entity, "sinNumber");
            loginLogServices.TransactionLog("Air_Field_Runway_Data", "edit  airfield " + entity.getSinNumber(), getCurrentUsername(), String.valueOf(entity.getSinNumber()));
            return dataModelRepositary.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update data for sinNumber: " + sinNumber);
        }
    }

    public AirFieldDataModel getBySinNumber(long sinNumber) {
        try {
            return dataModelRepositary.findById(sinNumber)
                    .orElseThrow(() -> new RuntimeException("AirFieldData not found with sinNumber: " + sinNumber));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving data for sinNumber: " + sinNumber);
        }
    }

    public List<AirFieldDataModel> getAll() {
        try {
            return (List<AirFieldDataModel>) dataModelRepositary.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve data list.");
        }
    }

    public AirFieldDataModel deleteBySinNumber(long sinNumber) {
        try {
            if (!dataModelRepositary.existsById(sinNumber)) {
                throw new RuntimeException("AirFieldData not found with sinNumber: " + sinNumber);
            }
            AirFieldDataModel deleteData = dataModelRepositary.findById(sinNumber)
                    .orElseThrow(() -> new RuntimeException("AirFieldData not found with sinNumber: " + sinNumber));
            dataModelRepositary.deleteById(sinNumber);
            loginLogServices.TransactionLog("Air_Field_Runway_Data", "delete airfield " + deleteData.getSinNumber(), getCurrentUsername(), String.valueOf(deleteData.getSinNumber()));


            return deleteData;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete data for sinNumber: " + sinNumber);
        }
    }


    public void validateAirFieldData(AirFieldDataDto dto) {

        String assetCode = dto.getAssetCode();


        // Validate aircraft/freight data for special asset types
        if ("Helipad".equalsIgnoreCase(assetCode) || "Waterdome".equalsIgnoreCase(assetCode)) {
            if (dto.getAircraftCount() == null || dto.getPassengerCount() == null || dto.getFreightMT() == null) {
                throw new IllegalArgumentException("aircraftCount, passengerCount, and freightMT must be filled for Helipad or Waterdome.");
            }
        } else {
            // Optional: nullify irrelevant values
            dto.setAircraftCount(null);
            dto.setPassengerCount(null);
            dto.setFreightMT(null);
        }





    }
}
