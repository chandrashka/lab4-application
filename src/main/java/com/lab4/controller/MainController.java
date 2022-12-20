package com.lab4.controller;

import com.lab4.dto.*;
import com.lab4.services.CalculationService;
import com.lab4.services.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class MainController {

    private final StorageService storage;

    private final CalculationService calculationService;

    @GetMapping("/api/lab4/{id}")
    public ResponseEntity<?> getTestResponse(@PathVariable String id) {
        if(storage != null) {
            ResponseDTO response = storage.getResponse(Integer.valueOf(id));
            return ResponseEntity.ok(Objects.requireNonNullElse(response, "Not found :("));
        }
        else {
            return ResponseEntity.ok("Storage is empty");
        }
    }

    @PostMapping("/api/lab4")
    public ResponseEntity<?> calculateResponse(@RequestBody RequestDTO requestDTO,
                                      @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CalculatedEntity calculatedEntity = calculationService
                .calculate(requestDTO.getLineDTO(),
                        requestDTO.getSurfaceDTO(),
                        locale);

        int id = storage.getCurrentId();
        ResponseDTO response = new ResponseDTO(calculatedEntity, id);
        storage.putResponse(response);

        return ResponseEntity.ok(response);
    }
}
