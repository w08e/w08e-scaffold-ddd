package com.w08e.common.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Version;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * 实体标记
 *
 * @author wo8e
 */

@Data
public abstract class BaseEntity<ID> implements MarkerInterface, Serializable {

    @Serial
    private static final long serialVersionUID = -17323267266286209L;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    protected OffsetDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "modify_time")
    protected OffsetDateTime modifyTime;

    @Column(name = "modifier")
    protected Long modifier;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "is_deleted")
    private Boolean deleted;

    /**
     * 0 可用, 1 禁用, 2 注销
     */
    @Column(name = "status")
    private Integer status;


    public BaseEntity() {
        createTime = OffsetDateTime.now();
        modifyTime = createTime;
        status = 0;
        deleted = false;
    }

    public BaseEntity(Long userId) {
        createTime = OffsetDateTime.now();
        modifyTime = createTime;
        status = 0;
        deleted = false;
        modifier = userId;
    }


    public boolean isDelete() {
        return deleted != null && deleted;
    }

    public void del() {
        this.deleted = true;
    }

    public abstract ID getId();
    public abstract void setId(ID id);
}
