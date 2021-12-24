package com.vdc.board.common.logger;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class })
        ,@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

    Object[] args = invocation.getArgs();
    MappedStatement ms = (MappedStatement) args[0];
    Object param = (Object) args[1];
    BoundSql boundSql = ms.getBoundSql(param);
    String query_id = ms.getId();

//    if ( query_id.contains(query_id)) {
//        System.out.println("====================================");
//        System.out.println("1:" + invocation.getMethod().getName());
//        System.out.println("====================================");
//        System.out.println("2:" + ms.getId());
//        System.out.println("====================================");
//        System.out.println("3:" + boundSql.getSql());
//        System.out.println("====================================");
//        System.out.println("4:" + param);
//        System.out.println("====================================");
//        System.out.println("5:" + ms.getId());
//    }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
