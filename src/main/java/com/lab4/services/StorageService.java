package com.lab4.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab4.dto.CalculatedEntity;
import com.lab4.dto.ResponseDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class StorageService {
    private final Map<Integer, ResponseDTO> storage = new HashMap<>();
    private int currentId;

    public ResponseDTO getResponse(Integer id) {
        return storage.get(id);
    }

    public ResponseDTO putCalculatedEntityAndGetResponse(CalculatedEntity entity) {
        ResponseDTO response = new ResponseDTO(entity, currentId);
        storage.put(currentId++, response);
        return response;
    }

    public void saveFile(ResponseDTO response) {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("results" + response.getId() + ".json");
        try {
            String json = mapper.writeValueAsString(response);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(json);
            bufferedWriter.write('\n');
            bufferedWriter.flush();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
