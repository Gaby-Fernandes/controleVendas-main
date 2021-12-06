package com.bethaCode.vendas.model.repository;

import com.bethaCode.vendas.model.entity.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoasRepository extends JpaRepository<Pessoas, Integer> {
}
