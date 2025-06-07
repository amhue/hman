package com.amhue.hman.Controllers;

import com.amhue.hman.Services.TableBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/table-booking")
public class TableBookingController {
    private final TableBookingService tableBookingService;

    public TableBookingController(TableBookingService tableBookingService) {
        this.tableBookingService = tableBookingService;
    }
}
