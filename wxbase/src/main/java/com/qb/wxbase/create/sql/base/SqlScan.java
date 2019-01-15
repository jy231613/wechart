package com.qb.wxbase.create.sql.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.qb.wxbase.create.sql.SqlConfig;
import com.qb.wxbase.create.sql.exception.NoPrimaryKeyException;
import com.qb.wxbase.create.sql.model.ConfigModel;
import com.qb.wxbase.create.sql.note.Alias;
import com.qb.wxbase.create.sql.note.DefaultValue;
import com.qb.wxbase.create.sql.note.Param;
import com.qb.wxbase.create.sql.note.SystemId;
import com.qb.wxbase.create.sql.note.Table;
import com.qb.wxbase.create.sql.note.Useless;
import com.qb.wxbase.json.Json;
import com.qb.wxbase.util.apkutil.ClassUtil;
import com.qb.wxbase.util.apkutil.SystemUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import dalvik.system.DexFile;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/14
 * 包    名：com.qb.wxbase.create.sql.base
 * 描    述：系统扫描处理类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class SqlScan {
    public static ConfigModel configModel = null;
    private static String sqlSss = "tables.json";

    /**
     * 扫描数据库表类
     * @param context 上下文对象
     * @return Class集合
     */
    public static List<Class<?>> scanTable(Context context) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            //判断是否已经读取配置文件
            if (configModel != null) {
                List<String> tables = Json.array(SystemUtils.getJson(context, sqlSss), String.class);
                for (String cls : tables) {
                    //过滤掉是否是数据库表的文件
                    Table table = Class.forName(cls).getAnnotation(Table.class);
                    if (table != null) {
                        classes.add(Class.forName(cls));
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 创建 sql ，并执行
     * @param classes 表对应的类的集合
     */
    public static void createSql(List<Class<?>> classes,Context context) {
        for (Class<?> cls : classes) {
            //处理当前表数据类
            Field[] fields = cls.getDeclaredFields();
            int isHaveId = -1;
            List<String> params = new ArrayList<>();
            for (Field fie : fields) {
                //处理表中的每一个参数
                Useless useless = fie.getAnnotation(Useless.class);//是否是无用参数
                //处理无用参数
                if (useless != null) {
                    continue;
                }
                SystemId systemId = fie.getAnnotation(SystemId.class);//是否存在主键且是数据库字段
                //处理是否存在主键
                if (systemId != null) {
                    isHaveId = systemId.value() ? 0 : 1;
                    continue;
                }
                //处理是否存在别名
                Alias alias = fie.getAnnotation(Alias.class);//是否存在别名且是数据库字段
//                            DefaultValue defaultValue = fie.getAnnotation(DefaultValue.class);//是否存在默认值
                if (alias != null) {
                    params.add(((alias.value().equals("") ? fie.getName() : alias.value()) + " " + typeString(fie.getGenericType())));
                    continue;
                }
                //处理其他参数
                Param param = fie.getAnnotation(Param.class);//是否是数据库字段
                if (param != null) {
                    params.add(((param.value().equals("") ? fie.getName() : param.value()) + " " + typeString(fie.getGenericType())));
                }
                Log.d("foxsql", "scanTable: >>" + fie.getName());
            }
            if (isHaveId == -1) try {
                throw new NoPrimaryKeyException();
            } catch (NoPrimaryKeyException e) {
                e.printStackTrace();
            }
            String tableName = cls.getAnnotation(Table.class).value().equals("") ? cls.getSimpleName() : cls.getAnnotation(Table.class).value();
            String SQL = SqlConfig.createTable(tableName, isHaveId == 0, params.toArray(new String[]{}));
            Log.d("foxsql", "scanTable: >>>" + SQL);
            FxDbOperation operation = new FxDbOperation(context,tableName,null,configModel.getVersion(),SQL);
            SQLiteDatabase db = operation.getWritableDatabase();
            if (db!=null) Log.d("foxsql", "createSql: >> 数据库创建成功！");
            db = null;
        }
    }

    /**
     * 读取配置文件
     *
     * @param context 上下文关系
     */
    public static void readerConfig(Context context) {
        //读取配置文件
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(SystemUtils.getXmlInPut(context, ConfigModel.configName));
            configModel = new ConfigModel();
            Element element = document.getDocumentElement();
            Element nameElement = (Element) element.getElementsByTagName("scanName").item(0);
            Element catchElement = (Element) element.getElementsByTagName("catchOpen").item(0);
            Element verElement = (Element) element.getElementsByTagName("version").item(0);
            configModel.setScanName(nameElement.getTextContent());
            configModel.setCatchOpen(catchElement.getTextContent().equals("true"));
            configModel.setVersion(Integer.valueOf(verElement.getTextContent()));
            Log.d("foxsql", "readerConfig: >>" + configModel.toString());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类型字符串
     *
     * @param cls Class
     * @return 字符串
     */
    private static String typeString(Type cls) {
        if (cls == int.class || cls == Integer.class) {
            return "integer";
        } else if (cls == double.class || cls == float.class) {
            return "real";
        } else {
            return "text";
        }
    }
}
