package parking.hfq.com.smartparking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Created by hfq on 2020/3/18 21:55
 * @used to:
 * @return:
 */
@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "userInfo";
    }

}
