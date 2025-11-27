package cn.mini.aop.aspectj;

import cn.mini.aop.ClassFilter;
import cn.mini.aop.MethodMatcher;
import cn.mini.aop.Pointcut;
import cn.mini.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }



    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

}
