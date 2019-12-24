package part01.lesson15.task01.work;

import part01.lesson15.task01.pojo.User;
import part01.lesson15.task01.pojo.Users;

/**
 * create new user
 * @author Oleg_Chapurin
 */
public class FactoryUsersPostgres implements FactoryUsers {

    /**
     * get new instance
     * @return Object implements User
     */
    @Override
    public Users newInstance() {
        return new User();
    }

    /**
     * get new instance by name
     * @return Object implements User
     */
    @Override
    public Users newInstance(String name) {
        return new User(name);
    }
}
