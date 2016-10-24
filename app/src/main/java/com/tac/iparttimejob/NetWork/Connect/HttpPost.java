package com.tac.iparttimejob.NetWork.Connect;

/**
 * Created by wait。 on 2016/10/24.
 * HTTP发送请求
 */
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.OutputStream;

import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Map;


public class HttpPost {
    public static void post(final String address, final Map<String,String>params, final HttpCallBackListener listener){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection connection = null;

                    try {
                        StringBuffer str=new StringBuffer(); //存储封装好的请求体信息
                        if(params!=null&&!params.isEmpty())
                        {
                          for (Map.Entry<String,String>entry:params.entrySet())
                          {
                              str.append(entry.getKey()).
                                      append("=").
                                      append(URLEncoder.encode(entry.getValue(),"UTF-8"))
                                      .append("&");
                          }
                        }
                        str.deleteCharAt(str.length()-1); //删除最后一个字符&
                        byte[] data=str.toString().getBytes();
                        URL url = new URL(address);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");    //设置以Post方式提交数据
                        connection.setConnectTimeout(8000); //设置连接超时时间
                        connection.setReadTimeout(8000);
                        connection.setDoInput(true);         //打开输入流，以便从服务器获取数据
                        connection.setDoOutput(true);            //打开输出流，以便向服务器提交数
                        OutputStream outputStream=connection.getOutputStream();
                        outputStream.write(data);               //提交数据
                        InputStream inPutStream=connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new  InputStreamReader(inPutStream,"UTF-8"));
                        StringBuilder result = new StringBuilder();  //返回结果
                        String line;
                        while((line = reader.readLine()) != null){
                            result.append(line);
                        }
                        if (listener != null) {
    // 回调onFinish()方法
                            listener.onFinish(result.toString());
                        }
                    } catch (Exception e) {
                        if (listener != null) {
    // 回调onError()方法
                            listener.onError(e.toString());
                        }
                    } finally {
                        if (connection != null) {
                            connection.disconnect();
                        }
                    }
                }
            }).start();
    }
}
