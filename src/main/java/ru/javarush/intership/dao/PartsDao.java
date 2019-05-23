package ru.javarush.intership.dao;

import ru.javarush.intership.model.Part;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PartsDao {
    List<Part> getAllParts(int pageNum);
    int add(Part part);
    int edit(Part part);
    int delete(int id);
    Part getPartById(int id);
    int getPartsCount();
    int getComputersCount();
}
