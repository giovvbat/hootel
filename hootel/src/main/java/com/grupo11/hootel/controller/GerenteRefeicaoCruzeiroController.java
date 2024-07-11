package com.grupo11.hootel.controller;

import com.grupo11.hootel.entity.RefeicaoCruzeiro;
import com.grupo11.hootel.entity.enums.PreferenciaAlimentarCruzeiro;
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
@RequestMapping("/cruzeiro")
public class GerenteRefeicaoCruzeiroController {
    private final RefeicaoService refeicaoService;

    @Autowired
    public GerenteRefeicaoCruzeiroController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @GetMapping("/mostrarCardapio")
    public String mostrarCardapio(Model model) {
        model.addAttribute("refeicao", new RefeicaoCruzeiro());
        model.addAttribute("categorias", PreferenciaAlimentarCruzeiro.values());

        return "cruzeiro/cardapio_gerente";
    }

    @PostMapping("/adicionarCardapio")
    public String addrefeicaoEmCardapio(@Valid @ModelAttribute("cardapio") RefeicaoCruzeiro refeicao,
                                        BindingResult bindingResult,
                                        Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("refeicao", new RefeicaoCruzeiro());
            model.addAttribute("categorias", PreferenciaAlimentarCruzeiro.values());
            return "cruzeiro/cardapio_gerente";
        }

        try {
            refeicaoService.addRefeicao(refeicao);
        } catch (HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("refeicao", new RefeicaoCruzeiro());
        model.addAttribute("categorias", PreferenciaAlimentarCruzeiro.values());
        return "cruzeiro/cardapio_gerente";
    }

    @PostMapping("/atualizaCardapio")
    public String atualizarrefeicaoEmCardapio(@Valid @ModelAttribute("cardapio") RefeicaoCruzeiro refeicao,
                                              BindingResult bindingResult,
                                              Model model) {

        if (bindingResult.hasErrors()) {
            return "cruzeiro/cardapio_gerente";
        }

        try {
            refeicaoService.atualizarRefeicao(refeicao);
        } catch (HoospedagemException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "cruzeiro/cardapio_gerente";
    }
}
