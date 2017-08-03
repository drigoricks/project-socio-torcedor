package br.com.programa.torcedor.controller.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.programa.torcedor.entity.TimeCoracao;
import br.com.programa.torcedor.exception.TimeCoracaoException;
import br.com.programa.torcedor.service.TimeCoracaoService;

@Component
public class TimeCoracaoFacade {
	@Autowired
	private TimeCoracaoService timeCoracaoService;
	
	public TimeCoracao buscar(Long id) throws TimeCoracaoException {
		return Optional.ofNullable(timeCoracaoService.pesquisarPorId(id)).orElseThrow(() -> new TimeCoracaoException());
	}
	
	public List<TimeCoracao> buscarTodas() {
		return timeCoracaoService.pesquisarTodas().stream().collect(Collectors.toList());
	}
	
	public TimeCoracao buscarTimePorNome(String nome) throws TimeCoracaoException {
		return Optional.ofNullable(timeCoracaoService.buscarTimePorNome(nome)).orElseThrow(() -> new TimeCoracaoException());
	}
}
