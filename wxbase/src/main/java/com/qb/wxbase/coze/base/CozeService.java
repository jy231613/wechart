package com.qb.wxbase.coze.base;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.qb.wxbase.coze.listener.ReceiveMessage;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/21
 * 包    名：com.qb.wxbase.coze.base
 * 描    述：聊天Service类
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public class CozeService extends Service {
    private Binder binder;
    private SocketServer socketServer;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new Binder();//创建
        Log.d("TAG", "onCreate: >> 服务创建成功");
    }

    public class Binder extends android.os.Binder{

        /**
         * 获取SocketServer
         * @return SocketServer
         */
        public SocketServer getSocketServer(){
            return socketServer;
        }

        /**
         * 初始化Socket服务
         * @param receiveMessage 接收消息
         */
        public void initSocketServer(ReceiveMessage receiveMessage){
            if (socketServer==null){
                socketServer = new SocketServer(receiveMessage);
                socketServer.execute();
            }
            else setReceive(receiveMessage);
        }

        /**
         * 重设管理器
         * @param receiveMessage 接收消息
         */
        public void setReceive(ReceiveMessage receiveMessage){
            if (socketServer!=null)socketServer.setReceiveMessage(receiveMessage);
        }

    }
}
