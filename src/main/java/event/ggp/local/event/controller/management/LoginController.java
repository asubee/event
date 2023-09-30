package event.ggp.local.event.controller.management;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import event.ggp.local.event.repositories.CreateTableMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    public final CreateTableMapper createTable;
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @RequestMapping("/management/login")
    public String show_Management_Login(){

        return "management/login.html";
    }

    @RequestMapping("/management/logout")
    public String show_Management_Logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response, Model model){
        this.logoutHandler.logout(request, response, authentication);
        model.addAttribute("logout","logout");
        return "/management/login.html";
    }
}
