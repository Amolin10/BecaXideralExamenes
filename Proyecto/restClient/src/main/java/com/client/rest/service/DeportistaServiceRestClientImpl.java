package com.client.rest.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.client.rest.model.Deportista;

/**
 * Implementación de la capa de servicio
 * Se conecta al backend para consumir el servicio REST
 * @author Amolin
 */
@Service
public class DeportistaServiceRestClientImpl implements DeportistaService {

	//url del backend
	private RestTemplate restTemplate;
	private String crmRestUrl;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	/**
	 * En el constructor se inyecta la url del servicio REST (backend) 
	 * @param theRestTemplate es inyectada por Spring
	 * @param theUrl url del backend
	 */
	@Autowired
	public DeportistaServiceRestClientImpl(RestTemplate theRestTemplate, 
										@Value("${crm.rest.url}") String theUrl) {
		
		restTemplate = theRestTemplate;
		crmRestUrl = theUrl;
				
		logger.info("Loaded property:  crm.rest.url=" + crmRestUrl);
	}
	
	/**
	 * Obtener una lista de deportistas
	 * Consume al servicio REST 
	 */
	@Override
	public List<Deportista> getDeportistas() {
		
		//Logger
		logger.info("***OBTENER LISTA DE DEPORTISTAS DESDE EL SERVICE REST CLIENTE");
		logger.info("in getDeportistas(): Calling REST API " + crmRestUrl);

		//Hace una llamada al servicio REST enviando un método GET
		ResponseEntity<List<Deportista>> responseEntity = 
											restTemplate.exchange(crmRestUrl, HttpMethod.GET, null, 
													 new ParameterizedTypeReference<List<Deportista>>() {});

		//Obtiene la lista de deportistas de la respuesta
		List<Deportista> deportistas = responseEntity.getBody();

		logger.info("in getDeportistas(): deportistas" + deportistas);
		
		//Regresa la lista de deportistas
		return deportistas;
	}

	/**
	 * Obtiene un deportista por su id
	 */
	@Override
	public Deportista getDeportista(int theId) {
		logger.info("***OBTENER UN DEPORTISTA DESDE EL SERVICE REST CLIENTE");
		logger.info("in getDeportista(): Calling REST API " + crmRestUrl);

		//Método GET + el id del deportista
		Deportista theDeportista = 
				restTemplate.getForObject(crmRestUrl + "/" + theId, 
						Deportista.class);

		logger.info("in saveDeportista(): theDeportista=" + theDeportista);
		
		//Regresa el deportista
		return theDeportista;
	}

	/**
	 * Guarda un deportista
	 */
	@Override
	public void saveDeportista(Deportista theDeportista) {

		logger.info("in saveDeportista(): Calling REST API " + crmRestUrl);
		
		int deportistaId = theDeportista.getId();

		//Si el id es 0, hace un POST
		if (deportistaId == 0) {
			// add employee
			logger.info("***SALVAR UN DEPORTISTA DESDE EL SERVICE REST CLIENTE");

			restTemplate.postForEntity(crmRestUrl, theDeportista, String.class);			
		
		//Si el id es diferente de 0, hace un PUT (Actualización)
		} else {
			// update deportista
			logger.info("***ACTUALIZAR UN DEPORTISTA DESDE EL SERVICE REST CLIENTE");

			restTemplate.put(crmRestUrl, theDeportista);
		}

		logger.info("in saveDeportista(): success");	
	}

	/**
	 * Eliminar un deportista por su id
	 */
	@Override
	public void deleteDeportista(int theId) {
		logger.info("***BORRAR UN DEPORTISTA DESDE EL SERVICE REST CLIENTE");
		logger.info("in deleteDeportista(): Calling REST API " + crmRestUrl);

		//Método delete, se indica el id del deportista que será eliminado
		restTemplate.delete(crmRestUrl + "/" + theId);

		logger.info("in deleteDeportista(): deleted deportista theId=" + theId);
	}

}
