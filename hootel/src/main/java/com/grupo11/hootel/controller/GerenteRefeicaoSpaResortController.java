package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.RefeicaoSpaResort;
import com.grupo11.hootel.entity.enums.ObjetivosSpaResort;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarSpaResort;
import com.grupo11.hootel.exceptions.HoospedagemException;
import com.grupo11.hootel.service.RefeicaoService;
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
@RequestMapping("/spa")
public class GerenteRefeicaoSpaResortController {
    private final RefeicaoService refeicaoService;

    @Autowired
    public GerenteRefeicaoSpaResortController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @GetMapping("/mostrarCardapio")
    public String mostrarCardapio(Model model) {
        model.addAttribute("refeicao", new RefeicaoSpaResort());
        model.addAttribute("categorias", PreferenciaAlimentarSpaResort.values());
        model.addAttribute("objetivos", ObjetivosSpaResort.values());

        return "spa/cardapio_gerente";
    }

    @PostMapping("/adicionarCardapio")
    public String addrefeicaoEmCardapio(@Valid @ModelAttribute("cardapio") RefeicaoSpaResort refeicao,
                                        BindingResult bindingResult,
                                        Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("refeicao", new RefeicaoSpaResort());
            model.addAttribute("categorias", PreferenciaAlimentarSpaResort.values());
            model.addAttribute("objetivos", ObjetivosSpaResort.values());
            return "spa/cardapio_gerente";
        }

        try {
            refeicaoService.addRefeicao(refeicao);
        } catch (HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("refeicao", new RefeicaoSpaResort());
        model.addAttribute("categorias", PreferenciaAlimentarSpaResort.values());
        model.addAttribute("objetivos", ObjetivosSpaResort.values());
        return "spa/cardapio_gerente";
    }

    @PostMapping("/atualizaCardapio")
    public String atualizarrefeicaoEmCardapio(@Valid @ModelAttribute("cardapio") RefeicaoSpaResort refeicao,
                                              BindingResult bindingResult,
                                              Model model) {

        if (bindingResult.hasErrors()) {
            return "spa/cardapio_gerente";
        }

        try {
            refeicaoService.atualizarRefeicao(refeicao);
        } catch (HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "spa/cardapio_gerente";
    }
}
