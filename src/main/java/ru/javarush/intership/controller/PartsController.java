package ru.javarush.intership.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javarush.intership.model.Part;
import ru.javarush.intership.service.PartsService;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet
@Controller
public class PartsController {
    private static final Logger logger = LoggerFactory.getLogger(PartsController.class);
    private PartsService partsService;

    @Autowired
    public PartsController(PartsService partsService) {
        this.partsService = partsService;
    }

    @GetMapping(value = "/")
    public ModelAndView allParts(@RequestParam(name = "page", defaultValue = "1") int pageNum) {
        List<Part> parts = partsService.allParts(pageNum);
        int partsCount = partsService.getPartsCount();
        int pagesCount = (partsCount + 9) / 10;

        int minMachinesCount = partsService.getComputersCount();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("parts");
        modelAndView.addObject("partsList", parts);
        modelAndView.addObject("partsCount", partsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("machinesCount", minMachinesCount);
        modelAndView.addObject("pageNum", pageNum);
        return modelAndView;
    }

    @GetMapping(value = "search")
    public ModelAndView searchParts(@RequestParam(name = "name", defaultValue = "") String partName,
                                    @RequestParam(name = "page", defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView();

        if (partName == null || partName.isEmpty()) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        modelAndView.setViewName("parts");

        List<Part> parts = partsService.searchPartsByName(pageNum, partName);
        int partsCount = partsService.getFilteredBySearchCount(partName);
        int pagesCount = (partsCount + 9) / 10;
        int minMachinesCount = partsService.getComputersCount();

        modelAndView.addObject("partsList", parts);
        modelAndView.addObject("partsCount", partsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("machinesCount", minMachinesCount);
        modelAndView.addObject("partName", partName);
        modelAndView.addObject("pageNum", pageNum);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView deletePart(@PathVariable("id") int id) {
        String result = partsService.delete(id) != 0 ? "SUCCESS" : "FAIL";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }


    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        Part part = partsService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPart");
        modelAndView.addObject("part", part);
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView editPart(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        partsService.edit(part);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPart() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPart");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addPart(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        if (!part.isEmpty()) {
            partsService.add(part);
        }
        return modelAndView;
    }

}
