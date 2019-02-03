package com.qb.wxbase.create.sql.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.qb.wxbase.create.sql.base.norm.DbO;
import com.qb.wxbase.create.sql.exception.NoPrimaryKeyException;
import com.qb.wxbase.create.sql.note.Alias;
import com.qb.wxbase.create.sql.note.Param;
import com.qb.wxbase.create.sql.note.SystemId;
import com.qb.wxbase.create.sql.note.Table;
import com.qb.wxbase.create.sql.note.Useless;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/15
 * 包    名：com.qb.wxbase.create.sql.base
 * 描    述：数据库操作类
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public final class DbOperation<T> implements DbO<T> {

    /**
     * 获取一个数据库操作对象
     *
     * @param context 上下文关系
     * @param tClass  相关类
     * @param <T>     表类型
     * @return FxDbOperation操作对象
     */
    public static <T> FxDbOperation createDbOperation(Context context, Class<T> tClass) {
        Table table = tClass.getAnnotation(Table.class);
        if (table == null) return null;
        return new FxDbOperation(context, table.value().equals("") ? tClass.getSimpleName() : table.value(), null, SqlScan.configModel.getVersion());
    }

    /**
     * 数据库操作对象
     */
    private FxDbOperation operation;

    /**
     * 构造方法初始化一个数据库对象
     *
     * @param operation 数据库对象
     */
    public DbOperation(FxDbOperation operation) {
        this.operation = operation;
    }

    @Override
    public List<T> select(Class<T> tClass, int id) {
        List<T> ts = select(tClass, " id = ?", new String[]{String.valueOf(id)});
        if (ts != null && ts.size() > 0) {
            return ts;
        } else return null;
    }

    @Override
    public List<T> select(Class<T> tClass, String where, String[] values) {
        Field[] fields = tClass.getDeclaredFields();
        String systemParamName = null;
        List<String> fieList = new ArrayList<>();
        for (Field fie : fields) {
            if (fie.getAnnotation(Useless.class) != null) continue;
            SystemId systemId = fie.getAnnotation(SystemId.class);
            if (systemId != null) {
                systemParamName = fie.getName();
                fieList.add("id");
                continue;
            }
            Alias alias = fie.getAnnotation(Alias.class);
            Param param = fie.getAnnotation(Param.class);
            if (param != null) {
                fieList.add(param.value().equals("") ? fie.getName() : param.value());
                continue;
            }
            if (alias != null) {
                fieList.add(alias.value().equals("") ? fie.getName() : alias.value());
            }
        }
        if (systemParamName == null) try {
            throw new NoPrimaryKeyException();
        } catch (NoPrimaryKeyException e) {
            e.printStackTrace();
        }
        return select(tClass, fieList.toArray(new String[]{}), where, values);
    }

    @Override
    public List<T> select(Class<T> tClass, String[] params, String where, String[] values) {

        Table table = tClass.getAnnotation(Table.class);
        if (table != null) {
            //结果集
            List<T> ts = new ArrayList<>();
            //获取表名
            String tableName = table.value().equals("") ? tClass.getSimpleName() : table.value();
            //获取数据库对象
            SQLiteDatabase db = operation.getReadableDatabase();
            Cursor cursor = db.query(tableName, params, where, values, null, null, null);
            //遍历结果集
            if (cursor.moveToFirst()) {
                try {
                    do {
                        T obj = tClass.newInstance();
                        for (Field fie : tClass.getDeclaredFields()) {
                            if (fie.getAnnotation(Useless.class) != null) continue;
                            Alias alias = fie.getAnnotation(Alias.class);
                            Param param = fie.getAnnotation(Param.class);
                            if (fie.getAnnotation(SystemId.class) != null) {
                                setValue(fie, cursor, "id", obj);
                                continue;
                            }
                            if (param != null) {
                                setValue(fie, cursor, param.value().equals("") ? fie.getName() : param.value(), obj);
                                continue;
                            }
                            if (alias != null) {
                                setValue(fie, cursor, alias.value().equals("") ? fie.getName() : alias.value(), obj);
                            }
                        }
                        //添加到结果集
                        ts.add(obj);
                    } while (cursor.moveToNext());
                    //释放元素
                    cursor.close();
                    return ts;
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(T t) {
        return update(t, "id = ?", null);
    }

    @Override
    public boolean update(T t, String where, String[] values) {
        //修改操作
        Table table = t.getClass().getAnnotation(Table.class);
        if (table != null) {
            Field[] fields = t.getClass().getDeclaredFields();
            ContentValues val = new ContentValues();
            String systemIdValue = "";
            try {
                for (Field fie : fields) {
                    if (fie.getAnnotation(Useless.class) != null) continue;
                    if (fie.getAnnotation(SystemId.class) != null) {
                        systemIdValue = String.valueOf(fie.get(t));
                        continue;
                    }
                    Alias alias = fie.getAnnotation(Alias.class);
                    Param param = fie.getAnnotation(Param.class);
                    if (alias != null) {
                        if (fie.get(t) != null) val.put(fie.getName(), String.valueOf(fie.get(t)));
                        continue;
                    }
                    if (param != null) {
                        if (fie.get(t) != null) val.put(fie.getName(), String.valueOf(fie.get(t)));
                    }
                }
                int number;
                //获取数据库对象
                SQLiteDatabase db = operation.getWritableDatabase();
                if (values == null) {
                    number = db.update(table.value().equals("") ? t.getClass().getSimpleName() : table.value(), val, where, new String[]{systemIdValue});
                } else {
                    number = db.update(table.value().equals("") ? t.getClass().getSimpleName() : table.value(), val, where, values);
                }
                if (number > 0) return true;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 给参数赋值
     *
     * @param fie    参数
     * @param cursor 数据源
     * @param fName  参数名
     * @param obj    参数所在对象
     * @throws IllegalAccessException 加载异常
     */
    private void setValue(Field fie, Cursor cursor, String fName, T obj) throws IllegalAccessException {
        //赋值
        Type type = fie.getType();
        if (type == int.class) {
            if (cursor.getString(cursor.getColumnIndex(fName)) != null)
                fie.set(obj, Integer.valueOf(cursor.getString(cursor.getColumnIndex(fName))));
        } else if (type == double.class) {
            if (cursor.getString(cursor.getColumnIndex(fName)) != null)
                fie.set(obj, Double.valueOf(cursor.getString(cursor.getColumnIndex(fName))));
        } else if (type == boolean.class) {
            if (cursor.getString(cursor.getColumnIndex(fName)) != null)
                fie.set(obj, Boolean.valueOf(cursor.getString(cursor.getColumnIndex(fName))));
        } else if (type == float.class) {
            if (cursor.getString(cursor.getColumnIndex(fName)) != null)
                fie.set(obj, Float.valueOf(cursor.getString(cursor.getColumnIndex(fName))));
        } else {
            if (cursor.getString(cursor.getColumnIndex(fName)) != null)
                fie.set(obj, cursor.getString(cursor.getColumnIndex(fName)));
        }
    }


    @Override
    public boolean insert(T t) {
        boolean isSuccess = false;
        Table table = t.getClass().getAnnotation(Table.class);
        if (table != null) {
            Field[] fields = t.getClass().getDeclaredFields();
            // 添加数据
            ContentValues values = new ContentValues();
            StringBuilder paramsStr = new StringBuilder();
            try {
                for (Field fie : fields) {
                    if (fie.getAnnotation(Useless.class) != null) continue;
                    SystemId systemId = fie.getAnnotation(SystemId.class);
                    if (systemId != null) {
                        if (systemId.value()) {
                            continue;
                        } else {
                            values.put("id", String.valueOf(fie.get(t)));
                            paramsStr.append("id,");
                        }
                    }
                    Alias alias = fie.getAnnotation(Alias.class);
                    Param param = fie.getAnnotation(Param.class);
                    if (alias != null) {
                        values.put(alias.value().equals("") ? fie.getName() : alias.value(), String.valueOf(fie.get(t)));
                        paramsStr.append(alias.value().equals("") ? fie.getName() : alias.value()).append(",");
                        continue;
                    }
                    if (param != null) {
                        values.put(param.value().equals("") ? fie.getName() : param.value(), String.valueOf(fie.get(t)));
                        paramsStr.append(param.value().equals("") ? fie.getName() : param.value()).append(",");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //获取表名
            String tableName = table.value().equals("") ? t.getClass().getSimpleName() : table.value();
            //获取数据库对象
            SQLiteDatabase db = operation.getWritableDatabase();
            long number = db.insert(tableName, paramsStr.substring(0, paramsStr.length() - 1), values);
            if (number > 0) isSuccess = true;
        }
        return isSuccess;
    }

    @Override
    public boolean insert(List<T> ts) {
        for (T t : ts) {
            insert(t);
        }
        return true;
    }

    @Override
    public boolean del(Class<T> t, String where, String[] values) {
        Table table = t.getAnnotation(Table.class);
        if (table != null) {
            //获取数据库对象
            SQLiteDatabase db = operation.getWritableDatabase();
            int number = db.delete(table.value().equals("") ? t.getSimpleName() : table.value(), where, values);
            return number > 0;
        }else{
            Log.d("TAG", "del: >>> table为空");
        }
        return false;
    }

    @Override
    public boolean del(Class<T> t, String id) {
        return del(t, " id = ? ", new String[]{id});
    }
}
