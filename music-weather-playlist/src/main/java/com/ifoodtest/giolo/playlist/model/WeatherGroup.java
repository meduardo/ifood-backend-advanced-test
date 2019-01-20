package com.ifoodtest.giolo.playlist.model;

import static com.ifoodtest.giolo.playlist.model.TemperatureScale.CELSIUS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifoodtest.giolo.playlist.exception.WeatherException;
/**
 * Represetnas as diferentes classificações de clima.
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public enum WeatherGroup {
	
	//TODO Checar limite nas escalas de temperatura possiveis
	
	HOT(31, Float.MAX_VALUE),
	WARM(15, 30), 
	COLD(10, 14), 
	FREEZING(-Float.MAX_VALUE, 10);  
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherGroup.class);
	
	private final float min;
	private final float max;
	
	private WeatherGroup(final float min, final float max){
		this.max = max;
		this.min = min;
	}
	
	private final boolean contains(final float value) {
		return min <= value && max >= value;
	}
	
	//TODO Pensar em uam forma de fazer cache, dos resultados desta classificação
	public static final WeatherGroup classify(final Temperature temperature) {
		float value = temperature.valueIn(CELSIUS);
		
		for(WeatherGroup classification : values()) {
			
			if(classification.contains(value)) {
				LOGGER.info("Classifying {} in {}", temperature, classification);
				return classification;
			}
		}
		
		throw new WeatherException("Temperature ["+temperature + "] can't be classified! Verify group ranges!!");
	}
}
