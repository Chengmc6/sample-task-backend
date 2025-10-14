package com.example.task.common;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

//封装分页，未使用，当前作为学习用例
/**
 * 分页工具类
 * 提供分页结果的构建方法
 * 通过泛型参数 T 和 R 支持不同实体和 DTO 类型的转换
 * 可以用于各种实体的分页查询结果封装。
 * @author 陈高明
 *
 */
public class PageUtils {

    /**
     * 构建分页响应结构
     * @param page MyBatis-Plus 分页结果
     * @param mapper 实体 → DTO 映射函数
     * @param <T> 原始实体类型,输入
     * @param <R> DTO 类型，输出
     * @return PageResult<R>
     */
    public static <T, R> PageResult<R> build(Page<T> page, Function<T, R> mapper) {// 使用流映射实体到DTO
        // 将实体列表映射为 DTO 列表
        List<R> dtoList = page.getRecords().stream()
                .map(mapper)
                .collect(Collectors.toList());

        return new PageResult<>(
            page.getTotal(),
            page.getCurrent(),
            page.getSize(),
            page.getPages(),
            dtoList
        );
    }
}
