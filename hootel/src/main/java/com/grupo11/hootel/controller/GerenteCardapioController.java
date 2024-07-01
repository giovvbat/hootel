package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.Alimentacao;
import com.grupo11.hootel.exceptions.HootelException;
import com.grupo11.hootel.service.AlimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GerenteCardapioController {

    private final AlimentacaoService alimentacaoService;

    @Autowired
    public GerenteCardapioController(AlimentacaoService alimentacaoService) {
        this.alimentacaoService = alimentacaoService;
    }

    @GetMapping("/mostrarCardapio")
    public String mostrarCardapio(Model model) {
        try {
            List<Alimentacao> alimentacao = alimentacaoService.listarAlimentacoes();
            model.addAttribute("cardapio", alimentacao);
        } catch (HootelException e) {
            model.addAttribute("cardapio", new Alimentacao());
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "cardapio_gerente";
    }

    @PostMapping("/adicionarCardapio")
    public String addAlimentacaoEmCardapio(@Valid @ModelAttribute("cardapio") Alimentacao alimentacao,
                                 BindingResult bindingResult,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            return "cardapio_gerente";
        }

        try {
            alimentacaoService.addAlimentacao(alimentacao);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "cardapio_gerente";
    }

    @PostMapping("/atualizaCardapio")
    public String atualizarAlimentacaoEmCardapio(@Valid @ModelAttribute("cardapio") Alimentacao alimentacao,
                                    BindingResult bindingResult,
                                    Model model) {

        if (bindingResult.hasErrors()) {
            return "cardapio_gerente";
        }

        try {
            alimentacaoService.atualizarAlimentacao(alimentacao);
        } catch (HootelException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "cardapio_gerente";
    }
}
