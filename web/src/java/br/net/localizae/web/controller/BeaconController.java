/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author marca
 */
@Controller
public class BeaconController {
    @RequestMapping(value = "/beacon/gerenciar", method = RequestMethod.GET)
    public ModelAndView estandes() {
        ModelAndView mv = new ModelAndView("Beacon/gerenciarBeacon");
        mv.addObject("aba", "beacon");
        return mv;
    }
    
    @RequestMapping(value = "/beacon/adicionar", method = RequestMethod.GET)
    public ModelAndView adicionarEstandes() {
        ModelAndView mv = new ModelAndView("Beacon/addBeacon");
        mv.addObject("aba", "beacon");
        return mv;
    }
    
    @RequestMapping(value = "/beacon/alterar/{id}", method = RequestMethod.GET)
    public ModelAndView alterarEstandes(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("Beacon/updBeacon");
        mv.addObject("aba", "beacon");
        mv.addObject("id", id);
        return mv;
    }
}