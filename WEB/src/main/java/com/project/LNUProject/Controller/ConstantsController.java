package com.project.LNUProject.Controller;

import com.project.LNUProject.utils.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ConstantsController {
    // == handler methods ==
    // http://localhost:8080/WEB/
    @GetMapping("/")
    public String home() {
        log.info("Home template map");
        return ViewNames.HOME;
    }
}
