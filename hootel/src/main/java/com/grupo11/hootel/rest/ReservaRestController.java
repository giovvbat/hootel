package com.grupo11.hootel.rest;

import com.grupo11.hootel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReservaRestController {

    private ReservaService reservaService;

    @Autowired
    public ReservaRestController(ReservaService reservaService) {
        
    }
}
