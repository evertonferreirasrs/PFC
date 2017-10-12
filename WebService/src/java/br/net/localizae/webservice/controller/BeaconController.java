/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.localizae.webservice.controller;

import br.com.localizae.model.entity.Beacon;
import br.com.localizae.model.service.BeaconService;
import br.net.localizae.webservice.converter.JsonConverter;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marca
 */
@CrossOrigin(origins = "http://localhost:8080")
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
    
    @RequestMapping(value = "beacon/{id}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable Long id){
        Beacon beacon = null;
        BeaconService service = new BeaconService();
        try {
            beacon = service.readById(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(beacon, HttpStatus.OK);
    }
    
    @RequestMapping(value = "beacon", method = RequestMethod.POST)
    public ResponseEntity post(@RequestBody String json){
        Beacon beacon = (Beacon)JsonConverter.convertFromJson(json, Beacon.class);
        BeaconService service = new BeaconService();
        try {
            service.create(beacon);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(beacon, HttpStatus.OK);
    }
    
    @RequestMapping(value = "beacon", method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody String json){
        Beacon beacon = (Beacon)JsonConverter.convertFromJson(json, Beacon.class);
        BeaconService service = new BeaconService();
        try {
            service.update(beacon);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(beacon, HttpStatus.OK);
    }
    
    @RequestMapping(value = "beacon/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id){
        BeaconService service = new BeaconService();
        try {
            service.delete(id);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
