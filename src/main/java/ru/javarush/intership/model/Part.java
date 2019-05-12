package ru.javarush.intership.model;

public class Part {
    private static int count = 0;
    private int id = count++;
    private String name;
    private boolean isNeed;
    private int num;

    protected Part() {

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
