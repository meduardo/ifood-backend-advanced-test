package com.ifoodtest.giolo.playlist.model.dto;

import java.util.Set;

import com.ifoodtest.giolo.playlist.model.music.Playlist;
import com.ifoodtest.giolo.playlist.model.music.Track;

/**
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public class PlaylistDTO {

	private Set<Track> tracks;

	private PlaylistDTO(final Set<Track> tracks) {
		this.tracks = tracks;
	}

	public final Set<Track> getTracks() {
		return tracks;
	}

	public static final PlaylistDTO valueOf(final Playlist playlist) {
		return new PlaylistDTO(playlist.tracks());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tracks == null) ? 0 : tracks.hashCode());
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
		PlaylistDTO other = (PlaylistDTO) obj;
		if (tracks == null) {
			if (other.tracks != null)
				return false;
		} else if (!tracks.equals(other.tracks))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlaylistDTO [tracks=" + tracks + "]";
	}

}
