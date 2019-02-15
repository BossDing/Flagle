# Flagle后台管理

#### 项目介绍
Flagle基于SpringBoot 2.X,构建一个能够快速开发的框架，整合SpringMVC + Shiro + MybatisPlus + Beetl!Flagle项目代码简洁，注释丰富，上手容易，同时Flagle包含许多基础模块(用户管理，角色管理，部门管理，字典管理等模块，后续还在开发...)，可以直接作为一个后台管理系统的脚手架!

#### Beetl对前台页面的拆分与包装

例如，把主页拆分成三部分，每个部分单独一个页面，更加便于维护

```
 <!-- 菜单部分 -->
 @include("/common/_nav.html"){}

 <!-- 内容部分 -->
 @include("/common/_page.html"){}

 <!-- 底部部分 -->
 @include("/common/_footer.html"){}
 
```
#### 对js常用代码的封装

例如，JsTree树型菜单，BootStrapTable的整合

```
/**
 * ztree插件的封装
 * @author hrabbit
 */
(function () {
    var $JSTree = function (id, url) {
        this.id = id;
        this.url = url;
        this.onClick = null;
        this.settings = null;
        this.btInstance =null;
        this.checkbox=false;
    };

    $JSTree.prototype = {
        /**
         * 初始化ztree的设置
         */
        initSetting: function () {
            var settings = {
                'core': {
                    'data':this.loadNodes(),
                    "themes": {
                        "responsive": false,
                        "dots" : false
                    }
                }
            };

            //是否开启复选框
            if (this.checkbox){
                settings = {
                    'core': {
                        //出现选择框
                        'plugins': ["checkbox"],
                        //不级联
                        'checkbox': {cascade: "", three_state: false},
                        'data': this.loadNodes(),
                        "themes": {
                            "responsive": false,
                            "dots": false
                        }
                    }
                }
            }
            return settings;
        },

        /**
         * 手动设置ztree的设置
         */
        setSettings: function (val) {
            this.settings = val;
        },

        /**
         * 初始化ztree
         */
        init: function () {
            var zNodeSeting = null;
            if (this.settings != null) {
                zNodeSeting = this.settings;
            } else {
                zNodeSeting = this.initSetting();
            }
            this.btInstance = $('#'+this.id).jstree(zNodeSeting).bind('changed.jstree', this.onClick);
            return this;
        },
        /**
         * 绑定onclick事件
         */
        bindOnClick: function (func) {
            this.onClick = func;
        },

        /**
         * 设置是否开启复选，默认false
         * @param data
         */
        setCheckBoxEnable: function(data){
            this.checkbox = data;
        },

        /**
         * 加载节点
         */
        loadNodes: function () {
            //设置返回数据的常量
            var zNodes = null;
            //发送ajax
            var ajax = new $ax(this.url, function (data) {
                zNodes = data;
            }, function (data) {
            });
            ajax.start();
            return datas;
        }
    };

    window.$JSTree = $JSTree;

}());

```
创建菜单树的时候，则变得更加简便

```
//初始化树状菜单
var userTree = new $JSTree('userTree','aa');
userTree.bindOnClick(UserInfo.onClickUser);
userTree.setCheckBoxEnable(true);
userTree.init();
```

#### 效果图

![image1.png](https://upload-images.jianshu.io/upload_images/5630287-7007bb7875e53eae.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image2.png](https://upload-images.jianshu.io/upload_images/5630287-c455598fbac8c914.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image3.png](https://upload-images.jianshu.io/upload_images/5630287-654d6cedc336e48f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image4.png](https://upload-images.jianshu.io/upload_images/5630287-3d17170737ab19aa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image5.png](https://upload-images.jianshu.io/upload_images/5630287-7cdb4d0d8e7753a9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image6.png](https://upload-images.jianshu.io/upload_images/5630287-df54988c45f58290.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
