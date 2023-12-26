package small.mybatis.session.defaults;

import small.mybatis.building.MapperRegistry;
import small.mybatis.session.SqlSession;

/**
 * @Auther: hehongyi
 * @Date: 2023/12/26 19:08
 * @Description: 默认上SqlSession接口实现
 */
public class DefaultSqlSession implements SqlSession {
    /**
     * 映射器注册机
     */
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    /**
     * 根据指定的SqlID获取一条记录的封装对象
     *
     * @param statement sqlID
     * @return Mapped object 封装之后的对象¬
     */
    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你的操作被代理了！" + statement);
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     * 根据指定的SqlID获取一条记录的封装对象，只不过这个方法容许我们可以给sql传递一些参数
     * 一般在实际使用中，这个参数传递的是pojo，或者Map或者ImmutableMap
     *
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return Mapped object
     */
    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你的操作被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    /**
     * Retrieves a mapper.
     * 得到映射器，这个巧妙的使用了泛型，使得类型安全
     *
     * @param type Mapper interface class
     * @return a mapper bound to this SqlSession
     */
    @Override
    public <T> T getMapper(Class<?> type) {
        return (T) mapperRegistry.getMapper(type, this);
    }
}
