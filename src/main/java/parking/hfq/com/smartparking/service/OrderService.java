package parking.hfq.com.smartparking.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.hfq.com.smartparking.mapper.ParkOrderMapper;
import parking.hfq.com.smartparking.model.ParkOrder;
import parking.hfq.com.smartparking.model.ParkOrderExample;

import java.util.List;

/**
 * @Created by hfq on 2020/4/8 14:26
 * @used to: 订单管理
 * @return:
 */
@Service
public class OrderService {
    @Autowired
    private ParkOrderMapper parkOrderMapper;

    public int countOneDayOrders(String startDate) {
        final ParkOrderExample example = new ParkOrderExample();
        example.createCriteria()
                .andStartdateEqualTo(startDate);
        return (int) parkOrderMapper.countByExample(example);
    }

    public List<ParkOrder> listOneDayOrders(String startDate, int offset, int size) {
        ParkOrderExample example = new ParkOrderExample();
        example.createCriteria()
                .andStartdateEqualTo(startDate);
        List<ParkOrder> orders = parkOrderMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        return orders;
    }


}
