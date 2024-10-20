package com.nutrition_monitoring_app.Meal;

import java.sql.Date;

import com.nutrition_monitoring_app.User.User;

import jakarta.persistence.*;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @JoinColumn(name="name", nullable = false)
    private String name;

    @JoinColumn(name="date", nullable = false)
    private Date date;

    public Meal() {
    }

    public Meal(int id) {
        this.id = id;
    }

    public Meal(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
