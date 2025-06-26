package com.logic.springjwt.services;

import com.logic.springjwt.DTO.FromToWhomDto;
import com.logic.springjwt.ExceptionHandler.ExceptionDataNotSaved;
import com.logic.springjwt.ExceptionHandler.ResourceNotFoundException;
import com.logic.springjwt.models.AirFieldDataModel;
import com.logic.springjwt.repository.AirFieldDataRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FromWhomToWhomService {

    @Autowired
    private AirFieldDataRepositary airFieldDataRepositary;

    @Autowired
    private TransactionLogServices loginLogServices;

    private AirFieldDataModel getAirFieldDataByIdOrThrow(Long sinNumber) {
        return airFieldDataRepositary.findById(sinNumber)
                .orElseThrow(() -> new ResourceNotFoundException("AirFieldData not found with sinNumber: " + sinNumber));
    }


    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("Current User: " + authentication.getName());
            return authentication.getName();
        }
        return null;
    }

    //save  from whom to whom data
    public AirFieldDataModel addFromToWhomData(Long sinNumber, FromToWhomDto dto) {

        try {
            
            validate(dto);
            loginLogServices.TransactionLog("Air_Field_Runway_Data", "save airfield  to whom from whome data " + sinNumber, getCurrentUsername(), String.valueOf(sinNumber));
            return getAirFieldDataModel(sinNumber, dto);
        } catch (Exception e) {
            throw new ExceptionDataNotSaved("Failed to save data. Please check the input and try again.");
        }

    }


    public AirFieldDataModel editFromToWhomData(Long sinNumber, FromToWhomDto dto) {
        try {
            // Validate input DTO
            validate(dto);
            loginLogServices.TransactionLog("Air_Field_Runway_Data", "update  airfield  to whom from whomgie data " + sinNumber, getCurrentUsername(), String.valueOf(sinNumber));
            // Retrieve and update
            return getAirFieldDataModel(sinNumber, dto);
        } catch (Exception e) {
            throw new ExceptionDataNotSaved("Failed to save data. Please check the input and try again.");
        }
    }

    private AirFieldDataModel getAirFieldDataModel(Long sinNumber, FromToWhomDto dto) {
        try {

            AirFieldDataModel model = getAirFieldDataByIdOrThrow(sinNumber);

            if (Objects.equals(model.getOwnership(), dto.getOwnership())) {
                model.setFromWhom(dto.getFromWhom());
                model.setToWhom(dto.getToWhom());
                model.setStartPeriod(dto.getStartPeriod());
                model.setEndPeriod(dto.getEndPeriod());
                model.setPaymentMethod(dto.getPaymentMethod());
                model.setIncomeOrPayment(dto.getIncomeOrPayment());

                model.setAmount(dto.getAmount());
                model.setStates(dto.getStates());

                return airFieldDataRepositary.save(model);
            } else {
                throw new ResourceNotFoundException("Ownership type not match found for sinNumber: " + sinNumber);
            }


        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Ownership type not match found for sinNumber: " + sinNumber);
        } catch (Exception e) {
            throw new ExceptionDataNotSaved("Failed to update data for sinNumber: " + sinNumber);
        }

    }

    public void validate(FromToWhomDto dto) {
        String ownershipType = dto.getOwnership();

        if ("Leased".equalsIgnoreCase(ownershipType) || "Rented".equalsIgnoreCase(ownershipType)) {
            dto.setIncomeOrPayment("payment");
            if (dto.getFromWhom() == null || dto.getFromWhom().trim().isEmpty()) {
                throw new IllegalArgumentException("fromWhom must be provided for leased or rented ownership types.");
            }
            if (dto.getToWhom() != null && !dto.getToWhom().trim().isEmpty()) {
                throw new IllegalArgumentException("toWhom must be null for leased or rented ownership types.");
            }
        }

        if ("Lease out".equalsIgnoreCase(ownershipType) || "Rent Out".equalsIgnoreCase(ownershipType)) {
            dto.setIncomeOrPayment("income");
            if (dto.getToWhom() == null || dto.getToWhom().trim().isEmpty()) {
                throw new IllegalArgumentException("toWhom must be provided for rent out or Lease Out ownership types.");
            }
            if (dto.getFromWhom() != null && !dto.getFromWhom().trim().isEmpty()) {
                throw new IllegalArgumentException("fromWhom must be null for rent out or Lease Out ownership types.");
            }
        }

        if (dto.getStartPeriod() != null && dto.getEndPeriod() != null &&
                dto.getStartPeriod().isAfter(dto.getEndPeriod())) {
            throw new IllegalArgumentException("Start period cannot be after end period.");
        }

        if ("own".equalsIgnoreCase(ownershipType)) {
            dto.setFromWhom(null);
            dto.setToWhom(null);
            dto.setStartPeriod(null);
            dto.setEndPeriod(null);
            dto.setPaymentMethod(null);
            dto.setIncomeOrPayment(null);
            dto.setAmount(null);
        }

        if ("own(Transfer in)".equalsIgnoreCase(ownershipType)) {
            if (dto.getFromWhom() == null || dto.getFromWhom().trim().isEmpty()) {
                throw new IllegalArgumentException("fromWhom must be provided for own(Transfer in) ownership types.");
            }
            if (dto.getToWhom() != null && !dto.getToWhom().trim().isEmpty()) {
                throw new IllegalArgumentException("toWhom must be null for own(Transfer in) ownership types.");
            }

            dto.setStartPeriod(null);
            dto.setEndPeriod(null);
            dto.setPaymentMethod(null);
            dto.setIncomeOrPayment(null);
            dto.setAmount(null);
        }

        if ("own(Transfer out)".equalsIgnoreCase(ownershipType)) {
            if (dto.getToWhom() == null || dto.getToWhom().trim().isEmpty()) {
                throw new IllegalArgumentException("toWhom must be provided for own(Transfer out) ownership types.");
            }
            if (dto.getFromWhom() != null && !dto.getFromWhom().trim().isEmpty()) {
                throw new IllegalArgumentException("fromWhom must be null for own(Transfer out) ownership types.");
            }

            dto.setStartPeriod(null);
            dto.setEndPeriod(null);
            dto.setPaymentMethod(null);
            dto.setIncomeOrPayment(null);
            dto.setAmount(null);
        }
    }
}
