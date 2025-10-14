package com.example.task.persistence.service.impl;

import com.example.task.persistence.entity.Attachment;
import com.example.task.persistence.mapper.AttachmentMapper;
import com.example.task.persistence.service.IAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务附件表 服务实现类
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {

}
