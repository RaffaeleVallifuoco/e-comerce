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
import it.raffo.e_commerce.model.Prodotto;
import it.raffo.e_commerce.repository.ProdottoRepo;
import it.raffo.e_commerce.repository.CategoryRepo;
import it.raffo.e_commerce.repository.MarcaRepo;

@Controller
@RequestMapping("/tab")
public class TabController {

    @Autowired
    ProdottoRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    MarcaRepo brandRepo;

    @GetMapping("/home")
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {

        List<Prodotto> productList = new ArrayList<>();

        if (keyword != null && !keyword.isBlank()) {

            productList = productRepo.cercaProdotti(keyword);

        } else {

            productList = productRepo.findAll();
        }

        model.addAttribute("list", productList);
        model.addAttribute("keyword", keyword);

        return "/tab/index";
    }

    @GetMapping("/home/{category_id}")
    public String getCategory(@PathVariable("category_id") Integer categoryId,
            Model model) {

        List<Prodotto> productList = new ArrayList<>();

        productList = productRepo.findByCategoriaId(categoryId);

        model.addAttribute("list", productList);

        return "/tab/index";
    }

}
