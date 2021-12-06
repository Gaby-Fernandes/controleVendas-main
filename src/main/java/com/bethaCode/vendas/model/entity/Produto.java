package com.bethaCode.vendas.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O campo descricao deve ser informado para o Produto!")
    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(name = "valor_unitario")
    @Min(value = 1, message = "O Valor unitario do Produto não pode ser menor do que 1")
    private BigDecimal valorUnitario;

    @Column(name = "data_create")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCreate;

    @Column(name = "movimenta_estoque", length = 1)
    private String movimentaEstoque;

    @Column(name = "quantidade_inicial")
    @Min(value = 0, message = "A quantidade do Produto não pode ser menor do que 0")
    private BigDecimal quantidadeInicial;

    @PrePersist
    public void prePersiste(){
        setDataCreate(LocalDate.now());
    }
}