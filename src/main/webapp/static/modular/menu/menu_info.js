/**
 * 按钮信息
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/12 1:40 PM
 */
var MenuInfo = {
    menuTableId: '',//table的id
    menuTable: null //MenuTable的对象
};

/**
 * 加载顶级按钮
 */
MenuInfo.loadTopMenu = function(){
    var topMenuDoc = $('#topMenu');
    var topMenu = "";
    //加载顶级菜单
    var ajax = new $ax("/menu/getMenuByParentId/-1", function (data) {
        for (var i=0;i<data.length;i++){
            topMenu +="<div class='list-group-item' onclick='clickItem("+data[i].id+")'>";
            topMenu +="<div class='list-content'>";
            topMenu +="<i class='icon "+data[i].icon+"'></i>";
            topMenu +="<span class='top_menuname'>"+data[i].title+"</span>";
            topMenu +="<div class='item-actions'>";
            topMenu +="<span class='btn btn-pure btn-icon'>";
            topMenu +="<i class='icon wb-edit' aria-hidden='true'></i></span>";
            topMenu +="<span class='btn btn-pure btn-icon'><i class='icon wb-close' aria-hidden='true'></i></span>";
            topMenu +="</div>";
            topMenu +="</div>";
            topMenu +="</div>";
        }
    }, function (data) {
    });
    ajax.setType("get");
    ajax.start();
    topMenuDoc.append(topMenu);
};

/**
 * 初始化
 * @author hrabbit
 * @Param:
 * @Date: 2019/1/12 1:41 PM
 */
$(function () {
    //加载顶级Menu
    MenuInfo.loadTopMenu();
});