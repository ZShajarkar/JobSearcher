package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.Collection;

@Mapper
public interface ModelToDtoMapper<D, M> {
    D toDto(M vm);

    default Collection<D> toDto(Collection<M> models) {
        Collection<D> dtoList = new ArrayList<>(models.size());
        for (M vm : models) {
            dtoList.add(toDto(vm));
        }
        return dtoList;
    }
}
