package com.lab4.services;

import com.lab4.dto.CalculatedEntity;
import com.lab4.dto.ResponseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
@NoArgsConstructor
public class StorageService {
    private int currentId;
    private Map<Integer, ResponseDTO> storage = new HashMap<>();

    public ResponseDTO getResponse(Integer id) {
        if(storage.containsKey(id)){
            return storage.get(id);
        }
        return null;
    }

    public int putResponse(ResponseDTO response){
        storage.put(currentId, response);
        return currentId++;
    }
}
