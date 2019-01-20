package com.ifoodtest.giolo.playlist.service.weather;

/**
 * Representa os dados retornados pelo serviço do OpenWeather
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public class OpenWeatherResponse {
	
	private WeatherMain main;
	
	public WeatherMain getMain() {
		return main;
	}

	public void setMain(WeatherMain main) {
		this.main = main;
	}
	
	@Override
	public String toString() {
		return "OpenWeatherResponse [main=" + main + "]";
	}

	public static class WeatherMain {
		
		private float temp;

		public float getTemp() {
			return temp;
		}

		public void setTemp(float temp) {
			this.temp = temp;
		}

		@Override
		public String toString() {
			return "WeatherMain [temp=" + temp + "]";
		}
		
	}
	
}
