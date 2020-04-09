package parking.hfq.com.smartparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import parking.hfq.com.smartparking.mapper.ParkingSpaceMapper;
import parking.hfq.com.smartparking.model.ParkingSpace;
import parking.hfq.com.smartparking.model.ParkingSpaceExample;

import java.util.List;

/**
 * @Created by hfq on 2020/4/9 17:23
 * @used to:
 * @return:
 */
@Controller
public class ParkingSpaceController {
    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;

    //

    /**
     *
     * @param parkinglot_id 停车场ID
     * @param parking_space_id 车位id
     * @return
     * 如果参数parking_space_id 为空，查找所有停车场ID为parkinglot_id的所有车位使用情况
     * 如果参数parking_space_id非空，根据停车场id和车位id 查询一条记录。
     */
    @ResponseBody
    @PostMapping("/parkinglot/{parkinglot_id}/space")
    private Object selectAllSpace(@PathVariable(name = "parkinglot_id") Integer parkinglot_id,
                                  @RequestParam(name = "parking_space_id",required = false) Integer parking_space_id
                                  ){

//        System.out.println(parking_space_id);
        ParkingSpaceExample example = new ParkingSpaceExample();
        if(parking_space_id!=null){
            example.createCriteria()
                    .andParkinglotIdEqualTo(parkinglot_id)
                    .andIdEqualTo(parking_space_id);
        }
        else {
            example.createCriteria()
                    .andParkinglotIdEqualTo(parkinglot_id);
        }
        List<ParkingSpace> parkingSpaces = parkingSpaceMapper.selectByExample(example);
        return parkingSpaces;
    }




}
