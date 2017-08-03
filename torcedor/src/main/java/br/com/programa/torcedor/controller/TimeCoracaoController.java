package br.com.programa.torcedor.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.programa.torcedor.controller.facade.TimeCoracaoFacade;
import br.com.programa.torcedor.entity.TimeCoracao;
import br.com.programa.torcedor.exception.TimeCoracaoException;

@RestController
@RequestMapping(value = "time")
public class TimeCoracaoController {

	@Autowired
	private TimeCoracaoFacade timeCoracaoFacade;
	
	@ApiOperation(value = "Consulta um Time pelo id", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TimeCoracao buscar(@PathVariable Long id) throws TimeCoracaoException {
		return timeCoracaoFacade.buscar(id);
	}
	
	@ApiOperation(value = "Consulta todos os times", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TimeCoracao> buscarTodos() {
		return timeCoracaoFacade.buscarTodas();
	}
	
	@ApiOperation(value = "Consulta um Time pelo nome", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TimeCoracao buscarTimePorNome(@PathVariable String nome) throws TimeCoracaoException {
		return timeCoracaoFacade.buscarTimePorNome(nome);
	}
}