package com.hrabbit.admin.core.response;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 表格数据
 * @Auther: hrabbit
 * @Date: 2018-11-15 3:39 PM
 * @Description:
 */
public class TableResultResponse<T>{
    // 结果集
    private List<T> rows;

    // 总数
    private long total;

    public TableResultResponse(PageInfo<T> page) {
        this.rows = page.getList();
        this.total = page.getTotal();
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
