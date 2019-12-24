package part01.lesson11.task01;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Creates a file JSON.
 * @author Oleg_Chapurin
 */
class JsonFile implements SerializationToFile{

    private ConversionArrayToList conversionArrayToList = new ConversionArrayToList();
    private StringBuffer stringBuffer;

    /** Adds element to JSON */
    private void writeElement(Object element) {
        if (element instanceof Number) {
            stringBuffer.append(element);
        } else if (element instanceof Boolean) {
            stringBuffer.append(element);
        } else if (element instanceof String) {
            stringBuffer.append("\"");
            stringBuffer.append(element);
            stringBuffer.append("\"");
        } else {
            serializeJson(element,false);
        }
    }

    /** Adds array simple elements to JSON */
    private void arrayToJson(List<?> object, boolean marker) {
        int length = object.size();
        for (int j = 0; j < length; j++) {
            if (marker) {
                stringBuffer.append("\"");
            }
            stringBuffer.append(object.get(j));
            if (marker) {
                stringBuffer.append("\"");
            }
            if ((j + 1) != length) {
                stringBuffer.append(",\n");
            }
        }
    }

    /** Adds array object to JSON */
    private void arrayObjectToJson(List<?> object) {
        int length = object.size();
        for (int j = 0; j < length; j++) {
            serializeJson(object.get(j),false);
        }
    }
    /** Adds simple array elements to JSON */
    private void boxArray(Class<?> ClassObjectFiled, Object object) {
        stringBuffer.append("[\n");
        switch (ClassObjectFiled.getName()) {
            case "[I":
                List<Number> arrayInt = conversionArrayToList.getListOfArray(((int[]) object));
                arrayToJson(arrayInt, false);
                break;
            case "[Z":
                List<Boolean> arrayBool = conversionArrayToList.getListOfArray(((boolean[]) object));
                arrayToJson(arrayBool, false);
                break;
            case "[D":
                List<Number> arrayDouble = conversionArrayToList.getListOfArray(((double[]) object));
                arrayToJson(arrayDouble, false);
                break;
            case "[C":
                List<Character> arrayChar = conversionArrayToList.getListOfArray(((char[]) object));
                arrayToJson(arrayChar, true);
                break;
            case "[F":
                List<Number> arrayFloat = conversionArrayToList.getListOfArray(((float[]) object));
                arrayToJson(arrayFloat, false);
                break;
            case "[J":
                List<Number> arrayLong = conversionArrayToList.getListOfArray(((long[]) object));
                arrayToJson(arrayLong, false);
                break;
            case "[B":
                List<Number> arrayByte = conversionArrayToList.getListOfArray(((byte[]) object));
                arrayToJson(arrayByte, false);
                break;
            case "[S":
                List<Number> arrayShort = conversionArrayToList.getListOfArray(((short[]) object));
                arrayToJson(arrayShort, false);
                break;
            case "[Ljava.lang.String;":
                List<String> arrayString = conversionArrayToList.getListOfArray(((String[]) object));
                arrayToJson(arrayString, true);
                break;
            default:
                List<Object> arrayObject = conversionArrayToList.getListOfArray(((Object[]) object));
               arrayObjectToJson(arrayObject);
                break;
        }
        stringBuffer.append("\n]");
        stringBuffer.append(",\n");
    }

    /** Processing fields objects */
    private void fieldProcessing(Field[] fields, Class<?> ClassObjectFiled, Object object) {
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            if (!Modifier.isFinal(fields[i].getModifiers())) {
                String name = fields[i].getName();
                stringBuffer.append("\"");
                stringBuffer.append(name);
                stringBuffer.append("\"");
                stringBuffer.append(":");
                try {
                    Field testAAClassFiled = ClassObjectFiled.getDeclaredField(name);
                    testAAClassFiled.setAccessible(true);
                    setValue(testAAClassFiled, object);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                if ((i + 1) != length) {
                    stringBuffer.append(",\n");
                }
            }
        }
    }

    /** Adds fields object to JSON */
    private void boxFields(Class<?> ClassObjectFiled, Object object) {
        Field[] fields = ClassObjectFiled.getDeclaredFields();
        if (fields.length > 0) {
            fieldProcessing(fields, ClassObjectFiled, object);
        }
        Field[] fieldsSuper = ClassObjectFiled.getSuperclass().getDeclaredFields();
        if (fieldsSuper.length > 0) {
            stringBuffer.append("super");
            stringBuffer.append(":");
            stringBuffer.append("{\n");
            fieldProcessing(fieldsSuper, ClassObjectFiled.getSuperclass(), object);
            stringBuffer.append("\n}");
        }
    }

    /** Adds value fields object to JSON */
    private void setValue(Field ObjectFiled, Object object) {
        try {
            switch (identifyType(ObjectFiled)) {
                case "number":
                    stringBuffer.append(ObjectFiled.get(object));
                    break;
                case "string":
                    stringBuffer.append("\"");
                    stringBuffer.append(ObjectFiled.get(object));
                    stringBuffer.append("\"");
                    break;
                case "class":
                    Object aa2 = (Object) ObjectFiled.get(object);
                    serializeJson(aa2,false);
                    break;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /** Determine the type */
    private String identifyType(Field ObjectFiled) {
        String type = ObjectFiled.getType().getSimpleName();
        if (("int".equals(type)) || ("boolean".equals(type))
                || ("byte".equals(type)) || ("char".equals(type))
                || ("double".equals(type)) || ("float".equals(type))
                || ("long".equals(type)) || ("short".equals(type))
                || ("Integer".equals(type)) || ("Boolean".equals(type))
                || ("Byte".equals(type)) || ("Char".equals(type))
                || ("Double".equals(type)) || ("Float".equals(type))
                || ("Long".equals(type)) || ("Short".equals(type))) {
            return "number";
        } else if (("String".equals(type)) || (ObjectFiled.getType().isEnum())) {
            return "string";
        } else {
            return "class";
        }
    }

    /** Adds collections to JSON */
    private void collectionToJson(Collection object) {
        Object[] array = object.toArray();
        int size = array.length;
        stringBuffer.append("[\n");
        for (int index = 0; index < size; index++) {
            Object element = array[index];
            writeElement(element);
            if ((index + 1) != size) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append("\n]");
        stringBuffer.append(",\n");
    }

    /** Adds map to JSON */
    private void hashMapToJson(HashMap<Object, Object> object) {
        stringBuffer.append("{\n");
        int size = object.size();
        int index = 0;
        for (Map.Entry entry : object.entrySet()) {
            writeElement(entry.getKey());
            stringBuffer.append(":");
            writeElement(entry.getValue());
            if ((index + 1) != size) {
                stringBuffer.append(",\n");
            }
            index++;
        }
        stringBuffer.append("\n\r}");
    }

    /** Processing objects */
    private void objectToJson(Object object,Boolean marker) {
        Class<?> ClassObjectFiled = object.getClass();
        if (ClassObjectFiled.isArray()) {
            boxArray(ClassObjectFiled, object);
        } else {
            stringBuffer.append("{\n");
            if (marker) {
                stringBuffer.append("Class");
                stringBuffer.append(":");
                stringBuffer.append(ClassObjectFiled.getName());
                stringBuffer.append("{\n");
            }
            boxFields(ClassObjectFiled, object);
            if (marker) {
                stringBuffer.append("\n}");
            }
            stringBuffer.append("\n}");
        }
    }

    private void serializeJson(Object object,Boolean marker) {
        if (object instanceof Collection) {
            collectionToJson((Collection) object);
        } else if (object instanceof HashMap) {
            hashMapToJson((HashMap) object);
        } else {
            objectToJson(object,marker);
        }
    }

    @Override
    public String serialize(Object object) {
        stringBuffer = new StringBuffer();
        serializeJson(object,true);
        return stringBuffer.substring(0);
    }
}
