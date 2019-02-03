package com.qb.wxbase.coze.base;

import android.os.AsyncTask;

import com.qb.wxbase.coze.bean.MessageType;
import com.qb.wxbase.coze.bean.SocketHeader;
import com.qb.wxbase.coze.listener.ReceiveMessage;
import com.qb.wxbase.coze.listener.SendMessageAfter;
import com.qb.wxbase.coze.model.SocketTransmit;
import com.qb.wxbase.json.Json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/21
 * 包    名：com.qb.wxbase.coze.base
 * 描    述：Socket服务
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
class SocketServer extends AsyncTask<String,String,String> {
    private Socket socket = null;
    private BufferedReader mReader = null;
    private BufferedWriter mWriter = null;

    //标识第一次消息识别
    private boolean isFirst = true;

    private ReceiveMessage receiveMessage = null;

    public void setReceiveMessage(ReceiveMessage receiveMessage) {
        this.receiveMessage = receiveMessage;
    }

    private String userId;

    /**
     * 初始化参数
     * @param receiveMessage 接收消息监听
     */
    public SocketServer(ReceiveMessage receiveMessage){
        this.receiveMessage = receiveMessage;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            socket = new Socket(SocketConfig.configModel.getIpConfig(),SocketConfig.configModel.getPortConfig());
            mReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            mWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //发送初始化信息
            String line;
            while ((line = mReader.readLine())!=null){
                publishProgress(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        if (values!=null&&values.length>0){
            if (receiveMessage==null)return;
            if (isFirst){
                //第一次消息,接收缓存消息
                isFirst = false;
                List<SocketTransmit> socketTransmits = Json.array(values[0],SocketTransmit.class);
                receiveMessage.receiveCatch(socketTransmits);
            }else{
                SocketTransmit socketTransmit = Json.obj(values[0],SocketTransmit.class);
                if (socketTransmit==null)return;
                switch (socketTransmit.getSocketType()){
                    case MessageType.MESSAGE_USER_GROUP:
                        //群组单独计算
                        receiveMessage.receiveGroup(socketTransmit);
                        break;
                    case MessageType.MESSAGE_USER_USER:
                        //点对点消息
                        receiveMessage.receiveMessage(socketTransmit);
                        break;
                    case MessageType.MESSAGE_EVENT_PUSH:
                        //事件推送
                        receiveMessage.receiveEvent(socketTransmit);
                        break;
                    case MessageType.MESSAGE_STATUS_PUSH:
                        //状态推送
                        receiveMessage.receiveStatus(socketTransmit);
                        break;
                    case MessageType.MESSAGE_SYSTEM_PUSH:
                        //系统推送
                        receiveMessage.receiveSystem(socketTransmit);
                        break;
                    default://其他
                        receiveMessage.receiveMessage(socketTransmit);
                        break;
                }
            }
        }
        super.onProgressUpdate(values);
    }

    /**
     * 发送消息方法
     * @param socketTransmit 消息封装对象
     * @return 停止方法
     */
    public Disposable sendMessage(SocketTransmit socketTransmit,SendMessageAfter sendMessageAfte){
        return Observable.create((ObservableOnSubscribe<SocketTransmit>) e -> {
            mWriter.write(Json.toJson(socketTransmit) + "\n");
            mWriter.flush();
            e.onNext(socketTransmit);
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (sendMessageAfte!=null){
                        sendMessageAfte.afterSend(s);
                    }
                });
    }

    private MessageSend messageSend;

    /**
     * 消息发送内部类
     */
    public class MessageSend{
        private SocketHeader socketHeader;

        private MessageSend(SocketTransmit socketTransmit) {
            socketHeader = new SocketHeader();
            //socketHeader初始化
            socketHeader.setPhoneName(SocketConfig.configModel.getPhoneName());
            socketHeader.setRequestIp(SocketConfig.configModel.getMyIp());
            socketHeader.setRequestKey(socketTransmit.getSocketHeader().getRequestKey());
        }

        /**
         * 发送事件
         */
        public void sendEvent(){

        }

        /**
         * 发送状态
         */
        public void sendStatic(){

        }

        /**
         * 发送初始化消息
         */
        public SocketHeader sendFirst(){
            socketHeader.setSendTime(new Date(System.currentTimeMillis()));
            return socketHeader;
        }

        /**
         * 发送消息
         */
        public void sendMessage(){

        }

        /**
         * 发送信息
         */
        public void sendInfo(){

        }

        /**
         * 发送退出状态
         */
        public void sendLogout(){

        }

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getmReader() {
        return mReader;
    }

    public void setmReader(BufferedReader mReader) {
        this.mReader = mReader;
    }

    public BufferedWriter getmWriter() {
        return mWriter;
    }

    public void setmWriter(BufferedWriter mWriter) {
        this.mWriter = mWriter;
    }
}
