package br.com.programa.torcedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.programa.torcedor.entity.TimeCoracao;

@Repository
public interface TimeCoracaoRepository extends JpaRepository<TimeCoracao, Long>{
	TimeCoracao findByNomeIgnoreCase(String nome);
}
