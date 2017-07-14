package br.net.localizae.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RelatorioController {
    
    @RequestMapping(value = "/relatorio/visitantePorEstande", method = RequestMethod.GET)
    public ModelAndView gerarRelatorioVisitantes() {
        ModelAndView mv = new ModelAndView("Relatorio/porVisitantes");
        mv.addObject("aba", "porVisitantes");
        return mv;
    }
    
    @RequestMapping(value = "/relatorio/avaliacoesEstandes", method = RequestMethod.GET)
    public ModelAndView gerarRelatorioAvaliacoes() {
        ModelAndView mv = new ModelAndView("Relatorio/avaliacoesEstandes");
        mv.addObject("aba", "avaliacoesEstandes");
        return mv;
    }
    
}
