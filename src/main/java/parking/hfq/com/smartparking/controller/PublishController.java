package parking.hfq.com.smartparking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Created by hfq on 2020/3/19 13:57
 * @used to: 完成发布公告功能
 * @return:
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
