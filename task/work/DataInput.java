package lesson.task.work;

import lesson.task.dao.TableDml;
import lesson.task.dao.TableInputPostgres;
import lesson.task.pojo.Role;
import lesson.task.pojo.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

/**
 * fill in tables
 * @author Oleg_Chapurin
 */
public class DataInput {
    private static final Logger logger = LogManager.getLogger("jdbc_");
    private FactoryUsers factoryUsers = new FactoryUsersPostgres();

    /**
     * We form a test set users
     * @param quantityUsers quantity users in set
     * @param nameUser name user
     * @return ArrayList Object implements Users
     */
    private ArrayList<Users> getArrayUser(int quantityUsers,String nameUser){
        ArrayList<Users> arrayList = new ArrayList<>();
        for(int i = 0; i < quantityUsers; i++){
            Users user = factoryUsers.newInstance(nameUser.concat(String.valueOf(i)));
            if((i % 2) == 0) {
                user.setRole(Role.ADMINISTRATOR);
            }
            if((i % 3) == 0) {
                user.setRole(Role.CLIENTS);
            }
            if((i % 5) == 0) {
                user.setRole(Role.BILLING);
            }
            user.setBirthday("15.10.2000");
            user.setDescription("text".concat(String.valueOf(i)));
            user.setLogin_id(i);
            user.setCity("Moscow");
            user.setEmail("rdbvdbvd@mail.ru");
            arrayList.add(user);
        }
        return arrayList;
    }

    /**
     * fill in tables
     */
    public void fill() {

        ArrayList<Users> arrayList = getArrayUser(10, "AutoCommitTrue");
        TableDml<Users> table = new TableInputPostgres<>(
                "jdbc:postgresql://localhost:5432/mybd",
                "postgres",
                "postgres");
        logger.info("Method fill: The role table is full " + table.insert(Role.getHashMapEnum()));
        logger.info("Method fill: The user table is full (AutoCommit true)" + table.insert(arrayList));
        ArrayList<Users> usersAll = table.selectAllUser(factoryUsers);
        logger.info(usersAll);
        table.setAutoCommit(false);
        ArrayList<Users> arrayList2 = getArrayUser(10, "AutoCommitFalse");
        logger.info("Method fill: The user table is full (AutoCommit false)" + table.insert(arrayList2));
        table.setAutoCommit(true);
        ArrayList<Users> usersAll2 = table.selectAllUser(factoryUsers);
        logger.info(usersAll2);
        table.closeConnection();
    }

    public static void main(String[] args) {
        DataInput di = new DataInput();
        di.fill();
    }
}
