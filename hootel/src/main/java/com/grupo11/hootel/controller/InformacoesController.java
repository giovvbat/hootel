package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Informacoes;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.InformacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformacoesController {
    private InformacoesService informacoesService;

    @Autowired
    public InformacoesController(InformacoesService informacoesService) {
        this.informacoesService = informacoesService;
    }

    @GetMapping("/informacoes")
    public String getInformacoes(Model model) {
        try {
            Informacoes informacoes = informacoesService.lerInformacoes();
            model.addAttribute("informacoes", informacoes);
        }catch (HootelException e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("informacoes", new Informacoes());
            return "info";
        }
        return "info";
    }
}
