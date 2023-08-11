package com.bjpowernode.micrdataservice.mapper;

import com.bjpowernode.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    //统计注册人数
    int selectCountUser();

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据手机号码查询用户
     * @param phone
     * @return
     */
    User selectByPhone(@Param("phone") String phone);

    void insertReturnPrimaryKey(User user);
    /*登录*/
    User selectLogin(@Param("phone") String phone, @Param("loginPassword") String newPassword);
}