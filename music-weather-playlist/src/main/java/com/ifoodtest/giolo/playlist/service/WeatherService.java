package com.ifoodtest.giolo.playlist.service;

import com.ifoodtest.giolo.playlist.model.GPS;
import com.ifoodtest.giolo.playlist.model.Weather;

/**
 * Obtém informações do clima
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */

public interface WeatherService {
	
	Weather weatherIn(final String cityName);
	
	Weather weatherIn(final GPS location);
	
}
