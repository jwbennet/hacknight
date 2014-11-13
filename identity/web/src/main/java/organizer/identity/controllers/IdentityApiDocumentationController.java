package organizer.identity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping( "/api-docs" )
public class IdentityApiDocumentationController {

    @RequestMapping( "" )
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("docs");
        return model;
    }
}
