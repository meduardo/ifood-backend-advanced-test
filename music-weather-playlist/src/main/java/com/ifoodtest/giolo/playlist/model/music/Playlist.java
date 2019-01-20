package com.ifoodtest.giolo.playlist.model.music;

import java.util.Set;

/**
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public class Playlist {
	
	private Set<Track> tracks;

	Playlist() {}
	
	public Playlist(final Set<Track> tracks) {
		this.tracks = tracks;
	}

	public final Set<Track> tracks() {
		return tracks;
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
		Playlist other = (Playlist) obj;
		if (tracks == null) {
			if (other.tracks != null)
				return false;
		} else if (!tracks.equals(other.tracks))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Playlist [tracks=" + tracks + "]";
	}
	
}
