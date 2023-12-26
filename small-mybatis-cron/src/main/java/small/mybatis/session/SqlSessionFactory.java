package small.mybatis.session;

/**
 * @Auther: hehongyi
 * @Date: 2023/12/26 19:05
 * @Description:
 */
public interface SqlSessionFactory {

    /**
     * 打开一个 session
     *
     * @return SqlSession
     */
    SqlSession openSession();
}
