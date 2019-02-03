package com.qb.wxbase.rxsql;

import com.qb.wxbase.create.sql.base.DbOperation;
import com.qb.wxbase.create.sql.base.OperationFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/19
 * 包    名：com.qb.wxbase.rxsql
 * 描    述：异步数据库操作封装类
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public final class RxSql {

    /**
     * 查找
     * @param tClass 数据表类型
     * @param id 数据id
     * @param observer 观察者对象,回调回主线程
     * @param <T> 表类型
     */
    public static <T> void select(Class<T> tClass, int id, Observer<T> observer) {
        Observable
                .create(new ObservableOnSubscribe<DbOperation<T>>() {
                    public void subscribe(ObservableEmitter<DbOperation<T>> e) throws Exception {
                        e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(tClass)));
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<DbOperation<T>, T>() {
                    public T apply(DbOperation<T> tDbOperation) throws Exception {
                        List<T> lists = tDbOperation.select(tClass,id);
                        return lists!=null?lists.get(0):null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 查询操作
     * @param tClass 表类型
     * @param where 条件语句
     * @param values 条件语句中的占位符字段值数组
     * @param observer 观察者对象
     * @param <T> 表数据类型
     */
    public static <T> void select(Class<T> tClass, String where, String[] values,Observer<List<T>> observer) {
        Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(tClass))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.select(tClass,where,values))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 查询操作
     * @param tClass 表类型
     * @param params 要查询的参数名
     * @param where 查询条件
     * @param values 占位符值
     * @param observer 观察者对象
     * @param <T> 表类型
     */
    public static <T> void select(Class<T> tClass, String[] params, String where, String[] values,Observer<List<T>> observer) {
        Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(tClass))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.select(tClass,params,where,values))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 修改
     * @param t 表类型
     * @param observer 观察者对象
     * @param <T> 表类型
     */
    public static <T> void update(T t,Observer<Boolean> observer) {
        Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(t.getClass()))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.update(t))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 修改
     * @param t 表对象
     * @param where 条件语句
     * @param values 占位符值
     * @param observer 观察者对象
     * @param <T> 表类型
     */
    public static <T> void update(T t, String where, String[] values,Observer<Boolean> observer) {
        Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(t.getClass()))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.update(t,where,values))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 存储
     * @param t 表对象
     * @param observer 观察者对象
     * @param <T> 表类型
     */
    public static <T> void insert(T t,Observer<Boolean> observer) {
        Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(t.getClass()))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.insert(t))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 存储
     * @param ts 表对象集合
     * @param observer 观察者对象
     * @param <T> 表类型
     */
    public static <T> void insert(List<T> ts,Observer<Boolean> observer) {
        if (ts==null||ts.size()<=0)return;
        Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(ts.get(0).getClass()))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.insert(ts))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 删除
     * @param t 表类型
     * @param where 条件语句
     * @param values 占位符值
     * @param observer 观察者对象
     * @param <T> 表类型
     */
    public static <T> void del(Class<T> t, String where, String[] values,Observer<Boolean> observer) {
        Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(t))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.del(t,where,values))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 删除
     * @param t 表类型
     * @param id id
     * @param observer 观察者对象
     * @param <T> 表类型
     */
    public static <T> void del(Class<T> t, String id,Observer<Boolean> observer) {
        Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(t))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.del(t,id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
