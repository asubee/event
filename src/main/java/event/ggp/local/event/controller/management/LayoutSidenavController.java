package event.ggp.local.event.controller.management;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LayoutSidenavController {
    
    @RequestMapping("/management/layout-sidenav")
    public String show_Management_Dashboard(Model model){
        model.addAttribute("mapping", "/management/layout-sidenav");
        return "/management/layout-sidenav-light.html";
    }
}
