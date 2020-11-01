package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.CollateralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/collateral")
public class CollateralObjectController {
    @Autowired
    private CollateralService service;

    @PostMapping("/save")
    public HttpEntity<Long> save(@RequestBody Collateral object) {
        Long id = service.saveCollateral(object);
        return id != null ? ResponseEntity.ok(id) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/info")
    public HttpEntity<Collateral> getInfo(@RequestBody Collateral object) {
        Collateral info = service.getInfo(object);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }

    //добавить стоимостную оценку объекту
    @PostMapping("/addValuation")
    public HttpEntity<Collateral> update(@RequestBody Collateral object) {
        Collateral updated = service.addValuation(object);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/test")
    public HttpEntity<String> test(@RequestBody Collateral object) {

        return  ResponseEntity.ok("jhg");
    }

}
