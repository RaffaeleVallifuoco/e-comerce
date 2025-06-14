package it.raffo.e_commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import it.raffo.e_commerce.model.Prodotto;
import it.raffo.e_commerce.repository.ProdottoRepo;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProdottoRepo productRepo;

    @GetMapping("/show/{id}")
    public String getProductById(@PathVariable("category_id") Integer id,
            Model model) {

        Prodotto product = productRepo.getReferenceById(id);

        model.addAttribute("product", product);

        return "/common/detail";

    }

}
