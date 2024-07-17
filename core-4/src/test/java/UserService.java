public class UserService {


    private String uId;
    private QueryDao queryDao;

    public UserService(String uId, QueryDao queryDao) {
        this.uId = uId;
        this.queryDao = queryDao;
    }

public void queryUserInfo() {
System.out.println("查询用户信息：" + queryDao.queryUserName(uId));
}
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(uId);
        return sb.toString();
    }
}
