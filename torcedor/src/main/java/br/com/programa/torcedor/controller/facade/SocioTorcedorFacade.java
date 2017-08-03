package br.com.programa.torcedor.controller.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.programa.torcedor.entity.SocioTorcedor;
import br.com.programa.torcedor.entity.campanha.Campanha;
import br.com.programa.torcedor.entity.campanha.TorcedorCampanha;
import br.com.programa.torcedor.exception.EmailException;
import br.com.programa.torcedor.exception.SocioTorcedorException;
import br.com.programa.torcedor.exception.TimeCoracaoException;
import br.com.programa.torcedor.service.SocioTorcedorService;

@Component
public class SocioTorcedorFacade {
	@Autowired
	private SocioTorcedorService socioTorcedorService;
		
	public SocioTorcedor salvar(SocioTorcedor socioTorcedor) throws EmailException {
		return this.socioTorcedorService.salvar(socioTorcedor.toSocioTorcedor());
	}
	
	public SocioTorcedor alterar(Long id, SocioTorcedor socioTorcedor) throws SocioTorcedorException, EmailException { 
		Optional.ofNullable(socioTorcedorService.pesquisarPorId(id)).orElseThrow(() -> new SocioTorcedorException());
		SocioTorcedor socioTorcedorRequest = socioTorcedor.toSocioTorcedor();
		socioTorcedorRequest.setId(id);
		return this.socioTorcedorService.salvar(socioTorcedorRequest);
	}
	
	public void excluir(Long id) throws SocioTorcedorException  {
		SocioTorcedor socioTorcedor = Optional.ofNullable(socioTorcedorService.pesquisarPorId(id)).orElseThrow(() -> new SocioTorcedorException());
		this.socioTorcedorService.deletar(socioTorcedor);
	}
	
	public SocioTorcedor buscar(Long id) throws SocioTorcedorException {
		return Optional.ofNullable(socioTorcedorService.pesquisarPorId(id)).orElseThrow(() -> new SocioTorcedorException());
	}
	
	public List<SocioTorcedor> buscarTodos() {
		return socioTorcedorService.pesquisarTodos().stream().collect(Collectors.toList());
	}
	
	public Campanha buscarCampanhaPorId(Long idCampanha) {
		return socioTorcedorService.pesquisarCampanhaPorId(idCampanha);
	}
	
	public ResponseEntity<Campanha[]> buscarCampanhasVigentesPorTime(String nome) throws TimeCoracaoException {
		return socioTorcedorService.pesquisarCampanhasVigentesPorTime(nome);
	}
	
	public ResponseEntity<Campanha[]> buscarCampanhasVigentes() {
		return socioTorcedorService.pesquisarCampanhasVigentes();
	}
	
	public Campanha salvarCampanha(Campanha campanhaRequestDTO) {
		return this.socioTorcedorService.salvarCampanha(campanhaRequestDTO);
	}
	
	public TorcedorCampanha associarCampanha(TorcedorCampanha torcedorCampanha) throws SocioTorcedorException {
		return this.socioTorcedorService.associarCampanha(torcedorCampanha);
	}
}
