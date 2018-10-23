package com.pkg.service;

import com.pkg.tables.DevTraining;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class DBService {
    String createTableQuery = ("CREATE TABLE DEV_TRAINING( " +
            "course VARCHAR(100), " +
            "startDate VARCHAR(100), " +
            "instructor VARCHAR(100), " +
            "capacity number(3)," +
            " id SERIAL)");
    ;

    private final String DB_NAME = getDBName(getCreateTableQuery());
    private final String DB_COLUMNS = getDBColumnsString(getCreateTableQuery());
    private final String DB_COLUMNS_INSERT = getDBColumnsInsertString(getCreateTableQuery());
    private final RowCallbackHandler ROW_TO_ENTITY_FUNCTION = getRowToEntityFunction();
    @Autowired
    JdbcTemplate template;


    public abstract String getCreateTableQuery();
    public abstract RowCallbackHandler getRowToEntityFunction();

    private String getDBName(String createTableQuery) {
        String dbString = createTableQuery.split(" ")[2];
        return dbString.substring(0, dbString.length() - 1);
    }

    private List<String> getColumnNames(String createTableQuery) {
        String parenthesis = createTableQuery.substring(createTableQuery.indexOf("("));
        String[] words = parenthesis.split(" ");
        ArrayList<String> columnNames = new ArrayList<String>();
        for (int i = 1; i < words.length - 1; i += 2) {
            System.out.println(words[i]);
            columnNames.add(words[i]);
        }
        return columnNames;
    }

    private String makeParenthesisString(List<String> columnNames) {
        String parenthesisString = "(";
        for (int i = 0; i < columnNames.size() - 1; i++) {
            parenthesisString = parenthesisString + columnNames.get(i) + ", ";
        }
        parenthesisString = parenthesisString + columnNames.get(columnNames.size() - 1) + ")";
        return parenthesisString;
    }

    private String getDBColumnsString(String createTableQuery) {
        List<String> columnNames = getColumnNames(createTableQuery);
        return makeParenthesisString(columnNames);
    }

    private String makeInsertString(List<String> columnNames) {
        String insertString = "";
        for (int i = 0; i < columnNames.size() - 1; i++) {
            insertString = insertString + columnNames.get(i) + " = ?, ";
        }
        insertString = insertString + columnNames.get(columnNames.size() - 1) + " = ?";
        return insertString;

    }

    private String getDBColumnsInsertString(String createTableQuery) {
        List<String> columnNames = getColumnNames(createTableQuery);
        return makeInsertString(columnNames);
    }

    private boolean DBExist() {
        try {
            template.execute("select 1 from " + DB_NAME);
        }
        catch (Exception x) {
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
        template.execute("DROP TABLE " + DB_NAME + " IF EXISTS");
    }

    public void configure() {
        log.info("Database Initialized");
        if (!DBExist()) {
            createDB();
        }
    }

    public void create(List<Object[]> args) {
        dropTable();

        if (!DBExist()) {
            createDB();
        }

        template.batchUpdate("INSERT INTO " + DB_NAME + " " + DB_COLUMNS + " "
                "VALUES (?,?,?,?,?)", args);
    }

    public List<Object> read(int id) {
        return template.query("select * from " + DB_NAME + " where id = ?",
                new Object[] {id}, ROW_TO_ENTITY_FUNCTION);
    }


    public boolean update(int id, List<Object> args) {
        args.add(id);
        args.forEach(arg -> log.info("arg: {}", arg));
        return template.update("UPDATE " + DB_NAME + " SET " + DB_COLUMNS_INSERT +
                " WHERE id = ?", args) > 0;
    }

    public boolean delete(int id) {
        return template.update("DELETE from " + DB_NAME + " where id = ?", id) > 0;
    }


}

