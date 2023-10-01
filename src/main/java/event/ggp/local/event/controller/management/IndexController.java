package event.ggp.local.event.controller.management;

import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

import event.ggp.local.event.config.EventProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
@Slf4j
public class IndexController {

    @Autowired
    private final EventProperty eventProperty = new EventProperty();

    @RequestMapping("/management/index")
    public String show_Management_Index(Model model){
        Map<String, String> eventProperty_init = eventProperty.getEvent();
        for (String key : eventProperty_init.keySet()) {
			log.info("key:"+key+ " value:"+eventProperty_init.get(key));
		}

        model.addAttribute("mapping", "/management/index");
        return "/management/index.html";
    }
    
}
