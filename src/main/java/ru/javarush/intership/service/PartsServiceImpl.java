package ru.javarush.intership.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javarush.intership.dao.PartsDao;
import ru.javarush.intership.model.Part;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PartsServiceImpl implements PartsService {
    private static final Logger logger = LoggerFactory.getLogger(PartsServiceImpl.class);

    private PartsDao partsDao;

    @Autowired
    public PartsServiceImpl(PartsDao partsDao) {
        this.partsDao = partsDao;
    }


    @Override
    @Transactional
    public List<Part> allParts(int pageNum) {
        return partsDao.getAllParts(pageNum);
    }

    @Override
    @Transactional
    public List<Part> searchPartsByName(int pageNum, String partName) {
        return partsDao.searchPartsByName(pageNum, partName);
    }

    @Override
    @Transactional
    public int getFilteredBySearchCount(String partName) {
        return partsDao.getFilteredBySearchCount(partName);
    }

    @Override
    @Transactional
    public int getPartsCount() {
        return partsDao.getPartsCount();
    }

    @Override
    @Transactional
    public int getComputersCount() {
        return partsDao.getComputersCount();
    }

    @Override
    @Transactional
    public int add(Part part) {
        partsDao.add(part);
        return 1;
    }

    @Override
    @Transactional
    public int delete(int id) {
        return partsDao.delete(id);
    }

    @Override
    @Transactional
    public int edit(Part part) {
        partsDao.edit(part);
        return 1;
    }

    @Override
    @Transactional
    public Part getById(int id) {
        return partsDao.getPartById(id);
    }
}
