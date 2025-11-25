package bean;

import cn.mini.beans.factory.*;
import cn.mini.context.ApplicationContext;

public class UserService  implements IUserService {


    private String uId;
    private String company;
    private String location;
    private UserDao userDao;
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;


    @Override
    public String getUid() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
