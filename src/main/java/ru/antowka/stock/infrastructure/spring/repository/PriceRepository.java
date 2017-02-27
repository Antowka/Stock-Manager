package ru.antowka.stock.infrastructure.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antowka.stock.domain.model.price.Price;

/**
 * Repository for prices
 */
public interface PriceRepository extends JpaRepository<Price, Long> {

}
