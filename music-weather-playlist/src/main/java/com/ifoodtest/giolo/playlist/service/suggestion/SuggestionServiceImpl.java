package com.ifoodtest.giolo.playlist.service.suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifoodtest.giolo.playlist.exception.PlaylistException;
import com.ifoodtest.giolo.playlist.model.Weather;
import com.ifoodtest.giolo.playlist.model.music.Playlist;
import com.ifoodtest.giolo.playlist.model.music.Selector.MusicGenreSelector;
import com.ifoodtest.giolo.playlist.service.MusicService;
import com.ifoodtest.giolo.playlist.service.SuggestionService;

/**
 * Sugere uma playlist de acordo com informações do clima.
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
@Service
public class SuggestionServiceImpl implements SuggestionService {

	@Autowired
	private MusicService musicService;
	
	@Override
	public final Playlist suggestBy(final Weather weather) {
		
		return MusicGenreSelector.WEATHER_GROUP
						  		 .selectBy(weather)
						         .map(musicService::findBy)
						         .orElseThrow(
						        		 () -> new PlaylistException("Could not find a playlist for the weather: " + weather)
						         );
	}
	
	
	
}
