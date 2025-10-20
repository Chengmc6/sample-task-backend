package com.example.task.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.task.common.ApiResponse;
import com.example.task.common.CustomerUserDetails;
import com.example.task.persistence.dto.taskDto.CreateTaskRequestDTO;
import com.example.task.persistence.dto.taskDto.TaskDeleteDTO;
import com.example.task.persistence.dto.taskDto.TaskPageResponseDTO;
import com.example.task.persistence.dto.taskDto.TaskQueryDTO;
import com.example.task.persistence.dto.taskDto.TaskResponseDTO;
import com.example.task.persistence.dto.taskDto.UpdateTaskRequestDTO;
import com.example.task.persistence.service.ITaskService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * <p>
 * 任务表 前端控制器
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
@RestController
@RequestMapping("/admin/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    // 创建任务
    @PostMapping
    public ApiResponse<Void> createTasks(@RequestBody @Valid CreateTaskRequestDTO createDto,
            @AuthenticationPrincipal CustomerUserDetails userDetails)  {
            Long userId = userDetails.getId(); // 从认证信息中获取用户ID
            taskService.createTasks(createDto, userId);
        return ApiResponse.success();
    }

    // 查询任务列表
    @GetMapping("/query")
    public ApiResponse<TaskPageResponseDTO> getTasks(@RequestBody @Valid TaskQueryDTO dto,
            @AuthenticationPrincipal CustomerUserDetails userDetails) {
            dto.setUserId(userDetails.getId()); // 强制设置用户ID，防止越权
            TaskPageResponseDTO responseDTO = taskService.getTasks(dto);    
        return ApiResponse.success(responseDTO);
    }
    // 删除任务
    @DeleteMapping("/delete")
    public ApiResponse<Void> deleteTasks(@RequestBody @Valid TaskDeleteDTO deleteDTO,
            @AuthenticationPrincipal CustomerUserDetails userDetails) {
            Long userId = userDetails.getId(); // 从认证信息中获取用户ID
            taskService.deleteTask(deleteDTO.getTaskIds(), userId);
        return ApiResponse.success();
    }
    // 根据ID获取任务详情
    @GetMapping("/{id}")
    public ApiResponse<TaskResponseDTO> getTaskById(@PathVariable Long id,
            @AuthenticationPrincipal CustomerUserDetails userDetails) {
            Long userId = userDetails.getId(); // 从认证信息中获取用户ID
            TaskResponseDTO responseDTO=taskService.getTaskById(id, userId);
        return ApiResponse.success(responseDTO);
    }

    // 更新任务
    @PostMapping("/update")
    public ApiResponse<TaskResponseDTO> updateTask(@RequestBody @Valid UpdateTaskRequestDTO updateDto,
            @AuthenticationPrincipal CustomerUserDetails userDetails) {
            Long userId = userDetails.getId(); // 从认证信息中获取用户ID
            TaskResponseDTO responseDTO= taskService.updateTask(updateDto, userId);
        return ApiResponse.success(responseDTO);
    }
}

