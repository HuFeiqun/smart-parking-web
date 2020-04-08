package parking.hfq.com.smartparking.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import parking.hfq.com.smartparking.model.Order;

import java.util.List;

/**
 * @Created by hfq on 2020/3/26 12:18
 * @used to:
 * @return:
 */
@Mapper
public interface OrderMapper {
//    2018-07-24
    @Select("select * from `order` where startDate=#{startDate} order by startHour limit #{offset},#{size}")
    List<Order> listOneDayOrders(String startDate,Integer offset,Integer size);

    @Select("select * from `order` limit 10")
    List<Order> list();

    @Select("select count(1) from `order` where startDate=#{startDate}")
    int countOneDayOrders(String startDate);



}
