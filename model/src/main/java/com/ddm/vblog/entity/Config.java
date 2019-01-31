package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 系统配置信息表
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-30
 */
@TableName("sys_config")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * key
     */
    private String paramKey;

    /**
     * value
     */
    private String paramValue;

    /**
     * 状态   0：隐藏   1：显示
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}
