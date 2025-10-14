package com.example.task.persistence.service.impl;

import com.example.task.persistence.entity.Tag;
import com.example.task.persistence.mapper.TagMapper;
import com.example.task.persistence.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务标签表 服务实现类
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
