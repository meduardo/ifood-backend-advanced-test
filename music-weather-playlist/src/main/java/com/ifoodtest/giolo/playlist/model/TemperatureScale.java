package com.ifoodtest.giolo.playlist.model;
/**
 * 
 * Representa as diferentes escalas de temperatura, e sua possiveis conversões.
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public enum TemperatureScale {
	
	CELSIUS {
		@Override
		public float toCelsius(float value) {
			return value; 
		}
		
		@Override
		public float toFahrenheit(float value)  {
			return 9 * (value / 5) + 32;
		}

		@Override
		public float convert(float value, TemperatureScale sourceScale) {
			return sourceScale.toCelsius(value); 
		}

	}, 
	
	FAHRENHEIT {
		@Override
		public float toFahrenheit(float value)  {
			return value;
		}

		@Override
		public float toCelsius(float value) {
			return (value - 32) * 5 / 9;
		}

		@Override
		public float convert(float value, TemperatureScale sourceScale) {
			return sourceScale.toFahrenheit(value);
		}
	};
	
	public float convert(float value, TemperatureScale sourceScale) {
        throw new AbstractMethodError();  
    }

    public float toFahrenheit(float value) {
        throw new AbstractMethodError();
    }

    public float toCelsius(float value) {
        throw new AbstractMethodError();
    }
}
