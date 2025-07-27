package com.amhue.hman.Services;

import java.util.ArrayList;
import java.util.List;

import com.amhue.hman.Entities.Bill;
import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.BillRepository;

import org.springframework.stereotype.Service;

@Service
public class BillService {
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public void addBill(String billType, Integer amount, Booking booking) {
        Bill bill = new Bill();
        bill.setBillType(billType);
        bill.setAmount(amount);
        bill.setBooking(booking);

        List<Bill> bills = booking.getBills();
        if (bills == null) {
            bills = new ArrayList<>();
        }
        bills.add(bill);
        System.out.println(bills);
        booking.setBills(bills);

        billRepository.save(bill);
    }

    public List<Bill> getBills() { return billRepository.findAll(); }

    public List<Bill> getBills(Users user) {
        return billRepository.findAllByBookingUsers(user);
    }
}
