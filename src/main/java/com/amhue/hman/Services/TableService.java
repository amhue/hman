package com.amhue.hman.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.amhue.hman.Entities.Table;
import com.amhue.hman.Repositories.TableRepository;

import org.springframework.stereotype.Service;

@Service
public class TableService {
    private final TableRepository tableRepository;
    private final TableBookingService tableBookingService;

    public TableService(TableRepository tableRepository,
                        TableBookingService tableBookingService) {
        this.tableRepository = tableRepository;
        this.tableBookingService = tableBookingService;
    }

    public void addTable(Table table) { tableRepository.save(table); }

    public List<Table> getTables() { return tableRepository.findAll(); }

    public List<Table> searchTables(LocalDateTime start, LocalDateTime end,
                                    Integer capacity) {
        return tableRepository.findAll()
            .stream()
            .filter(table -> tableBookingService.isAvailable(table, start, end))
            .filter(table -> table.getCapacity() == capacity)
            .collect(Collectors.toList());
    }
}
