package com.example.task.persistence.dto.taskDto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 任务删除DTO
 * 用于封装删除任务时的请求数据，包含要删除的任务ID列表
 * @author 陈高明
 * @since 2025-10-11
 */
@Data
public class TaskDeleteDTO {

    @NotEmpty(message = "任务ID列表不能为空")
    private List<Long> taskIds;
}
