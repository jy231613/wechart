package com.qb.wxbase.coze.base;

import android.content.Context;
import android.util.Log;

import com.qb.wxbase.coze.model.ConfigModel;
import com.qb.wxbase.util.apkutil.SystemUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * ================================================
 * 作    者：贾恒飞 >>> 17515250730@163.com
 * 项    目：wechart
 * 日    期：2019/1/21
 * 包    名：com.qb.wxbase.coze
 * 描    述：
 * Create by zFox from AndroidStudio3.2
 * ================================================
 */
public class SocketConfig {
    public static ConfigModel configModel = null;

    /**
     * 读取配置文件
     *
     * @param context 上下文关系
     */
    public static void readerConfig(Context context) {
        //读取配置文件
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(SystemUtils.getXmlInPut(context, ConfigModel.configName));
            configModel = new ConfigModel();
            Element element = document.getDocumentElement();
            Element ip = (Element) element.getElementsByTagName("Ip").item(0);
            Element port = (Element) element.getElementsByTagName("Port").item(0);
            Element cache = (Element) element.getElementsByTagName("OpenCache").item(0);
            configModel.setIpConfig(ip.getTextContent());
            configModel.setPortConfig(Integer.valueOf(port.getTextContent()));
            configModel.setCache(Boolean.valueOf(cache.getTextContent()));
            Log.d("foxsql", "readerConfig: >>" + configModel.toString());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}
