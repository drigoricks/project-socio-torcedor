package br.com.programa.torcedor.mock;

import java.util.Calendar;

import br.com.programa.torcedor.entity.SocioTorcedor;

public class SocioTorcedorRequestMock {

	public static SocioTorcedor getSocioTorcedorRequest(){
		SocioTorcedor socioTorcedorRequest = new SocioTorcedor();
		socioTorcedorRequest.setNome("Rodrigo Lima");
		socioTorcedorRequest.setEmail("rodrigo@app.com");
		socioTorcedorRequest.setDataNascimento(Calendar.getInstance());
		socioTorcedorRequest.setTimeCoracao("Santo Andre");
		return socioTorcedorRequest;
	}

}
