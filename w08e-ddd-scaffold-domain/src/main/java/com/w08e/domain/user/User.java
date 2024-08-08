package com.w08e.domain.user;

import com.w08e.common.core.domain.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/7 14:38
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AggregateRoot {

    private String name;

}
