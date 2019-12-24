package part01.lesson15.task01.pojo;

import java.math.BigDecimal;

/**
 * @author Oleg_Chapurin
 */
public class User implements Users {
    private String name;
    private String birthday;
    private BigDecimal login_id;
    private String city;
    private String email;
    private String description;
    private Role role;

    public User(String name){
        this.name =name;
    }

    public User(){}

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getBirthday(){
        return birthday;
    }
    @Override
    public BigDecimal getLogin_id() {
        return login_id;
    }
    @Override
    public String getCity() {
        return city;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Role getRole() {
        return role;
    }
    @Override
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    @Override
    public void setLogin_id(BigDecimal login_id) {
        this.login_id = login_id;
    }
    @Override
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public void setRole(Role role) {
        this.role = role;
    }
    @Override
    public String toString(){
        return "name: ".concat(this.name).concat(" birthday: ").
                concat(this.birthday).concat(" login_id: ").
                concat(String.valueOf(this.login_id)).concat(" city: ").
                concat(this.city).concat(" email: ").concat(this.email).
                concat(" role: ").concat(this.role == null ? "null":this.role.name()).
                concat(" description: ").concat(this.description).concat("\n");
    }
}
