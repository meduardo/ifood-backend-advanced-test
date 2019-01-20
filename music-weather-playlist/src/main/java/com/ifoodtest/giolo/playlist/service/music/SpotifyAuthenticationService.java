package com.ifoodtest.giolo.playlist.service.music;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.ifoodtest.giolo.playlist.service.music.SpotifyResponse.SpotifyToken;

/**
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
@Service
public class SpotifyAuthenticationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpotifyAuthenticationService.class);

	private final SpotifyProperties props;
	
	private final WebClient webClient;
	
	private final String requestTokenAuthorization;
	
	public SpotifyAuthenticationService(final WebClient.Builder clientBuilder, final SpotifyProperties props) {
		this.props = props;
		this.webClient = clientBuilder.baseUrl(props.getAccountBaseUrl())
									  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
									  .build();
		
		String pair = props.getClientId() + ":" + props.getSecretKey();
		
		this.requestTokenAuthorization = "Basic " + Base64.getEncoder().encodeToString(pair.getBytes());
		
		LOGGER.info("Request Token Authorization: {}", requestTokenAuthorization);
	}
	
	
	//TODO Criar uam mecanismo para reaproveitar o TOken, e apenas solicitar outro quando 
	// o token obtido estiver vencido, ou seja quando obter 401 - Não autoriado, pedir um novo token
	//  e repetir a requisição.
	
	public final String apiAuthorizationHeader() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "client_credentials"); 
		SpotifyToken token = webClient.post()
									  .uri(builder -> builder.path(props.getTokenResource())
											  				 .queryParams(map)
											  				 .build())
									  .header(HttpHeaders.AUTHORIZATION, requestTokenAuthorization)
									  .retrieve()
									  .bodyToMono(SpotifyToken.class)
									  .block();

		return token.getToken_type() + " " + token.getAccess_token(); 
	}
}
