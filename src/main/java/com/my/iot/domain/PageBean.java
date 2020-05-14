package com.my.iot.domain;

import java.util.List;

public class PageBean<E> {//分页对象
    private int count;//总记录数
    private int pageCount;//总页数
    private int page;//当前页码
    private int pageSize;//每页记录数，默认5
    private List<E> list;//当前页数据

    private int startIndex;//开始记录的索引

    public PageBean() {
    }

    public PageBean(int count, int pageCount, int page, int pageSize, List<E> list) {
        this.count = count;
        this.pageCount = pageCount;
        this.page = page;
        this.pageSize = pageSize;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "count=" + count +
                ", pageCount=" + pageCount +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
