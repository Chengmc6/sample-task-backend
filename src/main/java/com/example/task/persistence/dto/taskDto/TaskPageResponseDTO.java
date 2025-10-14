package com.example.task.persistence.dto.taskDto;

import java.util.List;

import lombok.Data;

/**
 * 任务分页响应 DTO
 * 用于封装任务分页查询的响应数据
 * @author 陈高明
 *
 */
@Data
public class TaskPageResponseDTO {

    private Long total; // 总记录数
    private Long pageNum; // 当前页码
    private Long pageSize; // 每页记录数
    private List<TaskListResponseDTO> taskRecords; // 当前页的任务列表

}
