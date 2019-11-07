package com.gtdq.generator.tkmybatis;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 批量操作接口
 *
 */
@RegisterMapper
public interface BatchMapper<T> extends UpdateBatchByPrimaryKeySelectiveMapper<T> {
}
