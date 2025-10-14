package com.example.task.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 任务表
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 任务状态(0-未开始，1-进行中，2-已完成，3-已删除)
     */
    private Integer status;

    /**
     * 优先级（1-低，2-中，3-高）
     */
    private Integer priority;

    /**
     * 截止日期
     */
    private LocalDate dueDate;

    /**
     * 任务所属用户ID
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
