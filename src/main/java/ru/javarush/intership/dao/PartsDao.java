package ru.javarush.intership.dao;

import ru.javarush.intership.model.Part;

import java.util.List;

public interface PartsDao {
    List<Part> getAllParts();
}
