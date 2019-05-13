package com.ddm.vblog.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Date:2019/3/12 12:07
 * @Author ddm
 **/
@Data
public class Page<T> implements Serializable {

    private Integer current;

    private Integer total;

    private Integer size;

    private List<T> list;

    public void setCurrent(Integer current) {
        this.current = (current - 1) * size;
    }
}
