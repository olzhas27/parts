package ru.javarush.intership.model;

import javax.persistence.*;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @Column(name = "part_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "part_name")
    private String name;

    @Column(name = "part_is_need")
    private boolean isNeed;

    @Column(name = "part_num")
    private int num;

    public Part() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isNeed() {
        return isNeed;
    }

    public int getNum() {
        return num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNeed(boolean need) {
        isNeed = need;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isNeed=" + isNeed +
                ", num=" + num +
                '}';
    }
}
