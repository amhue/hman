package com.amhue.hman.Repositories;

import com.amhue.hman.Entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}
