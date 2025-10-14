package com.example.task.persistence.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 任务与标签关联表
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TaskTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 标签ID
     */
    private Long tagId;


}
