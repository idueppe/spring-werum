package io.crowdcode.speedbay.auction.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    public Person() {
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //    private LocalDate dayOfBirth;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
