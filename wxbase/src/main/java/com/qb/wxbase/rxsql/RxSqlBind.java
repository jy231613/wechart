package com.qb.wxbase.rxsql;

import com.qb.wxbase.app.BaseActivity;
import com.qb.wxbase.create.sql.base.DbOperation;
import com.qb.wxbase.create.sql.base.OperationFactory;
import com.qb.wxbase.rxsql.base.RxResultSet;
import com.qb.wxbase.rxsql.base.RxSqlSet;

import java.lang.reflect.Method;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
public final class RxSqlBind {

    /**
     * 查询遍历
     * @param activityClass 当前Activity
     * @return 控制器
     */
    public static <T> Disposable select(Class<T> tClass,BaseActivity activityClass) {
        return Observable
                .create((ObservableOnSubscribe<Method>) e -> {
                    Method[] methods = activityClass.getClass().getDeclaredMethods();
                    for (Method method:methods) {
                        RxSqlSet rxSqlSet = method.getAnnotation(RxSqlSet.class);
                        if (rxSqlSet!=null){
                            e.onNext(method);
                        };
                    }
                }).subscribeOn(Schedulers.io())
                .map(method -> {
                    RxSqlSet rxSqlSet = method.getAnnotation(RxSqlSet.class);
                    DbOperation<T> dbOperation = new DbOperation<>(OperationFactory.getFactory().getOperation(tClass));
                    RxResultSet<T> resultSet = new RxResultSet<>();
                    resultSet.setMethod(method);
                    if (rxSqlSet.ids()!=0){
                        resultSet.setObjects(dbOperation.select(tClass,rxSqlSet.ids()));
                        resultSet.setKeys(1);
                    }//根据id查询
                    if (!rxSqlSet.where().equals(""))resultSet.setObjects(dbOperation.select(tClass,rxSqlSet.where(),rxSqlSet.values()));//根据条件查询
                    else resultSet.setObjects(dbOperation.select(tClass,"1=1",new String[]{}));//全查
                    if (resultSet.getObjects()==null)resultSet.setObjects(new ArrayList<>());
                    return resultSet;
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(rxResultSet -> {
                    if (rxResultSet.getKeys()==0){
                        rxResultSet.getMethod().invoke(activityClass,rxResultSet.getObjects());
                    }else {
                        rxResultSet.getMethod().invoke(activityClass,rxResultSet.getObjects().get(0));
                    }
                });
    }

}
