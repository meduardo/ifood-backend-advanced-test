package com.ifoodtest.giolo.playlist.service;

import com.ifoodtest.giolo.playlist.model.music.MusicGenre;
import com.ifoodtest.giolo.playlist.model.music.Playlist;

/**
 * Operações assoaicadas a música, como enconrar uma playlist de um determinado genêro
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public interface MusicService {

	//Poderiamos expor os metodos aqui, deixando-os publicos, para que seja possivel fazer cache
	// das operações desejadas.
	Playlist findBy(MusicGenre genre);
}
