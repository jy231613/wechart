package com.qb.wxbase.create.sql;

import android.content.Context;

import com.qb.wxbase.create.sql.base.SqlScan;
import com.qb.wxbase.create.sql.note.MakeClass;
import com.qb.wxbase.create.sql.note.Table;
import com.qb.wxbase.json.Json;
import com.qb.wxbase.util.apkutil.ClassUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/14
 * 包    名：com.qb.wxbase.create.sql
 * 描    述：FoxSql
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class FS {
    private final static String LOAD_JSON_CONFIG_PATH = "./app/src/main/assets/tables.json";

    /**
     * 初始化方法
     * @param context 上下文对象
     */
    public static void init(Context context){
        //读取配置文件
        SqlScan.readerConfig(context);
        //扫描包下的表
        SqlScan.createSql(SqlScan.scanTable(context),context);
    }

    /**
     * 销毁释放资源
     */
    public static void destroy(){
    }

    /**
     * 在main方法中调用此方法用来生成扫描注解包下的类
     * @param thisCls 当前调用main方法的类对象
     */
    public static void makeOn(Class<?> thisCls){
        MakeClass makeClass = thisCls.getAnnotation(MakeClass.class);
        if (makeClass!=null){
            try {
                List<String> strings = new ArrayList<>();
                for (Class<?> cls : ClassUtil.getClasses(makeClass.value())) {
                    //过滤掉带有配置De
                    Table table = cls.getAnnotation(Table.class);
                    if (table!=null){
                        strings.add(cls.getName());
                    }
                    System.out.println(cls.getName());
                }
                File news = new File(LOAD_JSON_CONFIG_PATH);
                if (news.exists()) {
                    //存在的话删除重建
                    news.delete();
                }
                //创建这个文件
                news.createNewFile();
                //数据写入文件中
                FileWriter fw = new FileWriter(LOAD_JSON_CONFIG_PATH);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(Json.toJson(strings));
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
