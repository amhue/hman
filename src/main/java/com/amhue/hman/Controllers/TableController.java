package com.amhue.hman.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.amhue.hman.Entities.Table;
import com.amhue.hman.Services.TableService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/table")
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public List<Table> getTables() {
        return tableService.getTables();
    }

    @GetMapping("/search")
    public List<Table> searchTables(@RequestParam LocalDateTime start,
                                    @RequestParam LocalDateTime end,
                                    @RequestParam Integer capacity) {
        return tableService.searchTables(start, end, capacity);
    }

    @PostMapping
    public void addTable(@RequestBody Table table) {
        tableService.addTable(table);
    }
}
