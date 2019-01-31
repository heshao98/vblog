package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-30
 */
@TableName("sys_oss")
public class Oss implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * URL地址
     */
    private String url;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

}
