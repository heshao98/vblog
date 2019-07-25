package com.ddm.vblog.dto.tag;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: shaohua
 * @Date: 2019/7/25 20:05
 * @Description:
 */
@Data
public class TagDTO {

    /**
     * 主键ID
     */
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
     * 所关联的文章数
     */
    private Integer articleCount;

}
