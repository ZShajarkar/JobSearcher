package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface DtoToModelMapper<D, M> {
    M toModel(D dto);

    default List<M> toModel(List<D> dtoList) {
        List<M> models = new ArrayList<>(dtoList.size());
        for (D dto : dtoList) {
            models.add(toModel(dto));
        }
        return models;
    }

    D toDto(M vm);

    default List<D> toDto(List<M> models) {
        List<D> dtoList = new ArrayList<>(models.size());
        for (M vm : models) {
            dtoList.add(toDto(vm));
        }
        return dtoList;
    }
}

