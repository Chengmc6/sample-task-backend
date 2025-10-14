package com.example.task.persistence.service.impl;


import com.example.task.common.ResultCode;
import com.example.task.common.SystemException;
import com.example.task.persistence.dto.taskDto.CreateTaskRequestDTO;
import com.example.task.persistence.dto.taskDto.TaskPageResponseDTO;
import com.example.task.persistence.dto.taskDto.TaskQueryDTO;
import com.example.task.persistence.dto.taskDto.TaskResponseDTO;
import com.example.task.persistence.dto.taskDto.UpdateTaskRequestDTO;
import com.example.task.persistence.entity.Task;
import com.example.task.persistence.mapper.TaskMapper;
import com.example.task.persistence.mapper.TaskStructMapper;
import com.example.task.persistence.service.ITaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 任务表 服务实现类
 * </p>
 * 任务服务实现类，提供任务的创建和分页查询功能
 * @author 陈高明
 * @since 2025-10-11
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {
    
    @Autowired
    private TaskMapper taskMapper;// 注入任务Mapper，用于数据库操作

    @Autowired
    private TaskStructMapper taskStructMapper;// 注入映射器，用于DTO与实体之间的转换

    // Create Task
    @Override
    public void createTasks(CreateTaskRequestDTO createDto,Long id) {
        // DTO 转实体
        Task task = taskStructMapper.toEntity(createDto);
        task.setUserId(id);
        taskMapper.insert(task);
    }

    // Query Tasks with Pagination
    @Override
    public TaskPageResponseDTO getTasks(TaskQueryDTO dto) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_id", dto.getUserId()) // 必须按用户ID过滤
                    .ne("status", 3); // 排除已删除的任务   
        if(StringUtils.hasText(dto.getTitle())){
            queryWrapper.like("title", dto.getTitle());// 模糊匹配标题
        }
        if(dto.getStatus() != null){
            queryWrapper.eq("status", dto.getStatus());// 精确匹配状态
        }
        if (dto.getPriority()!=null) {
            queryWrapper.eq("priority", dto.getPriority());// 精确匹配优先级
        }
        if(dto.getDueDate() != null){
            queryWrapper.eq("due_date", dto.getDueDate());// 精确匹配截止日期
        }
        // 排序
        queryWrapper.last("ORDER BY FIELD(status, 1, 0, 2), due_date DESC");// 自定义排序：按状态排序，未开始(1)、进行中(0)、已完成(2)，然后按截止日期降序
        
        Page<Task> page = new Page<>(dto.getPageNum(), dto.getPageSize());// 创建分页对象
        Page<Task> taskPage = taskMapper.selectPage(page, queryWrapper);// 执行分页查询
        TaskPageResponseDTO responseDTO = new TaskPageResponseDTO();
        responseDTO.setTotal(taskPage.getTotal());// 设置总记录数
        responseDTO.setPageNum(taskPage.getCurrent());// 设置当前页码
        responseDTO.setPageSize(taskPage.getSize());// 设置每页记录数
        responseDTO.setTaskRecords(taskStructMapper.toListsDto(taskPage.getRecords()));// 转换并设置当前页的任务列表
       
        return responseDTO;
    }

    // Delete Tasks
    @Override
    public void deleteTask(List<Long> ids, Long userId) {
        UpdateWrapper<Task> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids)
                     .eq("user_id", userId)
                     .set("status", 3)// 3表示已删除
                     .set("updated_at", LocalDateTime.now());
        taskMapper.update(null, updateWrapper);
    }

    // 任务详情展示
    @Override
    public TaskResponseDTO getTaskById(Long id, Long userId) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                    .eq("user_id", userId)
                    .ne("status", 3); // 排除已删除的任务
        Task task = taskMapper.selectOne(queryWrapper);
        if (task == null) {
            throw new SystemException(ResultCode.NOT_FOUND); // 抛出异常，表示任务不存在或已被删除
        }
        TaskResponseDTO responseDTO = taskStructMapper.toRseponseDto(task);
        return responseDTO;    
    }

    @Override
    public TaskResponseDTO updateTask(UpdateTaskRequestDTO updateDto, Long userId) {
        Task task = taskMapper.selectById(updateDto.getId());
        if (task==null || !task.getUserId().equals(userId) || task.getStatus() == 3) {
            throw new SystemException(ResultCode.NOT_FOUND); // 抛出异常，表示任务不存在或已被删除
        }
        taskStructMapper.patchTask(updateDto, task);
        taskMapper.updateById(task);
        TaskResponseDTO responseDTO = taskStructMapper.toRseponseDto(task);
        return responseDTO;
    }
}
