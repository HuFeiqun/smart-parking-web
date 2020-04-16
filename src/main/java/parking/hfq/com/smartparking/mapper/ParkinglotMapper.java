package parking.hfq.com.smartparking.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import parking.hfq.com.smartparking.model.Parkinglot;
import parking.hfq.com.smartparking.model.ParkinglotExample;

public interface ParkinglotMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    long countByExample(ParkinglotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    int deleteByExample(ParkinglotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    int insert(Parkinglot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    int insertSelective(Parkinglot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    List<Parkinglot> selectByExampleWithRowbounds(ParkinglotExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    List<Parkinglot> selectByExample(ParkinglotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    Parkinglot selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    int updateByExampleSelective(@Param("record") Parkinglot record, @Param("example") ParkinglotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    int updateByExample(@Param("record") Parkinglot record, @Param("example") ParkinglotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    int updateByPrimaryKeySelective(Parkinglot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table parkinglot
     *
     * @mbg.generated Thu Apr 16 21:21:39 CST 2020
     */
    int updateByPrimaryKey(Parkinglot record);
}