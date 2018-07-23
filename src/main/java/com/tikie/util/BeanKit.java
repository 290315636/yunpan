package com.tikie.util;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
public class BeanKit implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public BeanKit() {
    }

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static void setApplication(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clz) {
        return applicationContext.getBean(beanName, clz);
    }

    public static <T> T getBean(Class<T> clz) {
        return applicationContext.getBean(clz);
    }

    public static String[] getBeanNamesForType(Class<?> clz) {
        return applicationContext.getBeanNamesForType(clz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clz) {
        return applicationContext.getBeansOfType(clz);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> clz) {
        return applicationContext.getBeansWithAnnotation(clz);
    }

    public static DefaultListableBeanFactory getBeanFactory() {
        return (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();
    }

    public static boolean destroy(String beanName) {
        DefaultListableBeanFactory beanFactory = getBeanFactory();
        if (!beanFactory.containsBean(beanName)) {
            return false;
        } else {
            beanFactory.destroySingleton(beanName);
            beanFactory.destroyBean(beanName);
            beanFactory.removeBeanDefinition(beanName);
            return true;
        }
    }

    public static <T> T regist(String beanName, Class<T> clz) {
        DefaultListableBeanFactory beanFactory = getBeanFactory();
        if (!beanFactory.containsBean(beanName)) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(clz);
            beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        }

        return getBean(beanName, clz);
    }

    public static Object regist(String beanName, BeanDefinitionBuilder beanDefinitionBuilder) {
        DefaultListableBeanFactory beanFactory = getBeanFactory();
        if (!beanFactory.containsBean(beanName)) {
            beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        }

        return getBean(beanName);
    }
}
