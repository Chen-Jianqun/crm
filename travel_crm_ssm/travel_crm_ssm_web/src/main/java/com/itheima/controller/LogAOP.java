package com.itheima.controller;

import com.itheima.entity.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAOP {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//访问时间
    private Class clazz;//访问的类
    private Method method;//访问的方法
    private String url;//访问路径
    private String ip;//访问ip
    private String username;//访问者

    //前置通知，主要是获取时间，执行的类是哪一个，执行的方法是哪一个
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp)throws Exception{
        visitTime=new Date();
        clazz=jp.getTarget().getClass();
        Object[] methodArgs=jp.getArgs();
        if (methodArgs==null ||  methodArgs.length==0){
            method=clazz.getMethod(jp.getSignature().getName());
        }else{
            Class[] argsClass=new Class[methodArgs.length];
            for (int i=0;i<argsClass.length;i++){
                argsClass[i]=methodArgs[i].getClass();
            }
            method=clazz.getMethod(jp.getSignature().getName(),argsClass);
        }

    }

    //后置通知，获取方法执行时长，用户，ip，url
    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint jp)throws Exception{
        Long exeTime=new Date().getTime()-visitTime.getTime();//执行时长，通过后置通知执行时间减去前置通知执行时间

        if(clazz!=null&&method!=null&&clazz!=LogAOP.class){
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation!=null){
                String classValue = classAnnotation.value()[0];
                RequestMapping methodAnnotation = (RequestMapping) method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String methodValue = methodAnnotation.value()[0];
                    url=classValue+methodValue;
                    /**
                     *  获取ip就需要获取一个request对象
                     *  可以在web.xml里面配置一个监听器，这是一个spring提供的监听器
                     *  <listener>
                     *     <listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
                     *  </listener>
                     *  可以返回一个当前的request对象
                     */
                    ip=request.getRemoteAddr();

                    /**
                     * 获取操作者信息可以通过SpringSecurity来获得
                     * 因为springsecurity控制权限，所有用户的操作都需要经过它
                     * 那么该用户的所有信息，它是掌握的
                     */
                    SecurityContext context= SecurityContextHolder.getContext();
                    User user=(User) context.getAuthentication().getPrincipal();
                    username = user.getUsername();

                    //进行日志对象的封装
                    SysLog sysLog=new SysLog();
                    sysLog.setExecutionTime(exeTime);
                    sysLog.setIp(ip);
                    sysLog.setMethod(clazz.getName()+"/"+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setUsername(username);

                    //调用业务层存储日志
                    sysLogService.save(sysLog);
                }
            }
        }

    }
}
