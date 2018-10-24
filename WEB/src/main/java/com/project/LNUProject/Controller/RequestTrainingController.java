package com.project.LNUProject.Controller;

import com.project.LNUProject.utils.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class RequestTrainingController {

    final String NAME = "TrainingRequest";

    // == handler methods ==
    // http://localhost:8080/WEB/TrainingRequest
    @GetMapping(NAME)
    public String TableView() {
        log.info("Training VIEW being called");
        return NAME;
    }

    @GetMapping(Mappings.HOME + NAME + Mappings.EDIT)
    public String EditTableView() {
        return NAME + Mappings.EDIT;
    }

    @GetMapping(Mappings.HOME + NAME + Mappings.DELETE)
    public String DeleteTableView() {
        return NAME + Mappings.DELETE;

    }

    @GetMapping(Mappings.HOME + NAME + Mappings.ADD)
    public String AddTableView() {
        return NAME + Mappings.ADD;
    }
}

