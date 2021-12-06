package com.bethaCode.vendas.rest;

import com.bethaCode.vendas.model.entity.Produto;
import com.bethaCode.vendas.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoController(ProdutoRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@Valid @RequestBody Produto produto){
        return repository.save(produto);
    }

    @GetMapping("{id}")
    public Produto acharPorId(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O produto " + id + " não existe em nossa Aplicação!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(produto ->{
                    repository.delete(produto);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O produto " + id + " não existe em nossa aplicação!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody Produto dadoAtualizado){
        repository
                .findById(id)
                .map(produto -> {
                    produto.setDescricao(dadoAtualizado.getDescricao());
                    produto.setMovimentaEstoque(dadoAtualizado.getMovimentaEstoque());
                    produto.setQuantidadeInicial(dadoAtualizado.getQuantidadeInicial());
                    produto.setValorUnitario(dadoAtualizado.getValorUnitario());
                    return repository.save(produto);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O produto " + id + " não existe em nossa aplicação!"));
    }
}