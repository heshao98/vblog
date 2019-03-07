package com.ddm.vblog.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Auther: shaohua
 * @Date: 2019/2/12 16:41
 * @Description: 自动填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = metaObject.getValue("createTime");
        if(createTime == null){
            setFieldValByName("createTime",LocalDateTime.now(),metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }
}
