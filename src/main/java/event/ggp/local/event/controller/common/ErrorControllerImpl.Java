package event.ggp.local.event.controller.management;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/error")
public class ErrorControllerImpl implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }


    public ModelAndView handleError(HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView();
 
        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            return "common/404.html"
        }
        else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            return "common/403.html"
        }
        else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return "common/500.html"
        }
        else {
            modelAndView.setViewName("error");
            return "common/error.html"
        }
 
        return modelAndView;
    }
 
    
}