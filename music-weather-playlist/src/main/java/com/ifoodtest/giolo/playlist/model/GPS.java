package com.ifoodtest.giolo.playlist.model;

/**
 * Cordenadas GPS
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public class GPS {
	
	private final Double latitude;
	
	private final Double longitude;
	
	private GPS(final Double latitude, final Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public static final GPS with(final Double latitude, final Double longitude) {
		//TODO Checar se são cordenadas validas.
		return new GPS(latitude, longitude);
	}

	public final Double getLatitude() {
		return latitude;
	}

	public final Double getLongitude() {
		return longitude;
	}
	
	@Override
	public String toString() {
		return "GPS [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
		GPS other = (GPS) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}

	
}
