package ru.javarush.intership.service;

import ru.javarush.intership.model.Part;

import java.util.List;

public interface PartsService {
    List<Part> allParts();
    void add(Part part);
    void delete(Part part);
    void edit(Part part);
    Part getById(int id);
}
