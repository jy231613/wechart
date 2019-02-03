package com.qb.wxbase.rxsql;

import android.util.Log;

import com.qb.wxbase.app.BaseActivity;
import com.qb.wxbase.app.FoxBaseManagement;
import com.qb.wxbase.create.sql.base.DbOperation;
import com.qb.wxbase.create.sql.base.OperationFactory;
import com.qb.wxbase.create.sql.note.SystemId;
import com.qb.wxbase.rxsql.base.RxSqlSet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/19
 * 包    名：com.qb.wxbase.rxsql
 * 描    述：
 *      异步加载数据并且把数据映射在最上层的Activity中,
 *      如果上层页面没有数据,则不做操作
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public final class RxSqlBinding {

    /**
     * 查询并响应数据到最上层页面
     * @param tClass 类型
     * @param id id
     * @param activityClass 响应页面
     * @param <T> 表类型
     * @return Disposable 可使用此对象终止操作
     */
    public static <T> Disposable select(Class<T> tClass, int id, BaseActivity activityClass) {
        return Observable
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
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        if (activityClass.equals(FoxBaseManagement.getFoxManagement().getNowActivity())){
                            Method[] methods = activityClass.getClass().getDeclaredMethods();
                            for (Method method:methods) {
                                RxSqlSet rxSqlSet = method.getAnnotation(RxSqlSet.class);
                                if (rxSqlSet!=null){
                                    //赋值
                                    if (!rxSqlSet.value().equals(tClass))continue;//排除类型不对的方法
                                    method.invoke(activityClass,t);
                                }
                            }
                        }else{
                            Log.d("TAG", "accept: >>>当前Activity不匹配");
                        }
                    }
                });
    }

    public static <T> Disposable select(Class<T> tClass, String where, String[] values,BaseActivity activityClass) {
        return Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(tClass))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.select(tClass,where,values))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ts -> {
                    if (activityClass.equals(FoxBaseManagement.getFoxManagement().getNowActivity())){
                        Method[] methods = activityClass.getClass().getDeclaredMethods();
                        for (Method method:methods) {
                            RxSqlSet rxSqlSet = method.getAnnotation(RxSqlSet.class);
                            if (rxSqlSet!=null){
                                //赋值
                                if (!rxSqlSet.value().equals(tClass))continue;//排除类型不对的方法
                                method.invoke(activityClass,ts);
                            }
                        }
                    }else{
                        Log.d("TAG", "accept: >>>当前Activity不匹配");
                    }
                });
    }

    public static <T> Disposable select(Class<T> tClass, String[] params, String where, String[] values,BaseActivity activityClass) {
        return Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(tClass))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.select(tClass,params,where,values))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ts -> {
                    if (activityClass.equals(FoxBaseManagement.getFoxManagement().getNowActivity())){
                        Method[] methods = activityClass.getClass().getDeclaredMethods();
                        for (Method method:methods) {
                            RxSqlSet rxSqlSet = method.getAnnotation(RxSqlSet.class);
                            if (rxSqlSet!=null){
                                //赋值
                                if (!rxSqlSet.value().equals(tClass))continue;//排除类型不对的方法
                                method.invoke(activityClass,ts);
                            }
                        }
                    }else{
                        Log.d("TAG", "accept: >>>当前Activity不匹配");
                    }
                });
    }

    /**
     * 修改并响应
     * @param t 表类型
     * @param <T> 表类型
     */
    public static <T> Disposable update(T t,BaseActivity activityClass) {
        return Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(t.getClass()))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.update(t))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (aBoolean){
                        Field[] fields = t.getClass().getDeclaredFields();
                        int id = 0;
                        for (Field fie:fields) {
                            SystemId systemId = fie.getAnnotation(SystemId.class);
                            if (systemId==null)continue;
                            id = (Integer) fie.get(t);
                        }
                        if (id!=0) {
                            select(t.getClass(),id,activityClass);
                        }
                    }
                });
    }

    /**
     * 修改
     * @param t 表对象
     * @param where 条件语句
     * @param values 占位符值
     * @param activityClass 数据响应页面
     * @param <T> 表类型
     */
    public static <T> Disposable update(T t, String where, String[] values,BaseActivity activityClass) {
        return Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(t.getClass()))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.update(t,where,values))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (aBoolean){
                        Field[] fields = t.getClass().getDeclaredFields();
                        int id = 0;
                        for (Field fie:fields) {
                            SystemId systemId = fie.getAnnotation(SystemId.class);
                            if (systemId==null)continue;
                            id = (Integer) fie.get(t);
                        }
                        if (id!=0) {
                            select(t.getClass(),id,activityClass);
                        }
                    }
                });
    }

    /**
     * 存储
     * @param t 表对象
     * @param <T> 表类型
     */
    public static <T> Disposable insert(T t) {
        return Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(t.getClass()))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.insert(t))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {});
    }

    /**
     * 存储
     * @param ts 表对象集合
     * @param <T> 表类型
     */
    public static <T> Disposable insert(List<T> ts) {
        if (ts==null||ts.size()<=0)return null;
        return Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(ts.get(0).getClass()))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.insert(ts))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {});
    }

    /**
     * 删除
     * @param t 表类型
     * @param where 条件语句
     * @param values 占位符值
     * @param <T> 表类型
     */
    public static <T> Disposable del(Class<T> t, String where, String[] values) {
        return Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(t))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.del(t,where,values))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {});
    }

    /**
     * 删除
     * @param t 表类型
     * @param id id
     * @param observer 观察者对象
     * @param <T> 表类型
     */
    public static <T> Disposable del(Class<T> t, String id,Observer<Boolean> observer) {
        return Observable
                .create((ObservableOnSubscribe<DbOperation<T>>) e -> e.onNext(new DbOperation<>(OperationFactory.getFactory().getOperation(t))))
                .subscribeOn(Schedulers.io())
                .map(tDbOperation -> tDbOperation.del(t,id))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {});
    }
}
