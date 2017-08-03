package br.com.programa.torcedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import br.com.programa.torcedor.controller.facade.TimeCoracaoFacade;
import br.com.programa.torcedor.entity.SocioTorcedor;
import br.com.programa.torcedor.entity.TimeCoracao;
import br.com.programa.torcedor.entity.campanha.Campanha;
import br.com.programa.torcedor.entity.campanha.TorcedorCampanha;
import br.com.programa.torcedor.exception.EmailException;
import br.com.programa.torcedor.exception.SocioTorcedorException;
import br.com.programa.torcedor.exception.TimeCoracaoException;
import br.com.programa.torcedor.repository.SocioTorcedorRepository;

@Service
@Validated
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SocioTorcedorService {
	
	@Autowired
	private SocioTorcedorRepository socioTorcedorRepository;
	
	@Autowired 
	private TimeCoracaoFacade timeCoracaoFacade;

	@Transactional
	public SocioTorcedor salvar(final SocioTorcedor socioTorcedor) throws EmailException{
		if (this.emailExistente(socioTorcedor.getEmail())) throw new EmailException();
		return this.socioTorcedorRepository.save(socioTorcedor);
	}

	@Transactional
	public void deletar(final SocioTorcedor socioTorcedor){
		this.socioTorcedorRepository.delete(socioTorcedor);
	}
	
	public SocioTorcedor pesquisarPorId(final Long id) throws SocioTorcedorException{
		if (this.socioTorcedorRepository.findOneById(id) == null)
			throw new SocioTorcedorException();
		return this.socioTorcedorRepository.findOneById(id);
	}
	
	public List<SocioTorcedor> pesquisarTodos(){
		return this.socioTorcedorRepository.findAll();
	}
	
	private boolean emailExistente(final String email){
		SocioTorcedor socio = this.socioTorcedorRepository.findByEmail(email);
		return socio != null ? true : false;
	}
	
	public Campanha pesquisarCampanhaPorId(final Long idCampanha) {
		RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/campanha/{idCampanha}";
	    Campanha campanha = restTemplate.getForObject(url, Campanha.class, idCampanha);
	    return campanha;
	}
	
	public ResponseEntity<Campanha[]> pesquisarCampanhasVigentesPorTime(String nome) throws TimeCoracaoException{
		TimeCoracao timeCoracaoResponse = this.timeCoracaoFacade.buscarTimePorNome(nome);
		RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/campanha/time/{codigoTime}";
	    ResponseEntity<Campanha[]> campanhaResponseDTO = restTemplate.getForEntity(url, Campanha[].class, timeCoracaoResponse.getId());
	    return campanhaResponseDTO;
	}
	
	public ResponseEntity<Campanha[]> pesquisarCampanhasVigentes() {
		RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/campanha";
	    ResponseEntity<Campanha[]> campanha = restTemplate.getForEntity(url, Campanha[].class);
	    return campanha;
	}
	
	public Campanha salvarCampanha(Campanha campanha) {
		RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/campanha";
	    Campanha campanhaRest = restTemplate.postForObject(url, campanha, Campanha.class);
	    return campanhaRest;
	}
	
	public TorcedorCampanha associarCampanha(TorcedorCampanha torcedorCampanha) throws SocioTorcedorException {
		if(!this.emailExistente(torcedorCampanha.getEmail())) throw new SocioTorcedorException();
		RestTemplate restTemplate = new RestTemplate();
	    String url="http://localhost:8081/associacoes";
	    TorcedorCampanha torcedorCampanhaRest = restTemplate.postForObject(url, torcedorCampanha, TorcedorCampanha.class);
	    return torcedorCampanhaRest;
	}
}
