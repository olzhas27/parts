package ru.javarush.intership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.javarush.intership.dao.PartsDao;
import ru.javarush.intership.model.Part;
import ru.javarush.intership.service.PartsService;
import ru.javarush.intership.service.impl.PartsServiceImpl;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.List;

@WebServlet
@Controller
@RequestMapping(value = "/")
public class PartsController {
    private PartsService partsService = new PartsServiceImpl();


    @GetMapping(value = "/")
    public ModelAndView allParts() {
        List<Part> parts = partsService.allParts();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("parts");
        modelAndView.addObject("partsList", parts);
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
    public ModelAndView editFilm(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        partsService.edit(part);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addFilm(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        partsService.add(part);
        return modelAndView;
    }

}
