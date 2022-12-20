package com.lab4.controller;

import com.lab4.dto.CalculatedEntity;
import com.lab4.dto.RequestDTO;
import com.lab4.dto.ResponseDTO;
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

    private final StorageService storageService;

    private final CalculationService calculationService;

    @GetMapping("/api/lab4/{id}")
    public ResponseEntity<?> findResultById(@PathVariable Integer id) {

        ResponseDTO response = storageService.getResponse(id);
        return ResponseEntity.ok(Objects.requireNonNullElse(response, "Not found :("));
    }

    @PostMapping("/api/lab4")
    public ResponseEntity<?> calculateResponse(@RequestBody RequestDTO requestDTO,
                                               @RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        CalculatedEntity calculatedEntity = calculationService.calculate(requestDTO, locale);
        ResponseDTO response = storageService.putCalculatedEntityAndGetResponse(calculatedEntity);
        storageService.saveFile(response);
        return ResponseEntity.ok(response);
    }

}
