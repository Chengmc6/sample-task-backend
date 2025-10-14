package com.example.task.persistence.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.task.persistence.dto.taskDto.CreateTaskRequestDTO;
import com.example.task.persistence.dto.taskDto.TaskListResponseDTO;
import com.example.task.persistence.dto.taskDto.TaskResponseDTO;
import com.example.task.persistence.dto.taskDto.UpdateTaskRequestDTO;
import com.example.task.persistence.entity.Task;

/**
 * MapStruct 映射接口，用于在 Task 实体和 DTO 之间转换
 * ignore = true 表示在映射时忽略该字段
 * expression 用于自定义映射逻辑
 * constant 用于为字段设置固定值
 * source 用于指定源字段，当字段名不同时使用
 * target 用于指定目标字段
 * @author 陈高明
 *
 */
@Mapper(componentModel = "spring")// 使用 Spring 组件模型，而不是mybatis-plus默认的
public interface TaskStructMapper {

    @Mapping(target = "id", ignore = true) // 忽略ID字段，防止插入时指定ID
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())") // 设置创建时间为当前时间
    @Mapping(target = "updatedAt", ignore = true) // 忽略更新时间字段
    @Mapping(target = "userId", ignore = true) // 忽略用户ID字段
    @Mapping(target = "status", constant  = "0") // 新建任务默认状态为未开始(0)
    @Mapping(target = "priority", constant = "1") // 默认优先级为1
    Task toEntity(CreateTaskRequestDTO createDto);// DTO 转实体
    CreateTaskRequestDTO toDto(Task entity);// 实体 转 DTO
    
    @Mapping(target = "id", ignore = true) // 忽略ID字段，防止更新时覆盖
    @Mapping(target = "createdAt", ignore = true) // 忽略创建时间字段
    @Mapping(target = "updatedAt", ignore = true) // 忽略更新时间字段
    @Mapping(target = "userId", ignore = true) // 忽略用户ID字段
    Task toEntity(UpdateTaskRequestDTO updateDto);// DTO 转实体

    List<TaskResponseDTO> toDtoList(List<Task> entities);// 实体列表 转 DTO 列表
    TaskResponseDTO toRseponseDto(Task entity);// 实体 转 DTO

    TaskListResponseDTO toLRDto(Task entity);// 实体 转 DTO
    List<TaskListResponseDTO> toListsDto(List<Task> entities);// 实体列表 转 DTO 列表

    @Mapping(target = "id", ignore = true) // 忽略ID字段，防止更新时覆盖
    @Mapping(target = "createdAt", ignore = true) // 忽略创建时间字段
    @Mapping(target = "userId", ignore = true) // 忽略用户ID字段
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())") // 设置更新时间为当前时间
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)// 忽略空值属性，防止覆盖已有值
    void patchTask(UpdateTaskRequestDTO dto,@MappingTarget Task task);// 部分更新任务
}
