package event.ggp.local.event.controller.management;

import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import event.ggp.local.event.service.IntializeService;
import event.ggp.local.event.service.Register;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class InitializeController {

    public final IntializeService initializeService;
    public final Register register;

    @RequestMapping(value="/management/initialize")
    public String show_Management_initialize(Model model){
        
        try{
            // UserIDテーブルがなければ作成する
            ArrayList<String> initialize_msg = initializeService.createTableUserId();

            model.addAttribute("initialize_msg", initialize_msg);
            return "management/initialize";
        }catch(Exception e){
            log.error("想定外のエラーが発生しました：" + e.getMessage());
            return "management/initialize";
        }
    }
}
