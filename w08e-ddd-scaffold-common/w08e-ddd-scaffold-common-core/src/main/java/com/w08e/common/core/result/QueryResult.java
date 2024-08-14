package com.w08e.common.core.result;

import lombok.Data;

import java.util.List;


/**
 * @author 梦想成为超人的猪猪侠
 */
@Data
public class QueryResult<T> {

    public static <T> QueryResult<T> of(List<T> data) {
        return new QueryResult<>(data);
    }

    public static <T> QueryResult<T> of(List<T> data, Long total) {
        return new QueryResult<>(data, total);
    }

    private List<T> list;

    private Long total;

    public QueryResult(List<T> list, Long total) {
        this.total = total;
        this.list = list;
    }

    public QueryResult(List<T> list) {
        this.list = list;
    }

    public QueryResult() {
    }

}
