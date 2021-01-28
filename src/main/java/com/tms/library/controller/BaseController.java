package com.tms.library.controller;

import com.tms.library.exceptions.MyServiceException;
import com.tms.library.model.Book;
import com.tms.library.model.Genre;
import com.tms.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class BaseController {

    private final GenreService genreService;

    @Autowired
    public BaseController(GenreService genreService) {
        this.genreService = genreService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getMainPage(ModelMap modelMap) throws MyServiceException {
        System.out.println("We are inside controller");
        modelMap.addAttribute("message","Message from BaseController");
        List<Book> books = genreService.getAll()
                .stream()
                .map(it -> Book.builder().title(it.getName() + " title").genre(it.getName()).build())
                .collect(Collectors.toList());
        modelMap.addAttribute("books", books);
        return "main";
    }

    @RequestMapping(value = "/genre/new", method = RequestMethod.GET)
    public ModelAndView newGenre(){
        ModelAndView model = new ModelAndView();
        model.addObject("genreOb", new Genre());
        model.setViewName("addgenre");
        return model;
    }

    @RequestMapping(value = "/genres/add", method = RequestMethod.POST)
    public String addGenre(@ModelAttribute("genreOb") Genre genre, ModelMap model){
        Genre g = new Genre();
        g.setName(genre.getName());
        Genre created = genreService.create(g);
        model.addAttribute("genreOb", created);
        return "addgenre";
    }

}
