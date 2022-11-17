package com.franlb.noticiero.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.franlb.noticiero.model.Noticia;
import com.franlb.noticiero.model.Usuario;

@Controller
@RequestMapping("noticias")
public class NoticiaController {

    @RequestMapping(value = "/signin")
    public ModelAndView signin(Usuario usuario,HttpSession session) {

        session.setAttribute("usuario", usuario);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", getNoticias());
        modelAndView.setViewName("noticias/list");
        return modelAndView;
    }

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticias", getNoticias());
        modelAndView.setViewName("noticias/list");
        return modelAndView;
    }

    @RequestMapping(value = {"/nuevaNoticia"} )
    public ModelAndView nuevo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("noticias/nuevaNoticia");
        return modelAndView;
    }

    @PostMapping(value = "/createNoticia")
    public ModelAndView saveNoticia(Noticia noticia) {
        ModelAndView modelAndView = new ModelAndView();

        List<Noticia> noticias = getNoticias();
        noticias.add(noticia);

        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/list");
        return modelAndView;
    }

    @RequestMapping(value = {"/modificarNoticia"} )
    public ModelAndView edit(
        @RequestParam(name="codigo",required = true) int codigo
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("noticia", getNoticia(codigo));
        modelAndView.setViewName("noticias/modificarNoticia");
        return modelAndView;
    }

    @PostMapping(value = "/editNoticia")
    public ModelAndView editNoticia(Noticia noticia) {

        ModelAndView modelAndView = new ModelAndView();
        List<Noticia> noticias = getNoticias();

        int indexOf = noticias.indexOf(noticia);
        noticias.set(indexOf,noticia);

        modelAndView.addObject("noticias", noticias);
        modelAndView.setViewName("noticias/list");
        return modelAndView;
    }

    @RequestMapping(value = "/borrarNoticia")
    public ModelAndView delete(
        @RequestParam(name="codigo",required = true) int codigo
    ) {
        List<Noticia> noticias = getNoticias();
        noticias.remove(new Noticia(codigo));

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("noticias",noticias);
        modelAndView.setViewName("noticias/list");
        return modelAndView;
    }

    private Noticia getNoticia(int codigo) {
        List<Noticia> noticias = getNoticias();
        int indexOf = noticias.indexOf(new Noticia(codigo));
        
        return noticias.get(indexOf);
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
