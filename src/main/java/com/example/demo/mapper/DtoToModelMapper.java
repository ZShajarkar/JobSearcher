package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public interface DtoToModelMapper<D, M> {
    M toModel(D dto);

    default Collection<M> toModel(Collection<D> dtoList) {
        Collection<M> models = new ArrayList<>(dtoList.size());
        for (D dto : dtoList) {
            models.add(toModel(dto));
        }
        return models;
    }

    D toDto(M vm);

    default Collection<D> toDto(Collection<M> models) {
        Collection<D> dtoList = new ArrayList<>(models.size());
        for (M vm : models) {
            dtoList.add(toDto(vm));
        }
        return dtoList;
    }
}

