package com.amhue.hman.Repositories;

import com.amhue.hman.Entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRepository extends JpaRepository<Table, Integer> {
    Optional<Table> findByTableNumber(Integer tableNumber);
}
