package com.example.task.persistence.dto.taskDto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 创建任务请求DTO
 * 用于封装创建任务时的请求数据，包含任务的各个属性
 * @author 陈高明
 * @since 2025-10-11
 */
@Data
public class CreateTaskRequestDTO {
    
    @NotBlank(message = "标题不能为空")
    private String title;

    private String description;
    // 使用 @FutureOrPresent 注解确保截止日期是今天或将来
    @FutureOrPresent(message = "截止日期必须是今天或将来")
    private LocalDateTime dueDate; 

}
