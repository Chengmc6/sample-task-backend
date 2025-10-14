package com.example.task.persistence.service.impl;

import com.example.task.persistence.entity.TaskTag;
import com.example.task.persistence.mapper.TaskTagMapper;
import com.example.task.persistence.service.ITaskTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务与标签关联表 服务实现类
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
@Service
public class TaskTagServiceImpl extends ServiceImpl<TaskTagMapper, TaskTag> implements ITaskTagService {

}
