package com.ifoodtest.giolo.playlist.service;

import com.ifoodtest.giolo.playlist.model.Weather;
import com.ifoodtest.giolo.playlist.model.music.Playlist;

/**
 * Obtém as informações de Playlist 
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public interface SuggestionService {

	Playlist suggestBy(final Weather weather);
}
