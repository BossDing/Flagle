package com.hrabbit.admin.core.node;

import com.hrabbit.admin.core.constant.menu.MenuType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 菜单节点
 *
 * @Auther: hrabbit
 * @Date: 2018-11-27 11:26 AM
 * @Description:
 */
public class MenuNode implements Comparable {

    /**
     * 节点id
     */
    private Long id;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点编码
     */
    private String code;
    /**
     * 按钮级别
     */
    private Integer menus;

    /**
     * 按钮的排序
     */
    private Integer num;

    /**
     * 节点的url
     */
    private String url;

    /**
     * 节点图标
     */
    private String icon;


    /**
     * 是否默认选中
     */
    private Integer selectDef;

    /**
     * 子节点的集合
     */
    private List<MenuNode> children;

    /**
     * 查询子节点时候的临时集合
     */
    private List<MenuNode> linkedList = new ArrayList<MenuNode>();

    public MenuNode() {
        super();
    }

    public MenuNode(Long id, Long parentId) {
        super();
        this.id = id;
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSelectDef() {
        return selectDef;
    }

    public void setSelectDef(Integer selectDef) {
        this.selectDef = selectDef;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static MenuNode createRoot() {
        return new MenuNode(0L, -1L);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMenus() {
        return menus;
    }

    public void setMenus(Integer menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "MenuNode{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", menus=" + menus +
                ", name='" + name + '\'' +
                ", code='"+code+'\''+
                ", num=" + num +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                ", linkedList=" + linkedList +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        MenuNode menuNode = (MenuNode) o;
        Integer num = menuNode.getNum();
        if (num == null) {
            num = 0;
        }
        return this.num.compareTo(num);
    }

    /**
     * 构建整个菜单树
     *
     * @author fengshuonan
     */
    public void buildNodeTree(List<MenuNode> nodeList) {
        for (MenuNode treeNode : nodeList) {
            List<MenuNode> linkedList = treeNode.findChildNodes(nodeList, treeNode.getId());
            if (linkedList.size() > 0) {
                treeNode.setChildren(linkedList);
            }
        }
    }

    /**
     * 查询子节点的集合
     *
     * @author fengshuonan
     */
    public List<MenuNode> findChildNodes(List<MenuNode> nodeList, Long parentId) {
        if (nodeList == null && parentId == null)
            return null;
        for (Iterator<MenuNode> iterator = nodeList.iterator(); iterator.hasNext(); ) {
            MenuNode node = (MenuNode) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() != 0 && parentId.equals(node.getParentId())) {
                recursionFn(nodeList, node, parentId);
            }
        }
        return linkedList;
    }

    /**
     * 遍历一个节点的子节点
     *
     * @author fengshuonan
     */
    public void recursionFn(List<MenuNode> nodeList, MenuNode node, Long pId) {
        List<MenuNode> childList = getChildList(nodeList, node);// 得到子节点列表
        if (childList.size() > 0) {// 判断是否有子节点
            if (node.getParentId().equals(pId)) {
                linkedList.add(node);
            }
            Iterator<MenuNode> it = childList.iterator();
            while (it.hasNext()) {
                MenuNode n = (MenuNode) it.next();
                recursionFn(nodeList, n, pId);
            }
        } else {
            if (node.getParentId().equals(pId)) {
                linkedList.add(node);
            }
        }
    }

    /**
     * 得到子节点列表
     *
     * @author fengshuonan
     */
    private List<MenuNode> getChildList(List<MenuNode> list, MenuNode node) {
        List<MenuNode> nodeList = new ArrayList<MenuNode>();
        Iterator<MenuNode> it = list.iterator();
        while (it.hasNext()) {
            MenuNode n = (MenuNode) it.next();
            if (n.getParentId().equals(node.getId())) {
                nodeList.add(n);
            }
        }
        return nodeList;
    }

    /**
     * 清除掉按钮级别的资源
     *
     * @date 2017年2月19日 下午11:04:11
     */
    public static List<MenuNode> clearBtn(List<MenuNode> nodes) {
        ArrayList<MenuNode> noBtns = new ArrayList<MenuNode>();
        for (MenuNode node : nodes) {
            if (node.getMenus() == MenuType.YES.getCode()) {
                noBtns.add(node);
            }
        }
        return noBtns;
    }

    /**
     * 清除所有二级菜单
     *
     * @date 2017年2月19日 下午11:18:19
     */
    public static List<MenuNode> clearLevelTwo(List<MenuNode> nodes) {
        ArrayList<MenuNode> results = new ArrayList<MenuNode>();
        for (MenuNode node : nodes) {
            Long levels = node.getParentId();
            if (levels==0) {
                results.add(node);
            }
        }
        return results;
    }

    /**
     * 构建菜单列表
     *
     * @date 2017年2月19日 下午11:18:19
     */
    public static List<MenuNode> buildTitle(List<MenuNode> nodes) {

        List<MenuNode> clearBtn = clearBtn(nodes);

        new MenuNode().buildNodeTree(clearBtn);

        List<MenuNode> menuNodes = clearLevelTwo(clearBtn);

        //对菜单排序
        Collections.sort(menuNodes);

        //对菜单的子菜单进行排序
        for (MenuNode menuNode : menuNodes) {
            if (menuNode.getChildren() != null && menuNode.getChildren().size() > 0) {
                Collections.sort(menuNode.getChildren());
            }
        }

        return menuNodes;
    }
}
