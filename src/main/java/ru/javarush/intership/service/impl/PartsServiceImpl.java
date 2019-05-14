package ru.javarush.intership.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.intership.dao.PartsDao;
import ru.javarush.intership.dao.impl.mysql.PartsDaoImpl;
import ru.javarush.intership.model.Part;
import ru.javarush.intership.service.PartsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PartsServiceImpl implements PartsService {
    private static final Logger logger = LoggerFactory.getLogger(PartsServiceImpl.class);

    private PartsDao partsDao = new PartsDaoImpl();

    @Override
    public List<Part> allParts() {
        List<Part> allParts = new ArrayList<>();
        try {
            allParts.addAll(partsDao.getAllParts().values());
        } catch (SQLException e) {
            logger.error("Couldn't get parts", e);
        }
        return allParts;
    }

    @Override
    public void add(Part part) {
        try {
            partsDao.add(part);
        } catch (SQLException e) {
            logger.error("Couldn't add part", e);
        }
    }

    @Override
    public void delete(Part part) {
        try {
            partsDao.delete(part);
        } catch (SQLException e) {
            logger.error("Couldn't add part", e);
        }
    }

    @Override
    public void edit(Part part) {
        try {
            partsDao.edit(part);
        } catch (SQLException e) {
            logger.error("Couldn't add part", e);
        }
    }

    @Override
    public Part getById(int id) {
        return partsDao.getPartById(id);
    }
}
