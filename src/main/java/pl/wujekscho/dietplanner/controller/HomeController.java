package pl.wujekscho.dietplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {
    @GetMapping("")
    public String home() {
        return "hello2";
    }

    @GetMapping("/1")
    public String home1() {
        return "hello1";
    }
}
