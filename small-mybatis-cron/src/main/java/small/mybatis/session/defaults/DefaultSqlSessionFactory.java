package small.mybatis.session.defaults;

import small.mybatis.building.MapperRegistry;
import small.mybatis.session.SqlSession;
import small.mybatis.session.SqlSessionFactory;

/**
 * @Auther: hehongyi
 * @Date: 2023/12/26 19:15
 * @Description: 默认的 DefaultSqlSessionFactory
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    /**
     * 打开一个 session
     *
     * @return SqlSession
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
