package com.example.consumer1.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 断路器配置,当无法调用如下方法时,就会调用自定义的hiError方法
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name)
    {
        Object forObject = restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
        return forObject.toString();
    }

    public String hiError(String name) {
        return "hey" + name + ",there is some problem with hi page";
    }
}
