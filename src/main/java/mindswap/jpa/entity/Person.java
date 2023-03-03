package mindswap.jpa.entity;

import javax.persistence.*;

@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;
    private String name;

    // constructor, getters, setters


    public Person() {
    }

    public Person(int personId, String name) {
        this.id = personId;
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public int getPersonId() {
        return id;
    }

    public void setPersonId(int personId) {
        this.id = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}