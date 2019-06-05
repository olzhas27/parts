package ru.javarush.intership.model;

import javax.persistence.*;

@Entity
@Table(name = "PARTS")
public class Part {
    @Id
    @Column(name = "PART_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "PART_NAME")
    private String name;

    @Column(name = "PART_IS_NEED")
    private boolean isNeed;

    @Column(name = "PART_NUM")
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
