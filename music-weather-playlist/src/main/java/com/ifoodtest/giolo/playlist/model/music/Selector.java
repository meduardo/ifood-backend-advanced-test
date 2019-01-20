package com.ifoodtest.giolo.playlist.model.music;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifoodtest.giolo.playlist.model.Weather;
import com.ifoodtest.giolo.playlist.model.WeatherGroup;
import com.ifoodtest.giolo.playlist.model.music.MusicGenre.Genres;

/**
 * Permite criar seletores, de acordo com algum critério, no nosso exemmplo, 
 * podemos selecionar algum genero, com base em informações do clima
 * Futuramente pode ser utilizado para compor mais formas de selecionar, ou
 * evoluir os processo de seleção, apenas acrescentando novas implementações.
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 * @param <S> 
 * @param <T>
 */
@FunctionalInterface
public interface Selector<S, T> {

	Optional<T> selectBy(final S originInfo);

	public enum MusicGenreSelector implements Selector<Weather, MusicGenre> {
		
		WEATHER_GROUP {
			
			@Override
			public Optional<MusicGenre> selectBy(final Weather weather) {
				
				LOGGER.info("Looking MUSIC GENRE by weather: {}", weather);
				
				WeatherGroup classification = WeatherGroup.classify(weather.getTemperature());
				
				return Optional.ofNullable(
						GENRE_BY_TEMPERATURE.get(classification)
				);				   
			}
		};

		private static final Logger LOGGER = LoggerFactory.getLogger(MusicGenreSelector.class);

		private static final Map<WeatherGroup, MusicGenre> GENRE_BY_TEMPERATURE = musicGenreMap();
		
		private static final EnumMap<WeatherGroup, MusicGenre> musicGenreMap() {
			
			EnumMap<WeatherGroup, MusicGenre> genresByTemperature = new EnumMap<>(WeatherGroup.class);
			
			genresByTemperature.put(WeatherGroup.HOT, Genres.PARTY);
			genresByTemperature.put(WeatherGroup.WARM, Genres.POP);
			genresByTemperature.put(WeatherGroup.COLD, Genres.ROCK);
			genresByTemperature.put(WeatherGroup.FREEZING, Genres.CLASSICAL);
			
			return genresByTemperature;
		}
	}

}
