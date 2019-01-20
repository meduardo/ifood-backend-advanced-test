package com.ifoodtest.giolo.playlist;

/**
 * Configurações de cache
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public final class Caches {
 
	private Caches() {
	}
	
	public static final String WEATHER_BY_CITY = "weather_by_city";
	public static final String WEATHER_BY_GPS = "weather_by_gps";
	public static final String GENRE_BY_WEATHER = "genre_by_weather";

	//Podemos trabalhar com diferentes formas de cache, ou mesmo diferentes formas de implementar,
	// 
	// Esta é uam configuração simples em memória apenas, só para exercitarmos a ideía de ter cache
	// na aplicação, não necessariamente esta é a melhor abordagem para produção, onde devemos levar
	// outros fatores em consideração, como escalabilidade, dispnibilidade, volume,
	// possibilidade de cache centralizado (que sriva para N instancia da aplicação..)
	//, nao consumir a propria memória da aplicação, etc..  
	// um bom ex para produção, seria utilizar um cluster de redis (com lettuce) como cache.
    
}