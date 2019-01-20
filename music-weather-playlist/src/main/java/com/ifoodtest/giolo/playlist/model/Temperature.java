package com.ifoodtest.giolo.playlist.model;

public class Temperature {
	
	private final float value;
	private final TemperatureScale scale;
	
	private Temperature(final float value, final TemperatureScale scale) {
		this.value = value;
		this.scale = scale;
	}
	
	public static final Temperature with(final float value, final TemperatureScale scale) {
		return new Temperature(value, scale);
	}
	
	public final float valueIn(final TemperatureScale scale) {
		return scale.convert(this.value, this.scale);
	}
	
	public final float getValue() {
		return value;
	}

	public final TemperatureScale getScale() {
		return scale;
	}
	
	@Override
	public String toString() {
		return "Temperature [value=" + value + ", scale=" + scale + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((scale == null) ? 0 : scale.hashCode());
		result = prime * result + Float.floatToIntBits(value);
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
		Temperature other = (Temperature) obj;
		if (scale != other.scale)
			return false;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}
}
