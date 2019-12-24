package part01.lesson15.task01.work;

import part01.lesson15.task01.pojo.Users;

/**
 * @author Oleg_Chapurin
 */
public interface FactoryUsers {
    /**
     * get new instance
     * @return Object implements User
     */
    Users newInstance();
    /**
     * get new instance by name
     * @return Object implements User
     */
    Users newInstance(String name);
}
