# wechart

#### 介绍
仿微信安卓端源码

#### 软件架构

1. 大部分都是我自己写的,边摸索着边写;
2. 网络请求用的okhttp,也没用啥架构,想着写着写着就能理解到一些东西;
3. 封装数据库的时候用了RxJava;
4. 数据库还没有封装完,计划封装成那种根据入口参数自动去访问网络请求,然后自动存储并且回显到前段页面的那种响应式数据库;
5. 目前网络请求没有写,就是保存加回显写了,但是写的不是很满意,跟我预想的不太一样;
6. 聊天部分不打算用三方的,打算自己构建聊天socket,还包括一些服务器与客户端通过socket通道进行的推送和通信,一些频繁请求直接走socket,不再额外添加请求;

#### 使用说明

1. 后台代码ChartsApi;
2. 目前才刚开始写,慢慢的增加东西,可能写着写着就会改许多东西;
3. 有哪些地方不好的请指导我,谢谢;


#### 作者
贾恒飞
