package ru.javarush.intership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javarush.intership.model.Part;
import ru.javarush.intership.service.PartsService;

import javax.servlet.annotation.WebServlet;
import java.util.List;


@RestController
public class PartRest {
    private PartsService partsService;

    @Autowired
    public PartRest(PartsService partsService) {
        this.partsService = partsService;
    }

    @GetMapping(value = "/part/all")
    public List<Part> getAllParts() {
        return partsService.allParts();
    }
}
