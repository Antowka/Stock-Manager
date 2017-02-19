package ru.antowka.stock.application.mapper;

/**
 * Created by anton on 2/16/17.
 */
public interface Mapper<E, R> {

    R toRepresentation(E entity);
    E toEntity(R command);
}
