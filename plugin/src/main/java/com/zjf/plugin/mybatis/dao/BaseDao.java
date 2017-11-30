package com.zjf.plugin.mybatis.dao;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:601641916@qq.com">Cao Chang Ming</a>
 * @version 0.1
 * @since 0.1
 */
public interface BaseDao<T, E, ID extends Serializable> {
    int insertSelective(T record);

    <S extends T> int insert(S entity);

    List<T> selectByExample(E example);

    <S extends T> S selectByPrimaryKey(ID id);

    long countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    <S extends T> int saves(Iterable<S> entities);

    /**
     * 根据参数查询一条数据
     *
     * @param params
     * @return
     */
    <S extends T> List<S> getByParams(Map<String, Object> params);

    /**
     * 根据主键删除
     *
     * @param ids 主键集合
     */
    void deleteSet(Set<ID> ids);

    /**
     * 批量删除(软删除)
     *
     * @param params params中
     */
    void delete(Map<String, Object> params);
}
