package br.com.programa.torcedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.programa.torcedor.entity.TimeCoracao;
import br.com.programa.torcedor.repository.TimeCoracaoRepository;

@Service
@Validated
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TimeCoracaoService {
	
	@Autowired
	private TimeCoracaoRepository timeCoracaoRepository;
	
	@Transactional
	public TimeCoracao salvar(final TimeCoracao TimeCoracao){
		return this.timeCoracaoRepository.save(TimeCoracao);
	}
	
	public TimeCoracao pesquisarPorId(final Long id) {
		return this.timeCoracaoRepository.findOne(id);
	}
	
	public List<TimeCoracao> pesquisarTodas(){
		return this.timeCoracaoRepository.findAll();
	}
	
	public TimeCoracao buscarTimePorNome(String nome){
		return this.timeCoracaoRepository.findByNomeIgnoreCase(nome);
	}
}
