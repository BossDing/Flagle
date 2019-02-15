package com.hrabbit.admin.core.node;

import lombok.Data;

import java.io.Serializable;

/**
 * 组织机构树
 * @Auther: hrabbit
 * @Date: 2018-12-08 9:36 AM
 * @Description:
 */
@Data
public class TreeNode implements Serializable {


    public TreeNode(){

    }

    public TreeNode(String id, String parent, String text, String type){
        this.id=id;
        this.parent=parent;
        this.text=text;
        this.type=type;
    }
    /**
     * 组织机构id
     */
    private String id;

    /**
     * 组织机构父id
     */
    private String parent;

    /**
     * 组织机构名称
     */
    private String text;

    /**
     * 类型
     */
    private String type;

    /**
     * 是否打开节点
     */
    private Boolean open;

    /**
     * 是否被选中
     */
    private Boolean checked;

    /**
     * 获取顶级
     * @return
     */
    public static TreeNode createParent(){
        TreeNode organizationTree = new TreeNode();
        organizationTree.setId("0");
        organizationTree.setText("顶级");
        organizationTree.setParent("0");
        return organizationTree;
    }
}