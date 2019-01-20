package com.ifoodtest.giolo.playlist.model;

public class Weather {

	private Temperature temperature;

	public Weather(final Temperature temperature) {
		this.temperature = temperature;
	}

	public final Temperature getTemperature() {
		return temperature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Weather [temperature=" + temperature + "]";
	}
	
}
