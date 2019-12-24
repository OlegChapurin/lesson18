package lesson.task.work;

import lesson.task.pojo.User;
import lesson.task.pojo.Users;

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
