package ru.javarush.intership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javarush.intership.model.Part;
import ru.javarush.intership.service.PartsService;

import javax.servlet.annotation.WebServlet;
import java.util.List;


@RestController
@RequestMapping(value = "/part")
public class PartRest {
    private PartsService partsService;

    @Autowired
    public PartRest(PartsService partsService) {
        this.partsService = partsService;
    }

    @GetMapping(value = "/all")
    public List<Part> getAllParts() {
        return partsService.allParts();
    }

    @GetMapping(value = "/minMachinesCount")
    public int getMinMachinesCount() {
        return partsService.getComputersCount();
    }
}
