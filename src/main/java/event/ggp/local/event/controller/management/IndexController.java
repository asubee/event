package event.ggp.local.event.controller.management;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
public class IndexController {

    @RequestMapping("/management/index")
    public String show_Management_Index(Model model){
        model.addAttribute("mapping", "/management/index");
        return "/management/index.html";
    }
    
}
