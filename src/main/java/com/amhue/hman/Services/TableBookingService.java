package com.amhue.hman.Services;

import com.amhue.hman.Repositories.TableBookingRepository;
import org.springframework.stereotype.Service;

@Service
public class TableBookingService {
    private final TableBookingRepository tableBookingRepository;

    public TableBookingService(TableBookingRepository tableBookingRepository) {
        this.tableBookingRepository = tableBookingRepository;
    }
}
