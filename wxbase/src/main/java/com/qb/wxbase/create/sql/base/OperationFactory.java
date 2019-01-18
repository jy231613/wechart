package com.qb.wxbase.create.sql.base;

import android.content.Context;

import com.qb.wxbase.create.sql.note.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/16
 * 包    名：com.qb.wxbase.create.sql.base
 * 描    述：操作类工厂
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class OperationFactory {

    /**
     * 数据库操作对象集合
     */
    private static Map<String,FxDbOperation> operationMap ;

    /**
     * 工厂单例对象
     */
    private static OperationFactory factory;
    private OperationFactory(Context context){
        operationMap = new HashMap<>();
        for (Class<?> cls:SqlScan.scanTable(context)) {
            Table table = cls.getAnnotation(Table.class);
            if (table!=null){
                //创建操作类并添加到集合中
                FxDbOperation fxDbOperation = DbOperation.createDbOperation(context,cls);
                String tabName = table.value().equals("")?cls.getSimpleName():table.value();
                if (fxDbOperation != null)operationMap.put(tabName,fxDbOperation);
            }
        }
    }

    /**
     * 初始化工厂类
     * @param context 上下文关系
     */
    public static void initFactory(Context context) {
        if (factory == null)factory = new OperationFactory(context);
    }

    /**
     * 获取一个单例工厂对象
     * @return OperationFactory
     */
    public static OperationFactory getFactory() {
        return factory;
    }

    /**
     * 获取一个操作类,根据一个key
     * @param tClass key的类
     * @return FxDbOperation
     */
    public FxDbOperation getOperation(Class<?> tClass){
        Table table = tClass.getAnnotation(Table.class);
        if (table==null)return null;
        return operationMap.get(table.value().equals("")?tClass.getSimpleName():table.value());
    }

}
