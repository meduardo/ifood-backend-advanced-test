package com.ifoodtest.giolo.playlist.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ifoodtest.giolo.playlist.exception.WeatherException;
import com.ifoodtest.giolo.playlist.model.GPS;
import com.ifoodtest.giolo.playlist.model.Weather;
import com.ifoodtest.giolo.playlist.model.dto.PlaylistDTO;
import com.ifoodtest.giolo.playlist.service.SuggestionService;
import com.ifoodtest.giolo.playlist.service.WeatherService;

/**
 * 
 * Controller responsável por obter as informações de Playlist desejada.
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
@RestController
@RequestMapping("/v1/playlists")
public class PlaylistController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistController.class);

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private SuggestionService suggestionService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<PlaylistDTO> playlist(@RequestParam("city") Optional<String> city,
            									      @RequestParam("lat") Optional<Double> latitude,
            									      @RequestParam("lon") Optional<Double> longitude) {
		
		LOGGER.info("Searching playlist, criteria: City[{}], Lat:[{}] - Long:[{}]", city, latitude, longitude);
		
		return possibleWeather(city, latitude, longitude)
				.map(suggestionService::suggestBy)
				.map(PlaylistDTO::valueOf)
				.map(ResponseEntity::ok)
				.orElseGet(
					() -> ResponseEntity.badRequest()
										.build()
				);
		    
	}
	
	private Optional<Weather> possibleWeather(final Optional<String> city,	
											  final Optional<Double> latitude, 
											  final Optional<Double> longitude) {
		
		try {
			Optional<Weather> weatherByCity = city.map(weatherService::weatherIn);
	
			if(weatherByCity.isPresent()) {
				return weatherByCity;
			}
		
			if(latitude.isPresent() && longitude.isPresent()) {
				return Optional.ofNullable(
									GPS.with(latitude.get(),
									         longitude.get())
							   )
							   .map(weatherService::weatherIn);	
			}
		
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					  						  "Error to find weather. ", new WeatherException());
		}
		
		// TODO - Poderiamos definir um @ControllerAdvice, com um @ExceptionHandler, para um tratamento mais genérico na aplicação,
		// com um DTO de mensagens de erro, e seus devidos trtamento, de forma que fique facil evidenciar o que 
		// é um erro inesperado, um problema com os dados d cliente, ou um problema de negócio mesmo
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
										  "Location is not defined! Need city, ou GPS coordinates. ", new WeatherException());
				    
	}
	
}