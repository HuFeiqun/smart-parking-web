package parking.hfq.com.smartparking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Created by hfq on 2020/3/26 0:53
 * @used to:
 * @return:
 */
@Controller
public class ParkingDemandController {

    @GetMapping("/parkingDemand")
    public String parkingDemand(){
        return "/parkingDemand";
    }
}
