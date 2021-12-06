package com.bethaCode.vendas.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Getter@Setter

public class Pessoas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O campo Nome deve ser informado!!")
    @Column(nullable = false, length = 100)
    private String nome;

    @Column
    @Min(value = 18, message = "O Cliente deve ter no mínimo  18 anos!!")
    private Integer idade;

    @Column(length = 11)
    private String cpf;

    @Column(length = 2)
    private char sexo;

    @Column(length = 10)
    private String valorCredito;

}
