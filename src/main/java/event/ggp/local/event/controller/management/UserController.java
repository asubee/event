package event.ggp.local.event.controller.management;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.modelmapper.ModelMapper;
import event.ggp.local.event.service.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import event.ggp.local.event.service.Register;
import event.ggp.local.event.service.UserList;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final Register register;
    private final UserList userList;

    @RequestMapping("/management/userlist")
    public String show_Management_UserList(Model model){
        List<UserDTO> result = userList.GetUserAll();
        model.addAttribute("userList", result);
        return "management/userlist.html";
    }

    @RequestMapping(value="/management/register", method=RequestMethod.GET)
    public String show_Management_Register(Model model){

        return "management/register.html";
    }

    @RequestMapping(value="/management/register", method=RequestMethod.POST)
    public String execute_Management_Register(@ModelAttribute("UserDTO") UserDTO form, Model model){
        // DTO間のデータコピー
        ModelMapper modelMapper = new ModelMapper();
        event.ggp.local.event.service.UserDTO user = modelMapper.map(form, event.ggp.local.event.service.UserDTO.class);
        // ユーザ登録　重複があったりエラーとなったら例外を発生させ、Register画面に戻す
        try{
            register.registUser(user);
        }catch(Exception e){
            log.error(e.getMessage());
            model.addAttribute("msg", e.getMessage());
            return "management/register.html";
        }

        model.addAttribute("msg", "ユーザ登録が完了しました。");
        return "forward:/management/userlist";
    }
    
    @RequestMapping(value="/management/usermodify", method=RequestMethod.GET)
    public String show_Management_UserModify(@ModelAttribute("UserDTO") UserDTO form, Model model){
        if(form.getUserid() == null){
            model.addAttribute("error", "不正な画面遷移です。");
            return "/management/error";
        }else{
            UserDTO user =  userList.GetUser(form.getUserid());
            model.addAttribute("user", user);
            return "/management/usermodify";
        }
    }

    @RequestMapping(value="/management/usermodify", params="update", method=RequestMethod.POST)
    public String execute_Management_UserModify(@ModelAttribute("UserDTO") UserDTO form, Model model){
       
       // ユーザ編集 エラーとなったら例外を発生させ、Register画面に戻す
        try{
            register.modifyUser(form);
        }catch(Exception e){
            log.error(e.getMessage());
            model.addAttribute("msg", e.getMessage());
            return "management/usermodify.html";
        }

        model.addAttribute("msg", "ユーザ編集が完了しました。");
        return "forward:/management/userlist";

    }
    
    //ユーザ情報を編集する（ヘッダバーからの遷移＝ログインユーザで編集）
    @RequestMapping(value="/management/usermodify", params="self", method=RequestMethod.POST)
    public String show_self_Management_UserModify(Model model){
        UserDTO user =  userList.GetUser(getUserId());
        model.addAttribute("user", user);
        return "/management/usermodify";
    }

    @RequestMapping(value="/management/userdelete", method=RequestMethod.GET)
    public String show_Management_UserDelete(@ModelAttribute("UserDTO") UserDTO form, Model model){
       UserDTO user =  userList.GetUser(form.getUserid());
       model.addAttribute("user", user);
       return "/management/userdelete";

    }

    @RequestMapping(value="/management/userdelete", params="delete", method=RequestMethod.POST)
    public String execute_Management_UserDelete(@ModelAttribute("UserDTO") UserDTO form, Model model){
         // ユーザ編集 エラーとなったら例外を発生させ、userdelete画面に戻す
        try{
            register.deleteUser(form);
        }catch(Exception e){
            log.error(e.getMessage());
            model.addAttribute("msg", e.getMessage());
            return "management/userdelete.html";
        }

        model.addAttribute("msg", "ユーザ削除が完了しました。");
        return "forward:/management/userlist";

    }


    // ログインユーザ情報からuseridを取得する
    private String getUserId() {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null) {
            // AuthenticationオブジェクトからUserDetailsオブジェクトを取得
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                // UserDetailsオブジェクトから、ユーザ名を取得
                return ( (UserDetails) principal ).getUsername();
            }
        }
        return null;
    }

}

