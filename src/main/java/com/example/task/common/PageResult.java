package com.example.task.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用分页结果类
 * 用于封装分页查询的结果数据
 * 对于不同类型的记录，可以通过泛型参数 R 指定
 * 可以用于各种实体的分页查询结果封装。
 * @author 陈高明
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<R> {
    private Long total; // 总记录数
    private Long pageNum; // 当前页码
    private Long pageSize; // 每页记录数
    private Long totalPages; // 总页数
    private List<R> records; // 当前页的记录列表   
}
