package part01.lesson05.task01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Defines collection methods
 *
 * @author Oleg_Chapurin
 */
class CardFile<T extends HomeAnimal> {

    private Box<T> boxAnimal = new MyCollection<>();
    /** Adds to collection */
    private boolean addAnimal(T homeAnimal) {
        try {
            boxAnimal.add(homeAnimal);
        } catch (AddValueException e) {
            ReadWriteConsole.writeConsole(e.getMessage());
            return false;
        }
        return true;
    }
    /** Adds to collection */
    private boolean addAnimal(ArrayList<T> homeAnimal) {
        for (T animal : homeAnimal) {
            addAnimal(animal);
        }
        return true;
    }
    /** Returns value by name */
    private T findNickName(String nickname) {
        try {
            T valueBoxAnimal = boxAnimal.find(nickname);
            return valueBoxAnimal;
        } catch (FindValueException e) {
            ReadWriteConsole.writeConsole(e.getMessage());
        }
        return null;
    }
    /** Returns value by uid */
    private Person getOwner(int uid) {
        try {
            T valueBoxAnimal = boxAnimal.get(uid);
            return valueBoxAnimal.getOwner();
        } catch (FindValueException e) {
            ReadWriteConsole.writeConsole(e.getMessage());
        }
        return null;
    }
    /** Sets name */
    private T setNickName(int uid, String nickName) {
        try {
            T valueBoxAnimal = boxAnimal.get(uid);
            valueBoxAnimal.setNickName(nickName);
            return valueBoxAnimal;
        } catch (FindValueException e) {
            ReadWriteConsole.writeConsole(e.getMessage());
        }
        return null;
    }
    /** Sets weight */
    private T setMass(int uid, int mass) {
        try {
            T valueBoxAnimal = boxAnimal.get(uid);
            valueBoxAnimal.setMass(mass);
            return valueBoxAnimal;
        } catch (FindValueException e) {
            ReadWriteConsole.writeConsole(e.getMessage());
        }
        return null;
    }
    /** Sets owner */
    private T setOwner(int uid, Person owner) {
        try {
            T valueBoxAnimal = boxAnimal.get(uid);
            valueBoxAnimal.setOwner(owner);
            return valueBoxAnimal;
        } catch (FindValueException e) {
            ReadWriteConsole.writeConsole(e.getMessage());
        }
        return null;
    }
    /** Creat new home animal */
    void creatNewAnimal() {
        ReadWriteConsole.writeConsole(Message.ENTERNICKNAME);
        String nickname = ReadWriteConsole.readConsoleString();
        ReadWriteConsole.writeConsole(Message.NEWWEIGHT);
        Integer weight = ReadWriteConsole.readConsoleNumber();
        ReadWriteConsole.writeConsole(Message.NEWOWNER);
        Integer newOwner = ReadWriteConsole.readConsoleNumber();
        switch (newOwner) {
            /** New owner */
            case 1:
                ReadWriteConsole.writeConsole(Message.NAME);
                String nameOwner = ReadWriteConsole.readConsoleString();
                ReadWriteConsole.writeConsole(Message.AGE);
                Integer ageOwner = ReadWriteConsole.readConsoleNumber();
                ReadWriteConsole.writeConsole(Message.GENDER);
                Integer genderOwner = ReadWriteConsole.readConsoleNumber();
                Gender sexOwner = genderOwner == 1 ? Gender.MAN : Gender.WOMAN;
                Person owner1 = new Person(ageOwner, sexOwner, nameOwner);
                T newHomeAnimal1 = (T) new HomeAnimal(nickname, weight, owner1);
                if (!addAnimal(newHomeAnimal1)) {
                    ReadWriteConsole.writeConsole("Something is wrong, try again.");
                } else {
                    ReadWriteConsole.writeConsole(newHomeAnimal1.toString());
                }
                break;
            /** Owner or list*/
            case 2:
                printSortArray();
                ReadWriteConsole.writeConsole(Message.ENTERUIDOWNER);
                Integer uidOwner = ReadWriteConsole.readConsoleNumber();
                Person owner2 = getOwner(uidOwner);
                T newHomeAnimal2 = (T) new HomeAnimal(nickname, weight, owner2);
                if (!addAnimal(newHomeAnimal2)) {
                    ReadWriteConsole.writeConsole("Something is wrong, try again.");
                } else {
                    ReadWriteConsole.writeConsole(newHomeAnimal2.toString());
                }
                break;
            case 0:
                break;
        }
    }
    /** Select from the list home animal and edits*/
    void selectHomeAnimal() {
        /** Want to view data home animal (Yes) 1 or edit (Yes) 0 */
        ReadWriteConsole.writeConsole(Message.EDITHOMEANIMAL);
        Integer edit = ReadWriteConsole.readConsoleNumber();
        /** View data home animal */
        if (edit.equals(1)) {
            ReadWriteConsole.writeConsole(Message.ENTERNICKNAME);
            String nicknameEdit = ReadWriteConsole.readConsoleString();
            T homeAnimalEdit = (T) findNickName(nicknameEdit);
            if (homeAnimalEdit != null) {
                ReadWriteConsole.writeConsole(homeAnimalEdit.toString());
            }
        }
        /** edit data home animal */
        if (edit.equals(2)) {
            ReadWriteConsole.writeConsole(Message.ENTERIDANIMAL);
            Integer idAnimal = ReadWriteConsole.readConsoleNumber();
            ReadWriteConsole.writeConsole(Message.CHANGE);
            Integer change = ReadWriteConsole.readConsoleNumber();
            switch (change) {
                /** Change nickname */
                case 1:
                    ReadWriteConsole.writeConsole(Message.ENTERNICKNAME);
                    String nicknameChange = ReadWriteConsole.readConsoleString();
                    T homeAnimalChangeN = (T) setNickName(idAnimal, nicknameChange);
                    if (homeAnimalChangeN != null) {
                        ReadWriteConsole.writeConsole(homeAnimalChangeN.toString());
                    }
                    break;
                /** Change weight */
                case 2:
                    ReadWriteConsole.writeConsole(Message.ENTERWEIGHT);
                    Integer weightChange = ReadWriteConsole.readConsoleNumber();
                    T homeAnimalChangeW = (T) setMass(idAnimal, weightChange);
                    if (homeAnimalChangeW != null) {
                        ReadWriteConsole.writeConsole(homeAnimalChangeW.toString());
                    }
                    break;
                /** Change owner */
                case 3:
                    ReadWriteConsole.writeConsole(Message.NEWOWNER);
                    Integer newOwner = ReadWriteConsole.readConsoleNumber();
                    switch (newOwner) {
                        /** Select owner */
                        case 0:
                            printSortArray();
                            ReadWriteConsole.writeConsole(Message.ENTERUIDOWNER);
                            Integer uidOwner = ReadWriteConsole.readConsoleNumber();
                            Person owner1 = getOwner(uidOwner);
                            T homeAnimalChange1 = (T) setOwner(idAnimal, owner1);
                            if (homeAnimalChange1 != null) {
                                ReadWriteConsole.writeConsole(homeAnimalChange1.toString());
                            }
                            break;
                        /** New owner */
                        case 1:
                            ReadWriteConsole.writeConsole(Message.NAME);
                            String nameOwner = ReadWriteConsole.readConsoleString();
                            ReadWriteConsole.writeConsole(Message.AGE);
                            Integer ageOwner = ReadWriteConsole.readConsoleNumber();
                            ReadWriteConsole.writeConsole(Message.GENDER);
                            Integer genderOwner = ReadWriteConsole.readConsoleNumber();
                            Gender sexOwner = genderOwner == 1 ? Gender.MAN : Gender.WOMAN;
                            Person owner2 = new Person(ageOwner, sexOwner, nameOwner);
                            T homeAnimalChange2 = (T) setOwner(idAnimal, owner2);
                            if (homeAnimalChange2 != null) {
                                ReadWriteConsole.writeConsole(homeAnimalChange2.toString());
                            }
                            break;
                    }
                    break;
                /** Exit */
                case 0:
                    break;
            }
        }
        if (edit.equals(0)) {
            return;
        }
    }
    /** Print collection*/
    void printSortArray() {
        ArrayList<T> arrayListAnimal = new ArrayList<>();
        arrayListAnimal.addAll(Arrays.asList(boxAnimal.getCollection()));
        Collections.sort(arrayListAnimal);
        arrayListAnimal.forEach(a -> ReadWriteConsole.writeConsole(a.toString()));
    }

    public static void main(String[] args) {
        CardFile<HomeAnimal> cardFile = new CardFile<>();
        DataTest dataTest = new DataTest();
        cardFile.addAnimal(dataTest.fillData());
        ReadWriteConsole.newFlowWrite();
        ReadWriteConsole.newFlowRead();
        cardFile.printSortArray();
        boolean traffic = true;
        while (traffic) {
            ReadWriteConsole.writeConsole("--------------------------------------------");
            /**Add  new animal (Yes) 1 or select from the list (Yes) 2, write list 3, exit enter 0. */
            ReadWriteConsole.writeConsole(Message.ANIMAL);
            Integer answer = ReadWriteConsole.readConsoleNumber();
            /** Exit */
            if (answer.equals(0)) {
                traffic = false;
            }
            /** New Home animal*/
            if (answer.equals(1)) {
                cardFile.creatNewAnimal();
            }
            /** Select Home animal **/
            if (answer.equals(2)) {
                cardFile.selectHomeAnimal();
            }
            /** Exit */
            if (answer.equals(3)) {
                cardFile.printSortArray();
            }
        }
        ReadWriteConsole.closeAllFlow();
    }
}
