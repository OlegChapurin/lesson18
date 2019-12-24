package part01.lesson11.task01;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

import static java.lang.Boolean.getBoolean;

/**
 * Deserialize object of JSON.
 *
 * @author Oleg_Chapurin
 */
class JsonObject implements SerializationToObject {

    private static String OPEN_lEFT_CURLY_BRACE = "{";
    private static String CLOSE_RIGHT_CURLY_BRACE = "}";
    private static String OPEN_lEFT_SQUARE_BRACKET = "[";
    private static String CLOSE_RIGHT_SQUARE_BRACKET = "]";
    private static String COLON = ":";
    private static String QUOTATION_MARK = "\"";
    private static String COMMA = ",";
    private static String CLAZZ = "Class";
    private static String SUPERR = "super";
    private Class objectClass = null;
    private Object object = null;
    private Class objectClass2 = null;
    private Object object2 = null;
    /**
     * Marker
     */
    int fillArray = 0;
    /**
     * Temporarily save the parameter name
     */
    private String tempParameter = "";

    /**
     * Get a new class by name
     */
    private Class newClass(String nameClass) {
        Class newClass = null;
        try {
            newClass = Class.forName(nameClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newClass;
    }

    /**
     * Instance a new object
     */
    private Object newObject(Class newClass) {
        Object newObject = null;
        try {
            newObject = newClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return newObject;
    }

    /**
     * Creates class and object by name
     */
    protected void creatObject(String nameClass) {
        this.objectClass2 = this.objectClass = newClass(nameClass);
        this.object2 = this.object = newObject(this.objectClass);
    }

    /**
     * Set value field depending type
     */
    private void setValueDependingType(Class type, Field field, Object object, String value) {
        if (type == Boolean.TYPE) {
            try {
                field.setBoolean(object, Boolean.parseBoolean(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (type == Integer.TYPE) {
            try {
                field.setInt(object, Integer.parseInt(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (type == Character.TYPE) {
            try {
                field.setChar(object, (char) Integer.parseInt(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (type == Double.TYPE) {
            try {
                field.setDouble(object, Double.parseDouble(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (type == Long.TYPE) {
            try {
                field.setLong(object, Long.parseLong(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (type == Byte.TYPE) {
            try {
                field.setByte(object, Byte.parseByte(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (type == Short.TYPE) {
            try {
                field.setShort(object, Short.parseShort(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if ("java.lang.Integer".equals(type.getName())) {
            try {
                field.set(object, Integer.parseInt(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if ("java.lang.Character".equals(type.getName())) {
            try {
                field.set(object, (char) Integer.parseInt(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if ("java.lang.Double".equals(type.getName())) {
            try {
                field.set(object, Double.parseDouble(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if ("java.lang.Long".equals(type.getName())) {
            try {
                field.set(object, Long.parseLong(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if ("java.lang.Byte".equals(type.getName())) {
            try {
                field.set(object, Byte.parseByte(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if ("java.lang.Short".equals(type.getName())) {
            try {
                field.set(object, Short.parseShort(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (type.isEnum()) {

        } else {
            try {
                field.set(object, (Object) value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the initial state of objects
     */
    protected void returnToPreviousValue() {
        this.objectClass = this.objectClass2;
        this.object = this.object2;
    }

    /**
     * Fill simple array
     */
    private void processArrays(Field field, Object object, ArrayList<String> value) {
        int size = value.size();
        switch (field.getType().getName()) {
            case "[I":
                int[] iii = (int[]) Array.newInstance(int.class, size);
                for (int i = 0; i < size; i++) {
                    iii[i] = Integer.valueOf(value.get(i));
                }
                try {
                    field.set(object, iii);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "[Z":
                boolean[] bbb = (boolean[]) Array.newInstance(boolean.class, size);
                for (int i = 0; i < size; i++) {
                    bbb[i] = getBoolean(value.get(i));
                }
                try {
                    field.set(object, bbb);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "[D":
                double[] ddd = (double[]) Array.newInstance(double.class, size);
                for (int i = 0; i < size; i++) {
                    ddd[i] = Double.parseDouble(value.get(i));
                }
                try {
                    field.set(object, ddd);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "[C":
                char[] ccc = (char[]) Array.newInstance(char.class, size);
                for (int i = 0; i < size; i++) {
                    ccc[i] = Character.highSurrogate(Integer.valueOf(value.get(i)));
                }
                try {
                    field.set(object, ccc);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "[F":
                float[] fff = (float[]) Array.newInstance(float.class, size);
                for (int i = 0; i < size; i++) {
                    fff[i] = Float.parseFloat(value.get(i));
                }
                try {
                    field.set(object, fff);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "[J":
                long[] lll = (long[]) Array.newInstance(long.class, size);
                for (int i = 0; i < size; i++) {
                    lll[i] = Long.parseLong(value.get(i));
                }
                try {
                    field.set(object, lll);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "[B":
                byte[] byy = (byte[]) Array.newInstance(byte.class, size);
                for (int i = 0; i < size; i++) {
                    byy[i] = Byte.parseByte(value.get(i));
                }
                try {
                    field.set(object, byy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "[S":
                short[] shor = (short[]) Array.newInstance(short.class, size);
                for (int i = 0; i < size; i++) {
                    shor[i] = Short.parseShort(value.get(i));
                }
                try {
                    field.set(object, shor);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case "[Ljava.lang.String;":
                String[] str = (String[]) Array.newInstance(String.class, size);
                for (int i = 0; i < size; i++) {
                    str[i] = value.get(i);
                }
                try {
                    field.set(object, str);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * Fill array and collection
     */
    protected void fillArrayCollection(String currentObject, ArrayList<String> value) {
        try {
            Field field = objectClass.getDeclaredField(currentObject);
            field.setAccessible(true);
            processArrays(field, object, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /*** Get a super class and set to variables */
    protected void setSuperClass() {
        this.objectClass = objectClass.getSuperclass();
        this.object = newObject(objectClass);
    }

    /**
     * Sets the current value to new objects
     */
    protected void setFiledToCurrentObject(String currentObject) {
        try {
            Field field = objectClass2.getDeclaredField(currentObject);
            String nameNewClass = objectClass.getDeclaredField(currentObject).getType().getTypeName();
            this.objectClass = newClass(nameNewClass);
            this.object = newObject(objectClass);
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the value of fields
     */
    protected void setValueFiled(String parameter, String value) {
        try {
            Field field = objectClass.getDeclaredField(parameter);
            field.setAccessible(true);
            Class type = field.getType();
            setValueDependingType(type, field, object, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete commas around the edges
     */
    private String removeQuotesFromString(String str) {
        if (QUOTATION_MARK.equals(str.substring(0, 1))) {
            str = str.substring(1, str.length() - 1);
            if (QUOTATION_MARK.equals(str.substring(str.length() - 1, str.length()))) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    /**
     * Delete commas around the edges at parameter
     */
    private String getParameter(String temp, int indexColon) {
        if (indexColon > 0) {
            return removeQuotesFromString(temp.substring(0, indexColon));
        }
        return temp;
    }

    /**
     * Delete commas around the edges at value
     */
    private String getValue(String temp, int indexColon, int length) {
        if (indexColon > 0) {
            String value = temp.substring(indexColon + 1, length);
            return removeQuotesFromString(value);
        }
        return temp;
    }

    /**
     * Creat object
     */
    private Boolean creatNewObject(String parameter, String value) {
        Boolean marker = false;
        /** Check if the parameter value is {  */
        if ((OPEN_lEFT_CURLY_BRACE.equals(value)) & (value.length() == 1)) {
            if ((SUPERR.equals(parameter)) & (parameter.length() == 5)) {
                setSuperClass();
            } else {
                /** Parameter value { create a new object */
                setFiledToCurrentObject(parameter);
            }
            marker = true;
            /** Check if the parameter value is [ */
        } else if ((OPEN_lEFT_SQUARE_BRACKET.equals(value)) & (value.length() == 1)) {
            /** Remember the name parameter */
            this.tempParameter = parameter;
            this.fillArray = 1;
            marker = true;
            /** Check if the parameter value is ] */
        } else if ((CLOSE_RIGHT_SQUARE_BRACKET.equals(value)) & (value.length() == 1)) {
            this.fillArray = 2;
            marker = false;
        }
        return marker;
    }

    /**
     * Process file Json
     */
    private void processFileJson(TreeMap<Integer, String> treeMap) {
        int length = 0;
        Boolean fillArray = false;
        ArrayList<String> tempArrayList = new ArrayList<>();
        Set<Map.Entry<Integer, String>> set = treeMap.entrySet();
        for (Map.Entry<Integer, String> entry : set) {
            String temp = entry.getValue();
            length = temp.length();
            /** Check if at the end "," */
            if (COMMA.equals(temp.substring(length - 1, length))) {
                length--;
                temp = temp.substring(0, length);
            }
            /** Check whether it is a bracket or not { }  */
            if ((!OPEN_lEFT_CURLY_BRACE.equals(temp)) & (!CLOSE_RIGHT_CURLY_BRACE.equals(temp)) & (length > 0)) {
                int indexColon = temp.indexOf(COLON);
                indexColon = indexColon < 0 ? 0 : indexColon;
                /** Check this line with the type of class or not  */
                if (CLAZZ.equals(temp.substring(0, indexColon))) {
                    /**  ?? */
                    if (OPEN_lEFT_CURLY_BRACE.equals(temp.substring(length - 1, length)) ||
                            OPEN_lEFT_SQUARE_BRACKET.equals(temp.substring(length - 1, length))) {
                        creatObject(temp.substring(indexColon + 1, length - 1));
                        continue;
                    }
                }
                /** We remove at the end of the line "," */
                if (COMMA.equals(temp.substring(length - 1, length))) {
                    length--;
                }
                /** Get the name of the parameter */
                String parameter = getParameter(temp, indexColon);
                /** We get the parameter value  */
                String value = getValue(temp, indexColon, length);
                /** Check if there is a need to create an object */
                if (!creatNewObject(parameter, value)) {
                    /** We fill in the parameter value*/
                    if (this.fillArray == 0) {
                        setValueFiled(parameter, value);
                    }
                    if (this.fillArray == 1) {
                        tempArrayList.add(value);
                        int a = 1;
                    }
                    if (this.fillArray == 2) {
                        this.fillArray = 0;
                        fillArrayCollection(this.tempParameter, tempArrayList);
                        this.tempParameter = "";
                        int a = 1;
                    }
                }
            }
            if ((CLOSE_RIGHT_CURLY_BRACE.equals(temp)) & (length == 1)) {
                returnToPreviousValue();
            }
        }
    }

    @Override
    public Object deSerialize(TreeMap<Integer, String> treeMap) {
        processFileJson(treeMap);
        return this.object;
    }

}
