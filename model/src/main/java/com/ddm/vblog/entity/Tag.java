package com.ddm.vblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author DindDangMao
 * @since 2019-01-29
 */
@Data
@TableName("vblog_tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签名字
     */
    private String tagName;

    /**
     * 标签图标
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
