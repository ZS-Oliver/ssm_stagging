package cn.idea.modules.base.web;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
@RequestMapping("/test")
public class TestStaggingController {

    @RequestMapping(value = "/view", method = RequestMethod.GET)
//    public String index(){
//        return "jsp/index";
//    }
    public ModelAndView index(ModelAndView model) {
        model.setViewName("jsp/index");
        return model;
    }

    @GetMapping("content/{str}")
    public String showContent(@PathVariable String str) {
        log.info("str = {}", str);
        return str;
    }


}
