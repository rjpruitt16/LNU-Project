package com.project.LNUProject.Controller;

import com.project.LNUProject.utils.Mappings;
import com.project.LNUProject.utils.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class DevTrainingController implements CRUDController{
    @Override
    @GetMapping(Mappings.DEV_TRAINING_TABLE)
    public String table() {
        return ViewNames.DEV_TRAINING_TABLE;
    }

    @Override
    public String edit() {
        return "";
    }

    @Override
    public String delete() {
        return "";
    }

    @GetMapping("/")
    public String home() {
        log.info("Home template map");
        return ViewNames.HOME;
    }
}
