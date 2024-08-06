package com.w08e.common.core.domain;

import org.mapstruct.Mapper;


@Mapper
public interface GenericMapper<E, D> {
    D toDomain(E entity);
    E toEntity(D domain);
}