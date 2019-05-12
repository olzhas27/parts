package ru.javarush.intership.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.javarush.intership.dao.PartsDao;
import ru.javarush.intership.model.Part;

import javax.servlet.annotation.WebServlet;

@WebServlet
@Controller
@RequestMapping(value = "/")
public class PartsController {
    private PartsDao partsDao;


    @GetMapping(value = "/")
    public ModelAndView allParts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("parts");
        modelAndView.addObject("parts", partsDao.getAllParts());
        return modelAndView;
    }

    @GetMapping(value = "/edit/")
    public ModelAndView editPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPart");
        return modelAndView;
    }

}
