package ru.javarush.intership.controller;

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
    private PartsService partsService;

    @Autowired
    public void setPartsService(PartsService partsService) {
        this.partsService = partsService;
    }

    @GetMapping(value = "/")
    public ModelAndView allParts() {
        List<Part> parts = partsService.allParts();
        int minMachinesCount = parts.stream()
                .filter(Part::isNeed)
                .mapToInt(Part::getNum)
                .min()
                .orElse(0);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("parts");
        modelAndView.addObject("partsList", parts);
        modelAndView.addObject("machinesCount", minMachinesCount);
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
        partsService.add(part);
        return modelAndView;
    }

}
