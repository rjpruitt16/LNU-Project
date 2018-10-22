package com.pkg.tables;

import lombok.Data;

import java.util.Date;

@Data
public class DevTraining implements TableRow {
    private String course;
    private String startDate;
    private String instructor;
    private int capacity;
    private long id;

    public DevTraining(String course, String startDate, String instructor,
                       int capacity, long id) {
        this.course = course;
        this.startDate = startDate;
        this.instructor = instructor;
        this.capacity = capacity;
        this.id = id;
    }

}
