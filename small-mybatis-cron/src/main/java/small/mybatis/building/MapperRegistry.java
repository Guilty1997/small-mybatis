package small.mybatis.building;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.hutool.core.lang.ClassScanner;
import small.mybatis.session.SqlSession;

/**
 * @Auther: hehongyi
 * @Date: 2023/12/26 18:02
 * @Description: mapper注册器
 */
public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();


    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }

    }

    public <T> void addMapper(Class<T> type) {
        /* Mapper 必须是接口才会注册 */
        if (type.isInterface()) {
            if (hasMapper(type)) {
                //如果重复添加报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }

            knownMappers.put(type, new MapperProxyFactory<>(type));
        }

    }

    private <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public void addMappers(String packageName) {
        //扫描包上的所有接口
        Set<Class<?>> classes = ClassScanner.scanPackage(packageName);
        for (Class<?> type : classes) {
            addMapper(type);
        }


    }

}
