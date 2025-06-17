package it.raffo.e_commerce.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.raffo.e_commerce.model.Prodotto;
import it.raffo.e_commerce.repository.CategoryRepo;
import it.raffo.e_commerce.repository.ProdottoRepo;
import it.raffo.e_commerce.repository.MarcaRepo;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProdottoRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    MarcaRepo marcaRepo;

    // FIND BY ID
    @GetMapping("/show/{id}")
    public String getProductById(@PathVariable("id") Integer id,
            Model model) {

        Prodotto product = productRepo.getReferenceById(id);

        model.addAttribute("product", product);
        model.addAttribute("evidence", productRepo.findByEvidenceTrue());
        model.addAttribute("category", categoryRepo.findAll());

        return "/home/detail";

    }

    // FORM INSERT / UPDATE
    @GetMapping({ "/form", "/form/{id}" })
    public String productForm(@PathVariable(required = false) Integer id, Model model) {
        if (id != null) {
            Prodotto product = productRepo.findById(id).orElse(new Prodotto());
            model.addAttribute("product", product);
            model.addAttribute("category", categoryRepo.findAll());
            model.addAttribute("isUpdate", true);
            model.addAttribute("brand", marcaRepo.findAll());
        } else {
            model.addAttribute("product", new Prodotto());
        }
        model.addAttribute("brand", marcaRepo.findAll());
        model.addAttribute("category", categoryRepo.findAll());
        model.addAttribute("isUpdate", false);

        return "/tab/form"; //
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String Update(@PathVariable("id") Integer id, @Valid @ModelAttribute("ticket") Prodotto productUpdate,
            BindingResult bindingresult,
            Model model) {

        if (bindingresult.hasErrors()) {
            model.addAttribute("evidence", productRepo.findByEvidenceTrue());
            model.addAttribute("category", categoryRepo.findAll());

            return "/{id}/edit";
        }
        Prodotto existingProduct = productRepo.getReferenceById(id);

        existingProduct.setNome(productUpdate.getNome());
        existingProduct.setDescrizione(productUpdate.getDescrizione());
        existingProduct.setEvidence(productUpdate.isEvidence());
        existingProduct.setCategoria(productUpdate.getCategoria());
        existingProduct.setMarca(productUpdate.getMarca());
        existingProduct.setPrezzo(productUpdate.getPrezzo());
        existingProduct.setQuantita(productUpdate.getQuantita());
        existingProduct.setDataProduzione(productUpdate.getDataProduzione());

        productRepo.save(existingProduct);

        return "redirect:/product/show/{id}";
    }

    // CREATE
    @PostMapping("/insert")
    public String store(@Valid @ModelAttribute("product") Prodotto productForm, BindingResult bindingresult,
            Model model) {

        if (bindingresult.hasErrors()) {

            model.addAttribute("category", categoryRepo.findAll());
            model.addAttribute("brand", marcaRepo.findAll());

            return "/admin/create";
        }

        productRepo.save(productForm);

        return "redirect:/tab/home";

    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {

        productRepo.deleteById(id);

        return "redirect:/tab/home";
    }
}
