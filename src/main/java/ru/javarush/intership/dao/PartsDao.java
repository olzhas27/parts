package ru.javarush.intership.dao;

import ru.javarush.intership.model.Part;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PartsDao {
    Map<Integer, Part> getAllParts() throws SQLException;
    int add(Part part) throws SQLException;
    int edit(Part part) throws SQLException;
    int delete(Part part) throws SQLException;
    Part getPartById(int id);

    int deletePartById(int id) throws SQLException;
}
