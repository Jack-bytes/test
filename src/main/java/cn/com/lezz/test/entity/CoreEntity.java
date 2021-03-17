package cn.com.lezz.test.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * entity
 */
@Data
@Accessors(chain = true)
public class CoreEntity implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除
     */
    private Integer deleted;

    private String createdBy;

    private LocalDateTime createdTime = LocalDateTime.now();

    private String modifiedBy;

    private LocalDateTime modifiedTime = LocalDateTime.now();

}
