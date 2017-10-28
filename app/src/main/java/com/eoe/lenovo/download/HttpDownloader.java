package com.eoe.lenovo.download;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lenovo on 2016/4/28.
 */
public class HttpDownloader {
    public String download(String urlStr){
        StringBuffer sb=new StringBuffer();
        String line=null;
        BufferedReader buffer=null;
        try {
            //创建一个URL对象
            URL url=new URL(urlStr);
            //创建一个Http连接
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            //使用IO流读取数据
            buffer=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line=buffer.readLine())!=null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                buffer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();

    }
}