package com.ifoodtest.giolo.playlist.service.music;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "spotify", ignoreUnknownFields = true)
public class SpotifyProperties {
	
	//TODO é possível criar bjetos, considerando as configurações deo properties
	// por conveniencia amos manter tudo String mesmo
	
	@NotEmpty(message = "Spotify clientId is required")
	private String clientId;
	
	@NotEmpty(message = "Spotify secretKey is required")
	private String secretKey;
	
	@NotEmpty(message = "Spotify apiBaseUrl is required")
	private String apiBaseUrl;
	
	@NotEmpty(message = "Spotify browseResource is required")
	private String browseResource;
	
	@NotEmpty(message = "Spotify categoriesResource is required")
	private String categoriesResource;
	
	@NotEmpty(message = "Spotify playlistsResource is required")
	private String playlistsResource;
	
	@NotEmpty(message = "Spotify country is required")
	private String country;
	
	@NotEmpty(message = "Spotify tracksResource is required")
	private String tracksResource;
	
	@NotEmpty(message = "Spotify accountBaseUrl is required")
	private String accountBaseUrl;

	@NotEmpty(message = "Spotify tokenResource is required")
	private String tokenResource;
	
	public String getAccountBaseUrl() {
		return accountBaseUrl;
	}

	public void setAccountBaseUrl(String accountBaseUrl) {
		this.accountBaseUrl = accountBaseUrl;
	}

	public String getTokenResource() {
		return tokenResource;
	}

	public void setTokenResource(String tokenResource) {
		this.tokenResource = tokenResource;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	public void setApiBaseUrl(String baseUrl) {
		this.apiBaseUrl = baseUrl;
	}

	public String getBrowseResource() {
		return browseResource;
	}

	public void setBrowseResource(String browseResource) {
		this.browseResource = browseResource;
	}

	public String getCategoriesResource() {
		return categoriesResource;
	}

	public void setCategoriesResource(String categoriesResource) {
		this.categoriesResource = categoriesResource;
	}

	public String getPlaylistsResource() {
		return playlistsResource;
	}

	public void setPlaylistsResource(String playlistsResource) {
		this.playlistsResource = playlistsResource;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTracksResource() {
		return tracksResource;
	}

	public void setTracksResource(String tracksResource) {
		this.tracksResource = tracksResource;
	}
	
}
