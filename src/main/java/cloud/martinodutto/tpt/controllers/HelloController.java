package cloud.martinodutto.tpt.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String greetMe() {
        return "Hello, Tennis Personal Tracker here!";
    }
}
