package com.w08e.common.core.domain;

import org.springframework.util.CollectionUtils;

import java.util.Collection;


/**
 * @author 梦想成为超人的猪猪侠
 */
public interface Identified {
    static boolean isDuplicated(Collection<? extends Identified> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return false;
        }

        long count = collection.stream().map(Identified::getIdentifier).distinct().count();
        return count != collection.size();
    }

    Long getIdentifier();
}
