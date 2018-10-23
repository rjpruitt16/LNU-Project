package com.project.LNUProject;

import com.project.LNUProject.config.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Slf4j
@SpringBootApplication
public class WebAppInitializer extends SpringBootServletInitializer {
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    public static void main(String[] args) {
        SpringApplication.run(WebAppInitializer.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebAppInitializer.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("onStartUp");

        // create the spring application context
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        // create the dispatcher servlet
        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(context);

        // register and configure the dispatcher servlet
        ServletRegistration.Dynamic registration =
                servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcherServlet);

        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
