package com.martinodutto.tpt.controllers;

import com.martinodutto.tpt.controllers.entities.ActivityForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewActivityController {

    private final static Logger LOGGER = LoggerFactory.getLogger(NewActivityController.class);

    @RequestMapping(method = RequestMethod.POST, value = "/activity/createActivity")
    public boolean createActivity(@RequestBody ActivityForm form) {

        LOGGER.info("Received {} as a form input from the frontend", form);
        return true;
    }
}
