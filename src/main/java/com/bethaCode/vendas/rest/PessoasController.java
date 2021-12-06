package com.bethaCode.vendas.rest;


import com.bethaCode.vendas.model.entity.Pessoas;
import com.bethaCode.vendas.model.entity.Produto;
import com.bethaCode.vendas.model.repository.PessoasRepository;
import com.bethaCode.vendas.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pessoas")
public class PessoasController {

    private final PessoasRepository repository;

    @Autowired
    public PessoasController(PessoasRepository repository) {this.repository = repository;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public Pessoas salvar(@Valid @RequestBody Pessoas pessoas) {return repository.save(pessoas);}

    @GetMapping("{id}")
    public Pessoas acharPorId(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O Cliente " + id + " não existe em nossa Aplicação!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(pessoas ->{
                    repository.delete(pessoas);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O Cliente " + id + " não existe em nossa aplicação!"));
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody Pessoas dadoAtualizado){
        repository
                .findById(id)
                .map(pessoas -> {
                    pessoas.setNome(dadoAtualizado.getNome());
                    pessoas.setIdade(dadoAtualizado.getIdade());
                    pessoas.setCpf(dadoAtualizado.getCpf());
                    pessoas.setValorCredito(dadoAtualizado.getValorCredito());
                    pessoas.setSexo(dadoAtualizado.getSexo());
                    return repository.save(pessoas);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O Cliente " + id + " não existe em nossa aplicação!"));
    }
}
