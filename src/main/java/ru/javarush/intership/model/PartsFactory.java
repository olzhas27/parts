package ru.javarush.intership.model;

public class PartsFactory {

    public static Part newPart(int id, String partName, boolean isNeed, int num) {
        Part part = new Part();
        part.setId(id);
        part.setName(partName);
        part.setNum(num);
        part.setNeed(isNeed);
        return part;
    }
}
