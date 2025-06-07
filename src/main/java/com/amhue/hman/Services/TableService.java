package com.amhue.hman.Services;

import com.amhue.hman.Entities.Table;
import com.amhue.hman.Repositories.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public void addTable(Table table) {
        tableRepository.save(table);
    }

    public List<Table> getTables() {
        return tableRepository.findAll();
    }
}
