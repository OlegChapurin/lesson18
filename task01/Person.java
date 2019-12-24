package part01.lesson05.task01;

/**
 * @author Oleg_Chapurin
 */
class Person implements Comparable<Person> {

    private Integer age;
    private Gender sex;
    private String name;

    /**
     *
     * @param age type int
     * @param sex gender affiliation
     * @param name name
     */
    Person(int age, Gender sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }
    /** Return age */
    Integer getAge() {
        return age;
    }
    /** Return type gender */
    Gender getSex() {
        return sex;
    }
    /** Return name */
    String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "Name " + name + ": age " +  String.valueOf(age) + "; sex " + sex;
    }

    @Override
    public boolean equals(Object object){
        if (!(object instanceof Person)) return false;
        Person person = (Person)object;
        if ((name.compareTo(person.getName()) == 0) & (age.compareTo(person.getAge()) == 0)){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Person o){
        int prioritySex = sex.compareTo(o.getSex());
        int priorityAge = age.compareTo(o.getAge());
        int priorityName = name.compareTo(o.getName());
        return prioritySex != 0 ? prioritySex : (priorityAge != 0 ? priorityAge : priorityName);
    }
}
