package com.franlb.noticiero.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.franlb.noticiero.model.Noticia;

@Controller
@RequestMapping("noticias")
public class NoticiaController {

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", getNoticias());
        modelAndView.setViewName("noticias/list");
        return modelAndView;
    }

    private List<Noticia> getNoticias() {
        ArrayList<Noticia> noticias = new ArrayList<Noticia>();
        noticias.add(new Noticia(1,"Titular 1","Descripción 1"));
        noticias.add(new Noticia(2,"Titular 2","Descripción 2"));
        noticias.add(new Noticia(3,"Titular 3","Descripción 3"));
        noticias.add(new Noticia(4,"Titular 4","Descripción 4"));
        return noticias;
    }


}
