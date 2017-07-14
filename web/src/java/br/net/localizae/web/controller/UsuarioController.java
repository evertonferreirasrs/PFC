package br.net.localizae.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
    
    @RequestMapping(value = "/usuario/adicionar/administrador", method = RequestMethod.GET)
    public ModelAndView adicionarAdministrador() {
        ModelAndView mv = new ModelAndView("Usuario/addAdministrador");
        mv.addObject("aba", "addAdministrador");
        return mv;
    }
    
    @RequestMapping(value = "/usuario/gerenciar", method = RequestMethod.GET)
    public ModelAndView gerenciar() {
        ModelAndView mv = new ModelAndView("Usuario/gerenciarUsuarios");
        mv.addObject("aba", "gerenciarUsuarios");
        return mv;
    }
    
    @RequestMapping(value = "/usuario/adicionar/expositor", method = RequestMethod.GET)
    public ModelAndView adicionarExpositor() {
        ModelAndView mv = new ModelAndView("Usuario/addExpositor");
        mv.addObject("aba", "addExpositor");
        return mv;
    }
    
    @RequestMapping(value = "/usuario/adicionar/jurado", method = RequestMethod.GET)
    public ModelAndView adicionarJurado() {
        ModelAndView mv = new ModelAndView("Usuario/addJurado");
        mv.addObject("aba", "addJurado");
        return mv;
    }
    
}
