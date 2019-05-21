package cn.idea.modules.menu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/menu")
@Controller
public class MenuController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.setViewName("menu/list");
        return model;
    }

}
