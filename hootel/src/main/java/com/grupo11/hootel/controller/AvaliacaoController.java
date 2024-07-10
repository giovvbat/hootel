package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Avaliacao;
import com.grupo11.hootel.service.AvaliacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/hotel")
public class AvaliacaoController {

    private AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping("/avaliacao/formulario")
    public String mostrarFormulario(Model theModel) {

        Avaliacao theAvaliacao = new Avaliacao();

        theModel.addAttribute("avaliacao", theAvaliacao);

        return "hotel/avaliacao";
    }

    @PostMapping("/avaliacao/salvar")
    public String salvarAvaliacao(@ModelAttribute("avaliacao")Avaliacao theAvaliacao) {

        theAvaliacao.setData(new Date());
        avaliacaoService.criarAvaliacao(theAvaliacao);

        return "hotel/index";
    }
}
