package com.antony.todosimple.models;

//import jakarta.persistence.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "task")

public class Task {

    //Constuctors
    public Task() {
    }

    public Task(User user, String descrition, Long id) {
        this.user = user;
        this.descrition = descrition;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = true)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private  User user;

    @Column(name = "descrition",length = 255, nullable = false)
    @NotEmpty
    @NotNull
    @Size(min = 1,max = 255)
    private String descrition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }
}
