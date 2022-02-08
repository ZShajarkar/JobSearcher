package com.example.demo.services;

import java.util.Map;

public interface ProvinceService {
    Map<String, Integer> getProvinces();
    String getNameOfProvince(int id);
}
