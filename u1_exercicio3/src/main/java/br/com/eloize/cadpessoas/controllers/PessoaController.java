package br.com.eloize.cadpessoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import antlr.collections.List;
import br.com.eloize.cadpessoas.model.Pessoa;
import br.com.eloize.cadpessoas.repositories.PessoaRepository;

@Controller
@RequestMapping("/")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepo;

    public PessoaController(PessoaRepository pessoaRepo){
        this.pessoaRepo = pessoaRepo;
    }

    @GetMapping
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/listarPessoas")
    public ModelAndView listarPessoas(){
        List<Pessoa> todasAsPessoas = pessoaRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("listarPessoas");
        ModelAndView.addObject("todasAsPessoas", todasAsPessoas); 
        return ModelAndView; 
    }
}
