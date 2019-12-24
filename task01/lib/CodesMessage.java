package part01.lesson10.task01.lib;

/**
 * Codes message
 *
 * @author Oleg_Chapurin
 */
public enum CodesMessage {
    OK("10"),
    HELLO("20"),
    TO_ALL("30"),
    PERSONALLY("40");

    private String code;

    /**
     *
     * @param code name code message
     */
    CodesMessage(String code) {
        this.code = code;
    }

    /** Get name by code **/
    public static CodesMessage getEnumByCode(String code) {
        CodesMessage[] array = CodesMessage.values();
        for (int i = 0; i < array.length; i++){
            if(array[i].getCode().equals(code)){
                return array[i];
            }
        }
        return null;
    }

    /** Get code */
    public String getCode() {
        return code;
    }
}
