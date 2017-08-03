package br.com.programa.torcedor.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.programa.torcedor.controller.facade.SocioTorcedorFacade;
import br.com.programa.torcedor.entity.SocioTorcedor;
import br.com.programa.torcedor.entity.campanha.Campanha;
import br.com.programa.torcedor.entity.campanha.TorcedorCampanha;
import br.com.programa.torcedor.exception.EmailException;
import br.com.programa.torcedor.exception.SocioTorcedorException;
import br.com.programa.torcedor.exception.TimeCoracaoException;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "torcedor")
public class SocioTorcedorController {

	@Autowired
	private SocioTorcedorFacade socioTorcedorFacade;
	
	@ApiOperation(value = "Salva um Socio Torcedor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public SocioTorcedor salvar(@RequestBody @Valid SocioTorcedor socioTorcedor) throws EmailException {
		return this.socioTorcedorFacade.salvar(socioTorcedor);
	}
	
	@ApiOperation(value = "Altera um Socio Torcedor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public SocioTorcedor alterar(@PathVariable Long id, @RequestBody @Valid SocioTorcedor socioTorcedor) throws EmailException, SocioTorcedorException {
		return this.socioTorcedorFacade.alterar(id, socioTorcedor);
	}

	@ApiOperation(value = "Exclui um Socio Torcedor", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE , produces = {MediaType.APPLICATION_JSON_VALUE})
	public void excluir(@PathVariable Long id) throws SocioTorcedorException {
		this.socioTorcedorFacade.excluir(id);
	}
	
	@ApiOperation(value = "Consulta um Socio Torcedor por id", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public SocioTorcedor pesquisarId(@PathVariable Long id) throws SocioTorcedorException {
		return this.socioTorcedorFacade.buscar(id);
	}
	
	@ApiOperation(value = "Consulta todos os Socios Torcedores", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<SocioTorcedor> exibirTodos() {
		return socioTorcedorFacade.buscarTodos();
	}
	
	@ApiOperation(value = "Consulta uma Campanha da API por id", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/campanha/{idCampanha}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Campanha buscarCampanhaPorId(@PathVariable Long idCampanha) {
		return socioTorcedorFacade.buscarCampanhaPorId(idCampanha);
	}
	
	@ApiOperation(value = "Consulta Campanhas Vigentes por Time da API", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/campanha/time/{nome}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Campanha[]> buscarCampanhasVigentesPorTime(@PathVariable String nome) throws TimeCoracaoException {
		return socioTorcedorFacade.buscarCampanhasVigentesPorTime(nome);
	}
	
	@ApiOperation(value = "Consulta Campanhas Vigentes da API", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/campanha", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Campanha[]> buscarCampanhasVigentes() {
		return socioTorcedorFacade.buscarCampanhasVigentes();
	}
	
	@ApiOperation(value = "Cria uma Campanha da API", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/campanha", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Campanha criarCampanha(@RequestBody @Valid Campanha campanhaRequestDTO) {
		return this.socioTorcedorFacade.salvarCampanha(campanhaRequestDTO);
	}
	
	@ApiOperation(value = "Faz Associacao do Torcedor com a Campanha da API", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/associacao", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TorcedorCampanha associarCampanha(@RequestBody @Valid TorcedorCampanha torcedorCampanhaRequestDTO) throws SocioTorcedorException {
		return this.socioTorcedorFacade.associarCampanha(torcedorCampanhaRequestDTO);
	}
}