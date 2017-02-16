package ru.antowka.stock.application.mapper;

/**
 * Created by anton on 2/16/17.
 */
public interface Mapper<E, R, C> {

    R toRepresentation(E entity);
    E toEntity(C command);
}
