package com.w08e.common.core.domain.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * OSS对象
 *
 * @author Lion Li
 */
@Data
@NoArgsConstructor
public class OssDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对象存储主键
     */
    private Long ossId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 原名
     */
    private String originalName;

    /**
     * 文件后缀名
     */
    private String fileSuffix;

    /**
     * URL地址
     */
    private String url;

}