package br.net.localizae.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstandeController {
    
    @RequestMapping(value = "/estande/gerenciar", method = RequestMethod.GET)
    public ModelAndView estandes() {
        ModelAndView mv = new ModelAndView("Estande/gerenciarEstandes");
        mv.addObject("aba", "estande");
        return mv;
    }
    
    @RequestMapping(value = "/estande/adicionar", method = RequestMethod.GET)
    public ModelAndView adicionarEstande() {
        ModelAndView mv = new ModelAndView("Estande/addEstande");
        mv.addObject("aba", "adicionarEstande");
        return mv;
    }
    
    @RequestMapping(value = "/estande/estatisticas", method = RequestMethod.GET)
    public ModelAndView estatisticas() {
        ModelAndView mv = new ModelAndView("Estande/estatisticasEstande");
        mv.addObject("aba", "estatisticas");
        return mv;
    }
    
    @RequestMapping(value = "/estande/gerenciar/avaliacoes", method = RequestMethod.GET)
    public ModelAndView pegarAvaliacoes() {
        ModelAndView mv = new ModelAndView("Estande/gerenciarAvaliacoes");
        mv.addObject("aba", "avaliacoes");
        return mv;
    }
    
}
