/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.entity.Beacon;
import br.com.localizae.model.service.BeaconService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marca
 */
@RestController
public class BeaconController {
    @RequestMapping(value = "beacon", method = RequestMethod.GET)
    public ResponseEntity get(){
        List<Beacon> beaconList = null;
        BeaconService service = new BeaconService();
        try {
            beaconList = service.readByCriteria(null, null, null);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(beaconList, HttpStatus.OK);
    }
}
