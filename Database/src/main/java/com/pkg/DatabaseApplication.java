package com.pkg;

import com.pkg.service.DevTrainingService;
import com.pkg.tables.DevTraining;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class DatabaseApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);

    }

    @Autowired
    DevTrainingService devService;

    @Override
    public void run(String... strings) throws Exception {
        List<Object[]> training = new ArrayList<>();
        Object[] object = {"java", "Today", "Sajid", 20, 1};
        training.add(object);
        devService.create(training);
        devService.read(1).forEach(dev -> log.info(dev.toString()));
        List<Object> newObject = new ArrayList<Object>();
        newObject.add("Python");
        newObject.add("Tomorrow");
        newObject.add("Hussain");
        newObject.add(30);
        devService.update(1, newObject);
        devService.read(1).forEach(dev -> log.info(dev.toString()));
        log.info("Deletes properly {}", devService.delete(1));
    }
}
