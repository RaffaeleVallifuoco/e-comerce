package it.raffo.e_commerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.raffo.e_commerce.model.Prodotto;
import it.raffo.e_commerce.repository.ProdottoRepo;
import it.raffo.e_commerce.repository.CategoryRepo;
import it.raffo.e_commerce.repository.MarcaRepo;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    ProdottoRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    MarcaRepo brandRepo;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {

        List<Prodotto> productList = new ArrayList<>();

        if (keyword != null && !keyword.isBlank()) {

            productRepo.cercaProdotti(keyword);

        } else {

            productList = productRepo.findAll();
        }

        model.addAttribute("list", productList);
        model.addAttribute("keyword", keyword);

        return "/home/index";
    }

}
