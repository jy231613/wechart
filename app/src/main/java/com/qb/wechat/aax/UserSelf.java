package com.qb.wechat.aax;

import com.qb.wxbase.create.speasy.base.SharedPreferencesTrait;

import java.util.Date;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/2/1
 * 包    名：com.qb.wechat.aax
 * 描    述：用户自身信息保存类
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public class UserSelf extends SharedPreferencesTrait {
    private Integer id;//用户id
    private String username;//用户名
    private String userpic;//用户头像
    private String chartn;//用户嗡嗡号
    private Integer mans;//用户性别
    private String password;//用户密码
    private String phone;//用户手机号
    private String withqq;//用户绑定qq
    private String withemail;//用户绑定email
    private Integer countryn;//用户所在国家
    private Integer provincen;//用户所在省份
    private Integer cityn;//用户所在城市
    private String remake;//用户备注
    private String circlepic;//朋友圈顶部图片
    private Integer status;//用户状态
    private Integer viplv;//用户等级
    private Date createtime;//创建时间
    private Date updatetime;//修改时间
    private Date bannedtime;//账号解封时间
    private String standby;//备注信息

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getChartn() {
        return chartn;
    }

    public void setChartn(String chartn) {
        this.chartn = chartn;
    }

    public Integer getMans() {
        return mans;
    }

    public void setMans(Integer mans) {
        this.mans = mans;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWithqq() {
        return withqq;
    }

    public void setWithqq(String withqq) {
        this.withqq = withqq;
    }

    public String getWithemail() {
        return withemail;
    }

    public void setWithemail(String withemail) {
        this.withemail = withemail;
    }

    public Integer getCountryn() {
        return countryn;
    }

    public void setCountryn(Integer countryn) {
        this.countryn = countryn;
    }

    public Integer getProvincen() {
        return provincen;
    }

    public void setProvincen(Integer provincen) {
        this.provincen = provincen;
    }

    public Integer getCityn() {
        return cityn;
    }

    public void setCityn(Integer cityn) {
        this.cityn = cityn;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getCirclepic() {
        return circlepic;
    }

    public void setCirclepic(String circlepic) {
        this.circlepic = circlepic;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getViplv() {
        return viplv;
    }

    public void setViplv(Integer viplv) {
        this.viplv = viplv;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getBannedtime() {
        return bannedtime;
    }

    public void setBannedtime(Date bannedtime) {
        this.bannedtime = bannedtime;
    }

    public String getStandby() {
        return standby;
    }

    public void setStandby(String standby) {
        this.standby = standby;
    }

    @Override
    public String getSharedXmlName() {
        return "userSelf";
    }
}
