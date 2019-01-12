package com.qb.wxbase.create.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.RequiresApi;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/12
 * 包    名：com.qb.wxbase.create.sql
 * 描    述：数据库操作
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
public class FxDbOperation extends SQLiteOpenHelper{
    /**
     * 构造方法
     * @param context 上下文
     * @param name 数据库名称
     * @param factory 查询条件
     * @param version 数据库版本号
     */
    public FxDbOperation(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public FxDbOperation(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = 28)
    public FxDbOperation(Context context, String name, int version, SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    /**
     * 当数据库被第一次创建时被调用的的方法（类似于Activity的onCreate()）
     * @param db SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");
    }

    /**
     * 数据库已经被成打开后被调用。
     * @param db SQLiteDatabase
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    /**
     * 当数据库需要被更改（更新）时被调用。
     * @param db SQLiteDatabase
     * @param oldVersion 旧版本号
     * @param newVersion 新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
