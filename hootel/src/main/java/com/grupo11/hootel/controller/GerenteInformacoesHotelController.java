package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Informacoes;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.InformacoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
public class GerenteInformacoesHotelController {
    private final InformacoesService informacoesService;

    @Autowired
    public GerenteInformacoesHotelController(InformacoesService informacoesService) {
        this.informacoesService = informacoesService;
    }

    @GetMapping("/mostrarInfo")
    public String mostrarInfo(Model model) {
        try {
            Informacoes informacoes = informacoesService.lerInformacoes();
            model.addAttribute("info", informacoes);
        } catch (HootelException e) {
            model.addAttribute("info", new Informacoes());
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "hotel/info_gerente";
    }

    @PostMapping("/atualizaInfo")
    public String atualizarInfo(@Valid @ModelAttribute("info") Informacoes informacoes,
                                    BindingResult bindingResult,
                                    Model model) {

        if (bindingResult.hasErrors()) {
            return "hotel/info_gerente";
        }

        try {
            informacoesService.atualizarInformacoes(informacoes);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "hotel/info_gerente";
    }
}
