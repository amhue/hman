package com.amhue.hman.Services;

import com.amhue.hman.Entities.Bill;
import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        List<Bill> bills = new ArrayList<>();
        bills.add(bill);
        booking.setBills(bills);

        billRepository.save(bill);
    }
}
