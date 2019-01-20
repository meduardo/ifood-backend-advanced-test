package com.ifoodtest.giolo.playlist.service.music;

import java.util.List;

/**
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public class SpotifyResponse {

	private PlaylistsResponse playlists;

	public PlaylistsResponse getPlaylists() {
		return playlists;
	}

	public void setPlaylists(PlaylistsResponse playlists) {
		this.playlists = playlists;
	}

	
	@Override
	public String toString() {
		return "SpotifyPlaylistByGenreResponse [playlists=" + playlists + "]";
	}

	public static class PlaylistsResponse {
		
		private List<PlaylistResponse> items;
		
		public List<PlaylistResponse> getItems() {
			return items;
		}

		public void setItems(List<PlaylistResponse> items) {
			this.items = items;
		}

		@Override
		public String toString() {
			return "PlaylistsResponse [items=" + items + "]";
		}
		
	}
	
	public static class PlaylistResponse {
		
		private String id;
		private String name;
		private String href;
		
		public String getHref() {
			return href;
		}
		public void setHref(String href) {
			this.href = href;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return "PlaylistResponse [id=" + id + ", name=" + name + ", href=" + href + "]";
		}
		
	}

	public static class PlaylistItemsResponse {
		
		private List<PlaylistItemResponse> items;

		public List<PlaylistItemResponse> getItems() {
			return items;
		}

		public void setItems(List<PlaylistItemResponse> items) {
			this.items = items;
		}

		@Override
		public String toString() {
			return "PlaylistItemsResponse [items=" + items + "]";
		}
		
	}
	
	public static class PlaylistItemResponse {
		
		private TrackResponse track;

		public TrackResponse getTrack() {
			return track;
		}

		public void setTrack(TrackResponse track) {
			this.track = track;
		}

		@Override
		public String toString() {
			return "PlaylistItemResponse [track=" + track + "]";
		}
		
	}
	
	public static class TrackResponse {
		
		private String id;
		
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "TrackResponse [name=" + name + ", id=" + id + "]";
		}
		
	}
	
	public static class SpotifyToken {
		
		//TODO Por conveniencia vamos deixar o atributo com "_" mesmo
		// mas pode ser configurado a forma de fazer parse no Json, 
		// para evitar o uso de "_" que vai contra as convençẽos de nome do Java (CamelCase)
		private String access_token;
		private String token_type;
		private int expires_in;
		
		public String getAccess_token() {
			return access_token;
		}
		
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}
		
		public String getToken_type() {
			return token_type;
		}
		
		public void setToken_type(String token_type) {
			this.token_type = token_type;
		}
		
		public int getExpires_in() {
			return expires_in;
		}
		
		public void setExpires_in(int expires_in) {
			this.expires_in = expires_in;
		}

		@Override
		public String toString() {
			return "SpotifyToken [access_token=" + access_token + ", token_type=" + token_type + ", expires_in="
					+ expires_in + "]";
		}
		
	}
}
