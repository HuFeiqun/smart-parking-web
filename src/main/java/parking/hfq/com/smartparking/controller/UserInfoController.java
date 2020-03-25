package parking.hfq.com.smartparking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Created by hfq on 2020/3/19 15:04
 * @used to:
 * @return:
 */
@Controller
public class UserInfoController {
    @GetMapping("/userInfo")
    public String userInfo(){
        return "userInfo";
    }
}
