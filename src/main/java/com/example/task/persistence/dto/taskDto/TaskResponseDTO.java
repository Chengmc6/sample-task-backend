package com.example.task.persistence.dto.taskDto;

import lombok.Data;

/**
 * 任务响应DTO
 * 用于封装任务的详细信息，作为API响应的一部分返回给客户端
 * @author 陈高明
 * @since 2025-10-11
 */
@Data
public class TaskResponseDTO {
    private Long id;
    private String title;  
    private String description;
    private Integer status; //0-未开始, 1-进行中, 2-已完成
    private Integer priority; //1-低, 2-中, 3-高
    private String dueDate; //yyyy-MM-dd
    private Long userId;
    private String createdAt; //yyyy-MM-dd HH:mm:ss
    private String updatedAt; //yyyy-MM-dd HH:mm:ss
}
