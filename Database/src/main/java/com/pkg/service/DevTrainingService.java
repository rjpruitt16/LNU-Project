package com.pkg.service;

import com.pkg.tables.DevTraining;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DevTrainingService implements CRUDService {
    private final String TABLE_NAME = "DEV_TRAINING";

    @Autowired
    JdbcTemplate template;

    public void DevTrainingService() {
        if (!DBExist()) {
            createDB();
        }
        log.info("Database Initialized");
    }

    @Override
    public void create(List<Object[]> args) {
        dropTable();
        
        if (!DBExist()) {
            createDB();
        }

        template.batchUpdate("INSERT INTO " + TABLE_NAME + " (course, startDate, instructor, capacity, id) " +
                "VALUES (?,?,?,?,?)", args);
    }

    @Override
    public List<Object> read(int id) {
        return template.query("select * from " + TABLE_NAME + " where id = ?",
                new Object[] {id},
                (rs, rowNum) -> new DevTraining(rs.getString("course"),
                        rs.getString("startDate"), rs.getString("instructer"),
                        rs.getInt("capacity"), rs.getLong("id")));
    }

    @Override
    public boolean update(int id, List<Object> args) {
        return template.update("DELETE from " + TABLE_NAME + " where id = ?",
                               args) > 0;
    }

    @Override
    public boolean delete(int id) {
        return template.update("DELETE from " + TABLE_NAME + " where id = ?", id) > 0;
    }

    private boolean DBExist() {
        try {
            template.execute("select 1 from " + TABLE_NAME);
        }
        catch ( Exception x) {
            return false;
        }
        return true;
    }

    private void createDB() {
        template.execute("CREATE TABLE DEV_TRAINING( " +
                "course VARCHAR(100), startDate VARCHAR(100), " +
                "instructor VARCHAR(100), capacity number(3), id SERIAL)");
    }

    private void dropTable() {
        template.execute("DROP TABLE " + TABLE_NAME + " IF EXISTS");
    }
}
