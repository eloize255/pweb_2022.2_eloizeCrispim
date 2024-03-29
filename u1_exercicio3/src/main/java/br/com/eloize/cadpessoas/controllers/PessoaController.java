package br.com.eloize.cadpessoas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.eloize.cadpessoas.model.Pessoa;
import br.com.eloize.cadpessoas.repositories.PessoaRepository;

@Controller
@RequestMapping("/")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepo;

    public PessoaController(PessoaRepository pessoaRepo) {
        this.pessoaRepo = pessoaRepo;
    }

    @GetMapping
    public String index() {
        return "index.html";
    }

    @GetMapping("/listaPessoas")
    public ModelAndView listaPessoas() {
        List<Pessoa> todasAsPessoas = pessoaRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("listaPessoas");
        modelAndView.addObject("todasAsPessoas", todasAsPessoas);
        return modelAndView;
    }

    @GetMapping("/adicionaPessoa")
    public ModelAndView adicionaPessoa() {
        ModelAndView modelAndView = new ModelAndView("adicionaPessoa");
        modelAndView.addObject(new Pessoa());
        return modelAndView;
    }

    @PostMapping("/adicionaPessoa")
    public String adicionaPessoa(Pessoa p) {
        this.pessoaRepo.save(p);
        return "redirect:/listaPessoas";
    }

    @GetMapping("/remover/{id}")
    public ModelAndView removerPessoa(@PathVariable("id") long id) {
        Pessoa aRemover = pessoaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido" + id));

        pessoaRepo.delete(aRemover);
        return new ModelAndView("redirect:/listaPessoas");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView formularioEditaPessoa(@PathVariable("id") long id) {
        Pessoa aEditar = pessoaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalido" + id));
        ModelAndView modelAndView = new ModelAndView("editaPessoa");
        modelAndView.addObject(aEditar);
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editaPessoa(@PathVariable("id") long id, Pessoa p) {
        this.pessoaRepo.save(p);
        return "redirect:/listarPessoas";
    }

}
