package organizer.planner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/*")
public class PlannerController {

    @RequestMapping("")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @RequestMapping("/hello")
    public ModelAndView hello() {
        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        return model;
    }
}
