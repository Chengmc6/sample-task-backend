package com.example.task.persistence.dto.taskDto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 任务查询 DTO
 * 用于封装任务查询请求参数
 * @author 陈高明
 *
 */
@Data
public class TaskQueryDTO {

    @NotNull(message = "用户ID不能为空")
    private Long userId; // 用户ID

    private String title; // 任务标题

    private Integer status; // 任务状态

    private Integer priority; // 任务优先级

    private LocalDate dueDate; // 截止日期

    @Min(value = 1, message = "每页记录数必须大于0")
    private Integer pageNum = 1; // 页码，默认值为1

    @Min(value = 1, message = "每页记录数必须大于0")
    @Max(value = 50, message = "每页记录数不能超过50")
    private Integer pageSize = 10; // 每页记录数，默认值为10


    private String sortBy = "created_at"; // 排序字段，默认按创建时间排序

    private String sortOrder = "desc"; // 排序顺序，默认降序
}
