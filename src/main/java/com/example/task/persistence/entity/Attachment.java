package com.example.task.persistence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 任务附件表
 * </p>
 *
 * @author 陈高明
 * @since 2025-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 附件ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属任务ID
     */
    private Long taskId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件存储路径或URL
     */
    private String fileUrl;

    /**
     * 上传时间
     */
    private LocalDateTime uploadedAt;


}
