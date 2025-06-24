package com.amhue.hman.Repositories;

import java.util.List;

import com.amhue.hman.Entities.Bill;
import com.amhue.hman.Entities.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findAllByBookingUsers(Users user);
}
