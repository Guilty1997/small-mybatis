package small.mybatis.building.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import small.mybatis.building.MapperProxyFactory;
import small.mybatis.building.test.dao.IUserDao;

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
    public void testMapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("small.mybatis.building.test.dao.IUserDao.queryUser", "查询Sql语句执行");
        IUserDao userDao = factory.newInstance(sqlSession);
        String s = userDao.queryUser("1");
        logger.info(s);

    }

    @Test
    public void testProxy() {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class},
                (proxy, method, args) -> "你被代理了"
        );

        String s = userDao.queryUser("1");
        logger.info(s);

    }


}
