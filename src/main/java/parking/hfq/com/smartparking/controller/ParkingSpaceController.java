package parking.hfq.com.smartparking.controller;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import parking.hfq.com.smartparking.dto.ParkSpaceDTO;
import parking.hfq.com.smartparking.enums.ParkingSpaceStateEnum;
import parking.hfq.com.smartparking.mapper.ParkingSpaceMapper;
import parking.hfq.com.smartparking.model.ParkingSpace;
import parking.hfq.com.smartparking.model.ParkingSpaceExample;

import java.util.ArrayList;
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


    /**
     * 查询某停车场最近5条进/出的数据
     * @param parkinglot_id
     * @return
     */
    @ResponseBody
    @PostMapping("/parkinglot/{parkinglot_id}/space/recent")
    private Object selectRecentSpaceInfo(@PathVariable(name = "parkinglot_id") Integer parkinglot_id){
        ParkingSpaceExample example = new ParkingSpaceExample();
        example.createCriteria()
                    .andInUseEqualTo(ParkingSpaceStateEnum.IN_USE.getState())
                    .andParkinglotIdEqualTo(parkinglot_id);
        example.setOrderByClause("park_time desc");
        List<ParkingSpace> parkingSpaces = parkingSpaceMapper.selectByExampleWithRowbounds(example,new RowBounds(0,5)); //查询最近五条

        ParkingSpaceExample example2 = new ParkingSpaceExample();
        example2.createCriteria()
                .andInUseEqualTo(ParkingSpaceStateEnum.NOT_IN_USE.getState())
                .andParkinglotIdEqualTo(parkinglot_id);
        example2.setOrderByClause("park_time desc");
        List<ParkingSpace> parkingSpaces2 = parkingSpaceMapper.selectByExampleWithRowbounds(example2, new RowBounds(0, 5));//查询最近5条车辆离开记录
        parkingSpaces.addAll(parkingSpaces2);
        return parkingSpaces;
    }

    /**
     * 车辆进入
     * {
     *     id:"3",
     *     parkinlot_id:"1094",
     *     car_id:"浙A6666"
     * }
     */
    @PostMapping("/parkinglot/parkIn")
    public void parkIn(@RequestBody ParkSpaceDTO parkSpaceDTO) throws Exception {
//    public void parkIn(@RequestParam(name = "id",required = true) Integer id,
//                       @RequestParam(name = "parkinlot_id",required = true) Integer parkinlot_id,
//                       @RequestParam(name = "car_id",required = true) String car_id
//                       ) throws Exception {
//        ParkSpaceDTO ParkSpaceDTO = new ParkSpaceDTO();
//        ParkSpaceDTO.setId(id);
//        ParkSpaceDTO.setParkinlot_id(parkinlot_id);
//        ParkSpaceDTO.setCar_id(car_id);

        ParkingSpaceExample example = new ParkingSpaceExample();
        example.createCriteria()
                .andParkinglotIdEqualTo(parkSpaceDTO.getParkinlot_id())
                .andIdEqualTo(parkSpaceDTO.getId());
        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        parkingSpaces = parkingSpaceMapper.selectByExample(example);
        if(parkingSpaces.size()<1){
            System.out.println("停车场:"+parkSpaceDTO.getParkinlot_id());
            System.out.println("停车位:"+parkSpaceDTO.getId());

            throw new Exception("不存在该停车场-停车位组合");
        }
        ParkingSpace parkingSpace = parkingSpaces.get(0);
        if(parkingSpace.getInUse() == ParkingSpaceStateEnum.IN_USE.getState()){
            throw new Exception("该停车位已经有车了！");
        }
        ParkingSpace updateSpace = new ParkingSpace();
        updateSpace.setInUse(ParkingSpaceStateEnum.IN_USE.getState());
        updateSpace.setCarId(parkSpaceDTO.getCar_id());
        updateSpace.setParkTime(System.currentTimeMillis());  //设停车时间为当前系统时间
        parkingSpaceMapper.updateByExampleSelective(updateSpace,example); //更新数据库记录

    }


}
