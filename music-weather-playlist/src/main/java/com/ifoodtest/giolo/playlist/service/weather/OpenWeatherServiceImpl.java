package com.ifoodtest.giolo.playlist.service.weather;

import java.util.function.UnaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.ifoodtest.giolo.playlist.Caches;
import com.ifoodtest.giolo.playlist.exception.WeatherException;
import com.ifoodtest.giolo.playlist.model.GPS;
import com.ifoodtest.giolo.playlist.model.Temperature;
import com.ifoodtest.giolo.playlist.model.TemperatureScale;
import com.ifoodtest.giolo.playlist.model.Weather;
import com.ifoodtest.giolo.playlist.service.WeatherService;


@Service
public class OpenWeatherServiceImpl implements WeatherService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherServiceImpl.class);
	
	private final WebClient webClient;

	private final OpenWeatherProperties props;
	
	public OpenWeatherServiceImpl(final WebClient.Builder webClientBuilder, final OpenWeatherProperties props) {
		//TODO Verificar se os valores são validos, por ex se a base url é mesmo uma URL valida valida, etc.
		this.props = props;
		this.webClient = webClientBuilder.baseUrl(
											props.getBaseUrl()
										  )
										 .build();
		
		LOGGER.info("Properties: {}", props);
		LOGGER.info("Web Client: {}", webClient);
		LOGGER.info("Open Weather Service created. ");
	}
	
	@Override
	@Cacheable(Caches.WEATHER_BY_CITY)
	public Weather weatherIn(final String cityName) {
		
		LOGGER.info("Looking weather for City: {}", cityName);
		
		return requestWeatherFor(
				builder -> builder.queryParam("q", cityName)
		);

	}

	@Override
	@Cacheable(Caches.WEATHER_BY_GPS)
	public Weather weatherIn(GPS location) {
		
		LOGGER.info("Looking weather for GPS Location: {}", location);
		
		return requestWeatherFor(
				builder -> builder.queryParam("lat", location.getLatitude())
				  				  .queryParam("lon", location.getLongitude())
		);
						
	}
	
	private final UnaryOperator<UriBuilder> commonUri() {
		return builder-> builder.path(props.getWeatherResource())
				 				.queryParam("APPID", props.getAppId())
				 				.queryParam("units", props.getWeatherUnits());
	}
	
	private final <T> T commonGet(final UnaryOperator<UriBuilder> querySetter, final Class<T> objectType){
		/* 
		 * Obs.: A quem esta analisando o código feito:
		 * 
		 * Estou usando o WebClient, que é o novo client do Spring, que passará a ser
		 * o client padrão ao invés do "RestTemplate", usado já há algum tempo.
		 * estamos projetando as apis para serem bloqueantes, e não reativas no primeiro
		 * momento, futuramente sera mais facil reaproveitarmos estas operações caso migremos
		 * para uma stack reativa, com reactor.
		 * 
		 * Apenas alterando os retornos para tipos reativos, como Mono, Flux...
		 * 
		 */
		return webClient.get()
						.uri(commonUri()
								.andThen(querySetter)
								.andThen(UriBuilder::build)
						 )
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.retrieve()
						.bodyToMono(objectType)
						.block();
	}
	
	private final Weather requestWeatherFor(final UnaryOperator<UriBuilder> querySetter) {
		try {
			OpenWeatherResponse resp = commonGet(querySetter, OpenWeatherResponse.class);
			//TODO considerar o parametros units para saber a escala usada.	/
			//TODO podemos salvar o ID da Cidade, e criarmos um CACHE desta informação.

			return new Weather(
							Temperature.with(resp.getMain().getTemp(), TemperatureScale.CELSIUS)
					   );	

		} catch (Exception e) {
			//TODO Tratar os erros apropriadamente, com message bundles, por ex.
			throw new WeatherException(e);
		}
	}
	
}
