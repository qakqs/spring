public class UserService {


    private String name;
    private QueryDao queryDao;

    public UserService(String name, QueryDao queryDao) {
        this.name = name;
        this.queryDao = queryDao;
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + name);
    }
    public void queryUserName() {
        System.out.println(queryDao.query());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }
}
