package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Avaliacao;
import com.grupo11.hootel.service.AvaliacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model theModel) {

        Avaliacao theAvaliacao = new Avaliacao();

        theModel.addAttribute("avaliacao", theAvaliacao);

        return "avaliacao-form";
    }

    @PostMapping("/salvar")
    public String salvarAvaliacao(@ModelAttribute("avaliacao")Avaliacao theAvaliacao) {

        avaliacaoService.criarAvaliacao(theAvaliacao);

        return "redirect:saldacao";
    }
}
