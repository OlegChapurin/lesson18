package part01.lesson05.task01;

import java.util.Objects;
import java.util.UUID;

/**
 * Constructs home animal
 *
 * @author Oleg_Chapurin
 */
class HomeAnimal implements Comparable<HomeAnimal> {

    private final int UID;
    private String nickName;
    private Integer mass;
    private Person owner;

    {
        UID = (int) ((UUID.randomUUID().getLeastSignificantBits() ^
                UUID.randomUUID().getMostSignificantBits()) >> 32);
    }

    /**
     * @param nickName name home animal
     * @param mass weight home animal
     * @param owner owner home animal
     */
    HomeAnimal(String nickName, int mass, Person owner) {
        this.nickName = nickName;
        this.mass = mass;
        this.owner = owner;
    }
    /** return uid home animal */
    int getUID() {
        return UID;
    }
    /** return name home animal */
    String getNickName() {
        return nickName;
    }
    /** return weight home animal */
    Integer getMass() {
        return mass;
    }
    /** return owner home animal */
    Person getOwner() {
        return owner;
    }
    /** sets nickname home animal */
    void setNickName(String nickName) {
        this.nickName = nickName;
    }
    /** sets weight home animal */
    void setMass(int mass) {
        this.mass = mass;
    }
    /** sets owner home animal */
    void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeAnimal that = (HomeAnimal) o;
        return UID == that.getUID() &&
                mass == that.getMass() &&
                Objects.equals(nickName, that.getNickName());
    }

    @Override
    public int hashCode() {
        return UID;
    }

    @Override
    public String toString() {
        return "Home animal{" +
                "owner= ( UID owner = " + UID +
                "  " + owner +
                " ) ID= " + UID +
                ", nickName='" + nickName + '\'' +
                ", mass=" + mass +
                '}';
    }

    @Override
    public int compareTo(HomeAnimal o) {
        int priorityOwner = owner.compareTo(o.getOwner());
        int priorityNickName = nickName.compareTo(o.getNickName());
        int priorityMass = mass.compareTo(o.getMass());
        return priorityOwner != 0 ? priorityOwner : (priorityNickName != 0 ? priorityNickName : priorityMass);
    }
}
