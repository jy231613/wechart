package com.qb.wxbase.create.sql;

import java.util.Map;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/12
 * 包    名：com.qb.wxbase.create.sql
 * 描    述：sql语句
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public final class SqlConfig {

    /**
     * 系统预定义自增id
     */
    private final static String SQL_SYSTEM_ID = "id integer primary key autoincrement,";

    /**
     * 数据库表创建语句
     * %tableName -> 表名
     * %systemId -> 系统Id(主键)
     * %otherParams -> 其他字段
     */
    private final static String CREATE_TABER_SQL = "create table %tableName ( %systemId %otherParams )";

    /**
     * 查询语句
     * * -> 默认为*,查询表中所有字段,也可以自定义字段替换
     * %tableName -> 表名
     */
    private final static String SQL_SELECT_BASE = "select * from %tableName where 1=1";

    /**
     * 查询结果去重复
     * * -> 默认为*,查询表中所有字段,也可以自定义字段替换
     * %tableName -> 表名
     */
    private final static String SQL_SELECT_DISTINCT = " select distinct * from %tableName where 1=1";

    /**
     * 添加一条数据
     * %tableName -> 表名
     * %params -> 参数集
     * %values -> 值集
     */
    private final static String SQL_INSERT_ONE = "insert into %tableName ( %params ) value ( %values )";

    /**
     * 添加多条数据从某个表中
     * %tableNameIn -> 数据加入的表名
     * %params -> 字段名
     * %originParams -> 源字段名
     * %tableNameFrom -> 源数据库表名
     */
    private final static String SQL_INSERT_ALL = "insert into %tableNameIn ( %params ) select %originParams from %tableNameFrom ";

    /**
     * 字段修改语句
     * %tableName -> 表名
     * %keyMap -> 对应的键值属性
     */
    private final static String SQL_UPDATE_BASE = "update %tableName set %keyMap where 1=1";

    /**
     * 删除操作语句
     * 注意:!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *      请勿单独执行此语句,执行此语句请务必增加条件,负责会数据全删.
     * %tableName -> 表名称
     */
    private final static String SQL_DELETE_BASE = "delete from %tableName where 1=1";

    /**
     * and连接符,拼接条件
     * %condition -> 链接条件
     */
    private final static String SQL_WHERE_AND = " and %condition";

    /**
     * or连接符,拼接条件
     * %condition -> 链接条件
     */
    private final static String SQL_WHERE_OR = " or %condition";

    /**
     * 创建一个表格
     * @param tableName 表名
     * @param isSetSystemId 是否设置系统主键
     * @param params 参数及其类型集(系统自动拼接",")
     * @return 生成创建表的字符串
     */
    public static String createTable(String tableName, boolean isSetSystemId, String... params){
        StringBuilder otherParams = new StringBuilder();
        for (String param:params) {
            otherParams.append(param).append((param.contains(",") ? " " : ", "));
        }
        //删除最后一个","
        otherParams.deleteCharAt(otherParams.length()-2);
        return CREATE_TABER_SQL
                .replace("%tableName",tableName)
                .replace("%systemId",isSetSystemId?SQL_SYSTEM_ID:"")
                .replace("%otherParams", otherParams.toString());
    }

    /**
     * 获取查询语句
     * @param tableName 表名
     * @param params 字段参数(a, b, c,)
     * @param whereString 条件参数,使用and进行拼接
     * @return 生成的sql语句
     */
    public static String select(String tableName,String params,String... whereString){
        StringBuilder sql = new StringBuilder();
        String _params = "*";
        if (params!=null&&!params.trim().equals("")){
            _params = params;
        }
        String heard = SQL_SELECT_BASE.replace("*",_params).replace("%tableName",tableName);
        sql.append(heard);
        for (String where:whereString) {
            sql.append(SQL_WHERE_AND.replace("%condition",where));
        }
        return sql.toString();
    }

    /**
     * 获取不重复查询语句
     * @param tableName 表名
     * @param params 字段参数(a, b, c,)
     * @param whereString 条件参数,使用and进行拼接
     * @return 生成的sql语句
     */
    public static String selectDistinct(String tableName,String params,String... whereString){
        StringBuilder sql = new StringBuilder();
        String _params = "*";
        if (params!=null&&!params.trim().equals("")){
            _params = params;
        }
        String heard = SQL_SELECT_DISTINCT.replace("*",_params).replace("%tableName",tableName);
        sql.append(heard);
        for (String where:whereString) {
            sql.append(SQL_WHERE_AND.replace("%condition",where));
        }
        return sql.toString();
    }
}
