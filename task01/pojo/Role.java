package part01.lesson15.task01.pojo;

import java.util.HashMap;

/**
 * @author Oleg_Chapurin
 */
public enum Role {
    ADMINISTRATOR("ADMINISTRATOR"),
    CLIENTS("CLIENTS"),
    BILLING("BILLING");

    private String role;

    /**
     *
     * @param role name role
     */
    Role(String role) {
        this.role = role;
    }

    /** Get enum by name role **/
    public static Role getEnumByNameRole(String role) {
        Role[] array = Role.values();
        for (int i = 0; i < array.length; i++){
            if(array[i].getRole().equals(role)){
                return array[i];
            }
        }
        return null;
    }

    /** Get HashMap enum **/
    public static HashMap<Enum,String> getHashMapEnum() {
        HashMap<Enum,String> hashMap = new HashMap<>();
        Role[] array = Role.values();
        for (int i = 0; i < array.length; i++){
            hashMap.put(array[i],array[i].getRole());
        }
        return hashMap;
    }

    /** Get role
     * @return*/
    public String getRole() {
        return role;
    }
}
