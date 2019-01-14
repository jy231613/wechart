package com.qb.wechat.aax;

import com.qb.wxbase.create.sql.note.Alias;
import com.qb.wxbase.create.sql.note.Param;
import com.qb.wxbase.create.sql.note.SystemId;
import com.qb.wxbase.create.sql.note.Table;
import com.qb.wxbase.create.sql.note.Useless;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechat
 * 日    期：2019/1/14
 * 包    名：com.qb.wechat.sql
 * 描    述：
 * Create by Administrator from AndroidStudio3.2
 * ================================================
 */
@Table("hello_world")
public class HelloWorld {
    @SystemId()
    private int id;
    @Param
    private String name;
    @Param
    private int age;

    @Alias("nick_name")
    private String nickName;

    @Useless
    private String no;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
