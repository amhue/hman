package com.amhue.hman.Controllers;

import com.amhue.hman.Entities.Table;
import com.amhue.hman.Services.TableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping
    public void addTable(Table table) {
        tableService.addTable(table);
    }
}
