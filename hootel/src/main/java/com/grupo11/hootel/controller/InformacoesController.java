package com.grupo11.hootel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformacoesController {
    @GetMapping("/informacoes")
    public String getInformacoes() {
        return "informacoes";
    }
}
