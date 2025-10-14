package com.example.task.persistence.dto.taskDto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * 更新任务请求DTO
 * 用于封装更新任务时的请求数据，包含任务的各个属性
 * @author 陈高明
 * @since 2025-10-11
 */
@Data
public class UpdateTaskRequestDTO {
    //NotNull 注解用于确保 ID 不能为空
    @NotNull(message = "任务ID不能为空")
    private Long id;

    //NotBlank 注解用于确保标题不能为空
    @NotBlank(message = "任务标题不能为空")
    private String title;
    private String description;

    @FutureOrPresent(message = "截止日期必须是当前或未来的时间")
    private LocalDateTime dueDate;

    //Min 和 Max 注解用于确保优先级和状态在指定范围内
    @Min(value = 1, message = "任务优先级不合法")
    @Max(value = 3, message = "任务优先级不合法")
    private Integer priority;//1-低, 2-中, 3-高

    @NotNull(message = "任务状态不能为空")
    @Min(value = 0, message = "任务状态不合法")
    @Max(value = 2, message = "任务状态不合法")
    private Integer status;//0-未开始, 1-进行中, 2-已完成
}
