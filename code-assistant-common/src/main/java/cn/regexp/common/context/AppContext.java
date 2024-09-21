package cn.regexp.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Regexpei
 * @date 2024/8/2 21:32
 * @description 应用上下文容器
 * @since 1.0.0
 */
public class AppContext implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    /**
     * 上下文容器
     */
    private static ApplicationContext applicationContext;

    private static final AppContext INSTANCE = new AppContext();

    private AppContext() {}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppContext.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {}

    public static AppContext getInstance() {
        return INSTANCE;
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }

    /**
     * 根据 beanName 获取 bean
     *
     * @param beanName beanName
     * @return bean
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * 根据类型获取 bean
     *
     * @param clazz 类型字节码对象
     * @param <T>   类型
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据 beanName 和 类型获取 bean
     *
     * @param beanName beanName
     * @param clazz    类型字节码对象
     * @param <T>      类型
     * @return bean
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    /**
     * 根据类型获取多个 bean
     *
     * @param clazz 类型字节码对象
     * @return key 为 beanName，value 为 bean
     */
    public static Map<?, ?> getBeans(Class<?> clazz) {
        Map<String, Object> beanMap = new HashMap<>();
        String[] names = applicationContext.getBeanNamesForType(clazz);
        for (String name : names) {
            beanMap.put(name, applicationContext.getBean(name));
        }
        return beanMap;
    }

    /**
     * 根据注解获取多个 bean
     *
     * @param clazz 注解类型
     * @return key 为 beanName，value 为 bean
     */
    public static Map<?, ?> getBeansByAnnotation(Class<? extends Annotation> clazz) {
        return applicationContext.getBeansWithAnnotation(clazz);
    }
}
