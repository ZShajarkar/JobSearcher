package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.Collection;

@Mapper
public interface DtoToModelMapper<D, M> {
    M toModel(D dto);

    default Collection<M> toModel(Collection<D> dtoList) {
        Collection<M> models = new ArrayList<>(dtoList.size());
        for (D dto : dtoList) {
            models.add(toModel(dto));
        }
        return models;
    }
}
