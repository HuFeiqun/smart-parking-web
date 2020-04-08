package parking.hfq.com.smartparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import parking.hfq.com.smartparking.model.ParkOrder;
import parking.hfq.com.smartparking.service.OrderService;

import java.util.List;

/**
 * @Created by hfq on 2020/3/26 3:40
 * @used to:
 * @return:
 */
@Controller
public class VisualizeController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/visualize")
    public String visualize(
            @RequestParam(name = "startDate",defaultValue = "2018-07-23") String startDate,
            Model model,
            @RequestParam(name = "page",defaultValue = "1") int page,
            @RequestParam(name = "size",defaultValue = "12") int size
            ){
        int offset=(page-1)*size;
        int pageNum = (int) Math.ceil(orderService.countOneDayOrders(startDate)*1.0/size);
        List<ParkOrder> orders = orderService.listOneDayOrders(startDate,offset,size);
//        List<Order> orders = orderMapper.list();
//        System.out.println(startDate);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("startDate",startDate);
        model.addAttribute("orders",orders);
        return "visualize";
    }
}
