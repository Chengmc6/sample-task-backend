package com.example.task.persistence.service;

import com.example.task.persistence.dto.taskDto.CreateTaskRequestDTO;
import com.example.task.persistence.dto.taskDto.TaskPageResponseDTO;
import com.example.task.persistence.dto.taskDto.TaskQueryDTO;
import com.example.task.persistence.dto.taskDto.TaskResponseDTO;
import com.example.task.persistence.dto.taskDto.UpdateTaskRequestDTO;
import com.example.task.persistence.entity.Task;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务表 服务类
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
public interface ITaskService extends IService<Task> {
    void createTasks(CreateTaskRequestDTO createDto,Long id);
    TaskPageResponseDTO getTasks(TaskQueryDTO dto);
    void deleteTask(List<Long> ids, Long userId);
    TaskResponseDTO getTaskById(Long id, Long userId);
    TaskResponseDTO updateTask(UpdateTaskRequestDTO updateDto, Long userId);
}
