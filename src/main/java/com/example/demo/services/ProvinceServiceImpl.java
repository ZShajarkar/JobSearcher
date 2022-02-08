package com.example.demo.services;

import com.example.demo.enums.Province;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    public Map<String, Integer> getProvinces() {
        Map<String, Integer> provinces = new HashMap<>();
        for (Province province : Province.values()) {
            provinces.putIfAbsent(province.value(), province.id());
        }
        return provinces;
    }

    public String getNameOfProvince(int id) {
        return Arrays.stream(Province.values()).filter(item -> item.id() == id).toList().get(0).value();

    }
}
