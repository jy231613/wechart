package com.qb.wxbase.create.sql.base.norm;

import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/15
 * 包    名：com.qb.wxbase.create.sql.base
 * 描    述：数据库操作接口
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public interface DbO<T> {
    /**
     * 根据主键获取对象
     *
     * @param tClass 对象表类型
     * @param id     主键id
     * @return 结果对象
     */
    List<T> select(Class<T> tClass, int id);

    /**
     * 根据条件获取对象
     *
     * @param tClass 对象表类型
     * @param where  条件语句
     * @param values 条件语句中的占位符？的值
     * @return 结果集
     */
    List<T> select(Class<T> tClass, String where, String[] values);

    /**
     * 根据条件查询数据库
     *
     * @param tClass 数据库类型
     * @param params 查询字段名
     * @param where  条件语句
     * @param values 条件语句中的占位符？的值
     * @return 结果集
     */
    List<T> select(Class<T> tClass, String[] params, String where, String[] values);

    /**
     * 更新一条数据
     *
     * @param t 数据对象
     * @return true成功
     */
    boolean update(T t);

    /**
     * 更新一条数据,并拼接条件
     *
     * @param t      数据库对象
     * @param where  条件语句
     * @param values 条件语句中的占位符？的值
     * @return true成功
     */
    boolean update(T t, String where, String[] values);

    /**
     * 保存数据
     *
     * @param t 保存的对象
     * @return true插入成功
     */
    boolean insert(T t);

    /**
     * 批量插入操作对象
     *
     * @param ts 保存的对象集合
     * @return true插入成功条数
     */
    boolean insert(List<T> ts);

    /**
     * 删除
     * @return true删除成功
     */
    boolean del(Class<T> t,String where, String[] values);

    /**
     * 删除
     * @param t 表类型
     * @param id 主键id
     * @return true删除成功
     */
    boolean del(Class<T> t,String id);
}
