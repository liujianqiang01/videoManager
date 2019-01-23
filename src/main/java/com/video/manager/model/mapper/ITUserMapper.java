package com.video.manager.model.mapper;

import com.video.manager.model.TUser;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface ITUserMapper extends Mapper<TUser> {

    @Update({"update t_user set apply_state = 2 where  user_id = #{userId} "})
    void updateApplyState(Integer userId);

}