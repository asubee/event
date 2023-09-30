package event.ggp.local.event.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class TopPageController {
    
    @RequestMapping("/")
    public String show_Management_Dashboard(Model model){
        return "toppage.html";
    }
}
