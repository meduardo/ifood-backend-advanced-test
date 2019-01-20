package com.ifoodtest.giolo.playlist.service.weather;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "openweathermap", ignoreUnknownFields = true)
public class OpenWeatherProperties {
	
	@NotEmpty(message = "Open Weather appId is required")
	private String appId;
	
	@NotEmpty(message = "Open Weather baseUrl is required")
	private String baseUrl;
	
	@NotEmpty(message = "Open Weather weatherResource is required")
	private String weatherResource;

	@NotEmpty(message = "Open Weather weatherUnits is required")
	private String weatherUnits;

	public void setWeatherResource(String weatherResource) {
		this.weatherResource = weatherResource;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setWeatherUnits(String weatherUnits) {
		this.weatherUnits = weatherUnits;
	}

	public String getWeatherUnits() {
		return weatherUnits;
	}

	public String getAppId() {
		return appId;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getWeatherResource() {
		return weatherResource;
	}

	
	
	@Override
	public String toString() {
		return "OpenWeatherProperties [appId=" + appId + ", baseUrl=" + baseUrl + ", weatherResource=" + weatherResource
				+ ", weatherUnits=" + weatherUnits + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result + ((baseUrl == null) ? 0 : baseUrl.hashCode());
		result = prime * result + ((weatherResource == null) ? 0 : weatherResource.hashCode());
		result = prime * result + ((weatherUnits == null) ? 0 : weatherUnits.hashCode());
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
		OpenWeatherProperties other = (OpenWeatherProperties) obj;
		if (appId == null) {
			if (other.appId != null)
				return false;
		} else if (!appId.equals(other.appId))
			return false;
		if (baseUrl == null) {
			if (other.baseUrl != null)
				return false;
		} else if (!baseUrl.equals(other.baseUrl))
			return false;
		if (weatherResource == null) {
			if (other.weatherResource != null)
				return false;
		} else if (!weatherResource.equals(other.weatherResource))
			return false;
		if (weatherUnits == null) {
			if (other.weatherUnits != null)
				return false;
		} else if (!weatherUnits.equals(other.weatherUnits))
			return false;
		return true;
	}

	
	
}
