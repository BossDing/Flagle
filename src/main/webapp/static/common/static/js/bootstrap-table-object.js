/**
 * 初始化 BootStrap Table 的封装
 *
 * 约定：toolbar的id为 (bstableId + "Toolbar")
 *
 * @author hrabbit
 */
(function () {
    var BSTable = function (bstableId, url, columns) {
        this.btInstance = null;					//jquery和BootStrapTable绑定的对象
        this.bstableId = bstableId;
        this.url = url;
        this.queryParams = {}; // 向后台传递的自定义参数
        this.method = "get";
        this.paginationType = "server";			//默认分页方式是服务器分页,可选项"client"
        this.toolbarId = bstableId + "Toolbar";
        this.columns = columns;
        this.height = 665;						//默认表格高度665
        this.data = {};
        this.pagination = true;
        this.search = false;
        this.pagesize = 10;
        this.clickRow = function () {
        }
    };
    BSTable.prototype = {
        /**
         * 初始化bootstrap table
         */
        init: function () {
            var tableId = this.bstableId;
            var me = this;
            this.btInstance =
                $('#' + tableId).bootstrapTable({
                    contentType: "application/x-www-form-urlencoded",
                    url: this.url,				//请求地址
                    method: this.method,		//ajax方式,post还是get
                    ajaxOptions: {				//ajax请求的附带参数
                        data: this.data
                    },
                    striped: true,     			//是否显示行间隔色
                    cache: false,      			//是否使用缓存,默认为true
                    pagination: this.pagination,     		//是否显示分页（*）
                    sortable: false,      		//是否启用排序
                    sortOrder: "desc",     		//排序方式
                    pageNumber: 1,      			//初始化加载第一页，默认第一页
                    pageSize: this.pagesize,      			//每页的记录行数（*）
                    singleSelect: false,
                    pageList: [10,20,50,100],  	//可供选择的每页的行数（*）
                    queryParamsType: 'limit', 	//默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                    queryParams: function (param) {
                        var query = queryParams(this);
                        return $.extend(query, param,me.queryParams);
                    }, // 向后台传递的自定义参数
                    sidePagination: this.paginationType,   //分页方式：client客户端分页，server服务端分页（*）
                    search: false,      		//是否显示表格搜索，此搜索是客户端搜索，不会进服务端
                    strictSearch: false,			//设置为 true启用 全匹配搜索，否则为模糊搜索
                    showColumns: false,     		//是否显示所有的列
                    showRefresh: false,     		//是否显示刷新按钮
                    minimumCountColumns: 2,    	//最少允许的列数
                    clickToSelect: true,       	//是否启用点击选中行
                    searchOnEnterKey: true,		//设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
                    columns: this.columns,		//列数组
                    height: this.height,
                    responseHandler: responseHandler,//请求数据成功后，渲染表格前的方法,
                    onLoadSuccess: onLoadSuccess,
                    detailView:true,
                    detailFormatter:function(index,row){
                        var str = "";
                        return row;
                    },
                    icons: {
                        refresh: 'glyphicon-repeat',
                        toggle: 'glyphicon-list-alt',
                        columns: 'glyphicon-list'
                    },
                    onClickRow: this.clickRow,
                    iconSize:
                        'outline'
                })
            ;
            return this;
        },

        /**
         * 向后台传递的自定义参数
         * @param param
         */
        setQueryParams: function (data) {
            this.queryParams = data;
        },
        /**
         * 设置分页方式：server 或者 client
         */
        setPaginationType: function (type) {
            this.paginationType = type;
        },
        /**
         * 设置分页方式：server 或者 client
         */
        setPagination: function (type) {
            this.pagination = type;
        },
        /**
         * 设置ajax post请求时候附带的参数
         */
        set: function (key, value) {
            if (typeof key == "object") {
                for (var i in key) {
                    if (typeof i == "function")
                        continue;
                    this.data[i] = key[i];
                }
            } else {
                this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
            }
            return this;
        },

        /**
         * 设置ajax post请求时候附带的参数
         */
        setData: function (data) {
            this.data = data;
            return this;
        },

        setHeight: function (height) {
            this.height = height;
        },

        /**
         * 清空ajax post请求参数
         */
        clear: function () {
            this.data = {};
            return this;
        },

        /**
         * 刷新 bootstrap 表格
         * Refresh the remote server data,
         * you can set {silent: true} to refresh the data silently,
         * and set {url: newUrl} to change the url.
         * To supply query params specific to this request, set {query: {foo: 'bar'}}
         */
        refresh: function (parms) {
            if (typeof parms != "undefined") {
                this.queryParams = parms.query;
                this.btInstance.bootstrapTable('refresh', parms);
            } else {
                this.btInstance.bootstrapTable('refresh');
            }
        }
        /**
         * 返回选择行数据
         * @returns {*}
         */
        , getSelections: function () {
            return this.btInstance.bootstrapTable('getSelections');
        }
        /**
         * 返回选择行数据
         * @returns {*}
         */
        , getSelectionsId: function () {
            var selected = this.btInstance.bootstrapTable('getSelections');
            if (selected.length == 0) {
                return "";
            } else {
                return selected[0].id;
            }

        }
        /**
         * 返回选择行数据的所有ID
         * @returns {*}
         */
        , getSelectionIds: function () {
            var selects = this.btInstance.bootstrapTable('getAllSelections');
            var ids = [selects.length];
            for (var i = 0; i < selects.length; i++) {
                ids[i] = selects[i].id;
            }
            return ids;
        }
        /**
         * 根据ID选中行记录
         * @param id
         */
        ,
        checkedById: function (id) {
            this.btInstance.bootstrapTable("checkBy", {field: "id", values: [id]})
        },
        /**
         * 获取选中所有行数据，本分页内有效，切换分页无效
         * @returns {jQuery|*}
         */
        getAllSelections: function () {
            return this.btInstance.bootstrapTable('getAllSelections');
        },
        /**
         * 当表格有复选框时，选中所有行
         */
        checkAll: function () {
            this.btInstance.bootstrapTable('checkAll');
        },
        /**
         * 当表格有复选框时，取消选中所有行
         */
        uncheckAll: function () {
            this.btInstance.bootstrapTable('uncheckAll');
        },

        setClickRows: function (clickRow) {
            this.clickRow = clickRow;
        }
    };

    //请求成功方法
    function responseHandler(result) {
        if (this.pagination == false) {
            return result.rows;
        } else {
            return result;
        }
    };

    function onLoadSuccess(data) {
        if (this.pagination == true && data.rows.length == 0) {
            $(".fixed-table-pagination").css("display", "block");
        }
    };

    function queryParams(params) {
        return {
            pageSize: params.pageSize,
            pageNum: params.pageNumber
        };
    }


    window.BSTable = BSTable;

}());