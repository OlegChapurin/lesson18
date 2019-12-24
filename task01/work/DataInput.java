package part01.lesson15.task01.work;

import part01.lesson15.task01.dao.Table;
import part01.lesson15.task01.dao.TablePostgres;
import part01.lesson15.task01.pojo.Role;
import part01.lesson15.task01.pojo.Users;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * fill in tables
 * @author Oleg_Chapurin
 */
public class DataInput {
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
            user.setLogin_id(BigDecimal.valueOf(i));
            user.setCity("Moscow");
            user.setEmail("rdbvdbvd@mail.ru");
            arrayList.add(user);
        }
        return arrayList;
    }

    /**
     * fill in tables
     */
    public void fill(){
        ArrayList<Users> arrayList = getArrayUser(10,"AutoCommitTrue");
        Table<Users> table = new TablePostgres<>(
                "jdbc:postgresql://localhost:5432/mybd",
                "postgres",
                "postgres");
        System.out.println("The user table is full (AutoCommit true)" + table.insert(arrayList));
        ArrayList<Users> usersAll = table.selectAllUser(factoryUsers);
        System.out.println(usersAll);
        table.setAutoCommit(false);
        ArrayList<Users> arrayList2 = getArrayUser(10,"AutoCommitFalse");
        System.out.println("The user table is full (AutoCommit false)" + table.insert(arrayList2));
        table.setAutoCommit(true);
        ArrayList<Users> usersAll2 = table.selectAllUser(factoryUsers);
        System.out.println(usersAll2);
        table.closeConnection();
    }

    public static void main(String[] args) {
        DataInput di = new DataInput();
        di.fill();
    }
}
