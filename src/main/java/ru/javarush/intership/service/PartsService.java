package ru.javarush.intership.service;

import ru.javarush.intership.model.Part;

import java.util.List;

public interface PartsService {
    List<Part> allParts();
    int add(Part part);
    int delete(int id);
    int edit(Part part);
    Part getById(int id);
}
