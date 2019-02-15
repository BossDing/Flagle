package com.hrabbit.admin.core.node;

import com.hrabbit.admin.modual.system.bean.SysRole;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 复杂下拉框数据
 *
 * @Auther: hrabbit
 * @Date: 2019-01-29 2:08 PM
 * @Description:
 */
public class SelectNode implements Serializable {

    /**
     * 父级id
     */
    private Integer id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 存放子节点
     */
    List<SysRole> linkedList = new ArrayList<>();

    public List<SysRole> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(List<SysRole> linkedList) {
        this.linkedList = linkedList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}