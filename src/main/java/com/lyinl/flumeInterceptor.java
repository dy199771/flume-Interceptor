package com.lyinl;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

public class flumeInterceptor implements Interceptor {

    public void initialize() {
    }

    public Event intercept(Event event) {
        String body = new String(event.getBody());
        //获取heads
        Map<String, String> headers = event.getHeaders();
        //判断
        if(body.contains("adu")){
            headers.put("fs","yes");
        }
        else {
            headers.put("fs","no");
        }
        return event;
    }


    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list ;
    }

    public void close() {

    }
    public static class myBuild implements Builder{
        //创建拦截器对象
        public Interceptor build() {

            return new flumeInterceptor();
        }

        public void configure(Context context) {

        }
    }
}
