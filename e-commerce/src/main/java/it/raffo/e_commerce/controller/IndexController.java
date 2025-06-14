package it.raffo.e_commerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.raffo.e_commerce.model.Categoria;
import it.raffo.e_commerce.model.Prodotto;
import it.raffo.e_commerce.repository.ProdottoRepo;
import jakarta.websocket.server.PathParam;
import it.raffo.e_commerce.repository.CategoryRepo;
import it.raffo.e_commerce.repository.MarcaRepo;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    ProdottoRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    MarcaRepo brandRepo;

    @GetMapping("/home")
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {

        List<Prodotto> productList = new ArrayList<>();
        List<Prodotto> evidenceList = new ArrayList<>();

        if (keyword != null && !keyword.isBlank()) {

            productList = productRepo.cercaProdotti(keyword);

        } else {

            productList = productRepo.findAll();
        }

        evidenceList = productRepo.findByEvidenceTrue();

        model.addAttribute("list", productList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("evidence", evidenceList);
        model.addAttribute("category", categoryRepo.findAll());

        return "/home/index";
    }

    @GetMapping("/home/{category_id}")
    public String getCategory(@PathVariable("category_id") Integer categoryId,
            Model model) {

        List<Prodotto> productList = new ArrayList<>();

        productList = productRepo.findByCategoriaId(categoryId);

        model.addAttribute("list", productList);
        model.addAttribute("evidence", productRepo.findByEvidenceTrue());
        model.addAttribute("category", categoryRepo.findAll());

        return "/home/index";
    }

}
