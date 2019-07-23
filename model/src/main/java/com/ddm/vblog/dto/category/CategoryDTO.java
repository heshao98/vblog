package com.ddm.vblog.dto.category;

import lombok.Data;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shaohua
 * @Date: 2019/7/22 20:53
 * @Description: 商品分类显示的数据传输类
 */
@Data
public class CategoryDTO {


    public static void main(String[] args) {
//        String a = "heshaohua";
//        System.out.println(a.hashCode() ^ a.hashCode());
//        Map<String,String> aa = new HashMap();
//        aa.put("aa","aa");
        System.out.println(4<<1);
    }

    /**
     * 文章分类id
     */
    private Integer id;

    /**
     * 文章分类名称
     */
    private String categoryName;

    /**
     * 文章分类图片信息
     */
    private String avatar;

    /**
     * 文章分类的创建时间
     */
    private LocalTime createTime;

    /**
     * 该分类的文章数量
     */
    private Integer articleCount;

}
