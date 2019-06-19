package ru.javarush.intership.service;

import ru.javarush.intership.model.Part;

import java.util.List;

public interface PartsService {
    List<Part> allParts();
    List<Part> allParts(int pageNum);
    int add(Part part);
    int delete(int id);
    int edit(Part part);
    Part getById(int id);
    int getPartsCount();
    int getComputersCount();
    List<Part> searchPartsByName(int pageNum, String partName);
    int getFilteredBySearchCount(String partName);
}
