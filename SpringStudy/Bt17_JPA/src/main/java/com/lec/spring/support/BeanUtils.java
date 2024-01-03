package com.lec.spring.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// ApplicationContextAware 를 implement 하면
// ApplicationContext (IoC 컨테이너)를 가져올수 있다.
@Component
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware#setApplicationContext() 호출");
        BeanUtils.applicationContext = applicationContext;
    }

    // ApplicationContext 에서 T 타입 Bean 을 받아오기
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
