/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author marca
 */
@Controller
public class EstatisticaController {
    @RequestMapping(value = "/estatisticas", method = RequestMethod.GET)
    public ModelAndView estatisticas() {
        ModelAndView mv = new ModelAndView("Estatistica/index");
        mv.addObject("aba", "estatisticas");
        return mv;
    }
}
