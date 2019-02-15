/**
 * ztree插件的封装
 */
(function () {
    var $JSTree = function (id, url) {
        this.id = id;
        this.url = url;
        this.onClick = null;
        this.settings = null;
        this.btInstance = null;
        this.checkbox = false;
    };

    $JSTree.prototype = {
        /**
         * 初始化ztree的设置
         */
        initSetting: function () {
            var resData = this.loadNodes();
            var settings = {
                'core': {
                    'data': resData,
                    "themes": {
                        "responsive": false,
                        "dots": false,
                        "stripes": true
                    }
                },
                "types": {
                    "default": {
                        "icon": "jstree-icon jstree-themeicon fa-folder-o jstree-themeicon-custom"
                    },
                    "file": {
                        "icon": "jstree-icon jstree-themeicon fa-file-o jstree-themeicon-custom"
                    }
                },

                "plugins": ['types']
            };

            //是否开启复选框
            if (this.checkbox) {
                settings = {
                    'core': {
                        //出现选择框
                        'plugins': ["checkbox"],
                        //不级联
                        'checkbox': {cascade: "", three_state: false},
                        'data': resData,
                        "themes": {
                            "responsive": false,
                            "dots": false,
                            "icons": true
                        }
                    },
                    "types": {
                        "default": {
                            "icon": "jstree-icon jstree-themeicon fa-folder-o jstree-themeicon-custom"
                        },
                        "file": {
                            "icon": "jstree-icon jstree-themeicon fa-file-o jstree-themeicon-custom"
                        }
                    },

                    "plugins": ['types']
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
            this.btInstance = $('#' + this.id).jstree(zNodeSeting).bind('activate_node.jstree', this.onClick);
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
        setCheckBoxEnable: function (data) {
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
                zNodes = data.data;
            }, function (data) {
            });
            ajax.setType('get');
            ajax.start();
            return zNodes;
        }
    };
    window.$JSTree = $JSTree;
}());

