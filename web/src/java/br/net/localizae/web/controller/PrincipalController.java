package br.net.localizae.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {
    
    @RequestMapping(value = "/entrar", method = RequestMethod.GET)
    public ModelAndView logar() {
        ModelAndView mv = new ModelAndView("Principal/Login/login");

        return mv;
    }
    
    @RequestMapping(value = "/gerenciador", method = RequestMethod.GET)
    public ModelAndView gerenciar() {
        ModelAndView mv = new ModelAndView("Principal/Gerenciador/painel");

        return mv;
    }
    
}
