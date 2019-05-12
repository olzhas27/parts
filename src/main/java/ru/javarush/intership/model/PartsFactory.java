package ru.javarush.intership.model;

public class PartsFactory {

    public static Part newPart(String partName, boolean isNeed, int num) {
        Part part = new Part();
        part.setName(partName);
        part.setNum(num);
        part.setNeed(isNeed);
        return part;
    }
}
