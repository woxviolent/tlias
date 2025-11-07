package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    List<Emp> list(EmpQueryParam empQueryParam);

//    @Select("select e.*, d.name as deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc ")
//    public List<Emp> list();


    @Select("select count(*) from emp")
    public Long count();

    @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id limit #{start}, #{pageSize}")
    public List<Emp> listByPage(Integer start , Integer pageSize);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 批量删除员工信息
     */
    void deleteByIds(List<Integer> ids);


    Emp getById(Integer id);

    void updateById(Emp emp);
    /**
     * 统计各个职位的员工人数
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 统计员工性别信息
     */
    @MapKey("name")
    List<Map> countEmpGenderData();
}