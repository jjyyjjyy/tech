package com.example;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.lang.reflect.Field;

/**
 * @author jy
 */
public class MybatisApplication {

    public static void main(String[] args) {

        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql:mybatis-demo");
        dataSource.setUsername("jy");
        dataSource.setPassword("123456");

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        configuration.addInterceptor(new PagingInterceptor());
        configuration.addMapper(UserMapper.class);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.findAll(new RowBounds(0, 100)).forEach(System.out::println);

        userMapper.deleteUser(2);
    }

    @Intercepts(@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
    public static class PagingInterceptor implements Interceptor {

        @Override
        public Object intercept(Invocation invocation) throws Throwable {

            Object[] args = invocation.getArgs();
            RowBounds rowBounds = (RowBounds) args[2];

            if (rowBounds == RowBounds.DEFAULT) {
                return invocation.proceed();
            }

            MappedStatement mappedStatement = (MappedStatement) args[0];
            BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
            String sql = boundSql.getSql();
            sql += String.format(" LIMIT %d OFFSET %d", rowBounds.getLimit(), rowBounds.getOffset());

            StaticSqlSource sqlSource = new StaticSqlSource(mappedStatement.getConfiguration(), sql, boundSql.getParameterMappings());

            Field field = MappedStatement.class.getDeclaredField("sqlSource");
            field.setAccessible(true);
            field.set(mappedStatement, sqlSource);

            return invocation.proceed();
        }
    }
}
