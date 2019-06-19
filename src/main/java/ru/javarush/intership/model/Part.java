package ru.javarush.intership.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "PARTS")
@JsonIgnoreProperties(value = { "empty" })
public class Part {
    @Id
    @Column(name = "PART_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "PART_NAME")
    private String name;

    @Column(name = "PART_IS_NEED")
    private boolean need;

    @Column(name = "PART_NUM")
    private BigInteger num;

    public Part() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isNeed() {
        return need;
    }

    public BigInteger getNum() {
        return num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNeed(boolean need) {
        this.need = need;
    }

    public void setNum(BigInteger num) {
        this.num = num;
    }

    public boolean isEmpty() {
        return name == null || name.isEmpty() || num == null;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", need=" + need +
                ", num=" + num +
                '}';
    }
}
