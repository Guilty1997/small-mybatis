package small.mybatis.building.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import small.mybatis.building.MapperProxyFactory;
import small.mybatis.building.MapperRegistry;
import small.mybatis.building.test.dao.IUserDao;
import small.mybatis.session.SqlSession;
import small.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


/**
 * @Auther: hehongyi
 * @Date: 2023/12/4 22:31
 * @Description:
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);


    @Test
    public void test_MapperProxyFactory() {
        //生成mapperRegistry
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("small.mybatis.building.test.dao");

        //获取sqlSession工厂
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = defaultSqlSessionFactory.openSession();

        //生成对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String userName = userDao.queryUserName("111");

        logger.info("userName:{}", userName);

    }


}
