package parking.hfq.com.smartparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import parking.hfq.com.smartparking.mapper.ParkinglotMapper;
import parking.hfq.com.smartparking.model.Parkinglot;
import parking.hfq.com.smartparking.model.ParkinglotExample;

import java.util.List;

/**
 * @Created by hfq on 2020/4/9 0:08
 * @used to:
 * @return:
 */
@Controller
public class ParkinglotController {
    @Autowired
    private ParkinglotMapper parkinglotMapper;

    @GetMapping("/parkinglot")
    public String parkinglot(Model model){
        ParkinglotExample example = new ParkinglotExample();
        List<Parkinglot> parkinglots = parkinglotMapper.selectByExample(example);
        model.addAttribute("parkinglots",parkinglots);
        return "parkinglot";
    }

    @GetMapping("/parkinglot/detail/{id}")
    public String parkinglotDetail(
            @PathVariable(name = "id") Integer id,
            Model model){
        Parkinglot parkinglot = parkinglotMapper.selectByPrimaryKey(id);
//        ParkinglotExample example = new ParkinglotExample();
//        List<Parkinglot> parkinglots = parkinglotMapper.selectByExample(example);
        model.addAttribute("parkinglot",parkinglot);
        return "parkinglotDetail";
    }

}
