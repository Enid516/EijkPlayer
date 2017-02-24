package com.enid.eijkplayer.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by big_love on 2017/2/22.
 */

public class HttpUtils {
    private static HttpUtils INSTANCE;
    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HttpUtils();
        }
        return INSTANCE;
    }

    public String get(String requestUrl) throws IOException {
        //用于存放请求结果数据
        StringBuilder stringBuilder = new StringBuilder();
        //URL
        URL url = new URL(requestUrl);
        //获取HttpURLConnection 对象
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //设置超时时间
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        //设置请求方式
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        //获取请求返回输入流
        InputStream inputStream = connection.getInputStream();
        //读取输入流的数据
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1){
            stringBuilder.append(new String(buffer,0,len));
        }
        //关闭连接
        connection.disconnect();
        return stringBuilder.toString();
    }
}
