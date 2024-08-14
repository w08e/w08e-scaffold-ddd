package com.w08e.common.core.command.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 梦想成为超人的猪猪侠
 */
@Setter
@Getter
public class QueryParameter {

    /**
     * 页码
     */
    private Long pageNumber;

    /**
     * 每页条数
     */
    private Long pageSize;

    /**
     * 是否查询总条目
     */
    private boolean withTotal;

    /**
     * 排序
     */
    private List<Sort> sorts;


    @Setter
    @Getter
    public static class Sort {

        private String field;

        private Direction direction;

    }


    public enum Direction {
        ASCENDING,
        DESCENDING
    }
}
