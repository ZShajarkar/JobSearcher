package com.example.demo.services;

import com.example.demo.enums.Province;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Override
    public List<String> getProvinces() {
        List<String> provinces = new ArrayList<>();
        for (Province province : Province.values()) {
            provinces.add(province.value());
        }
        return provinces;
    }
}
