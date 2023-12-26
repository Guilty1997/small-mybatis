package small.mybatis.building.test.dao;

/**
 * @Auther: hehongyi
 * @Date: 2023/12/4 22:32
 * @Description:
 */
public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);
}
