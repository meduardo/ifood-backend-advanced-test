package com.ifoodtest.giolo.playlist.service.music;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.ifoodtest.giolo.playlist.model.music.MusicGenre;
import com.ifoodtest.giolo.playlist.model.music.Playlist;
import com.ifoodtest.giolo.playlist.model.music.Track;
import com.ifoodtest.giolo.playlist.service.MusicService;
import com.ifoodtest.giolo.playlist.service.music.SpotifyResponse.PlaylistItemResponse;
import com.ifoodtest.giolo.playlist.service.music.SpotifyResponse.PlaylistItemsResponse;
import com.ifoodtest.giolo.playlist.service.music.SpotifyResponse.PlaylistResponse;

@Service
public class SpotifyMusicServiceImpl implements MusicService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpotifyMusicServiceImpl.class);

	private final SpotifyProperties props;
	
	private final WebClient webClient;
	
	@Autowired
	private SpotifyAuthenticationService authService;
	
	public SpotifyMusicServiceImpl(final WebClient.Builder clientBuilder, final SpotifyProperties props) {
		this.props = props;
		this.webClient = clientBuilder.baseUrl(
										props.getApiBaseUrl()
									  )
									  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
									  .build();
		
	}
	
	//TODO é possível extrair este processo de GET similar, em uma classe unica
	private <T> T get(final UnaryOperator<UriBuilder> uriBuilder, final Class<T> bodyType){
		/* 
		 * Obs.: A quem esta analisando o código feito:
		 * 
		 * Estou usando o WebClient, que é o novo client do Spring, que passará a ser
		 * o client padrão ao invés do "RestTemplate", usado já há algum tempo.
		 * estamos projetando as apis para serem bloqueantes, e não reativas no primeiro
		 * momento, futuramente sera mais facil reaproveitarmos estas operações caso migremos
		 * para uma stack reativa, com reactor.
		 * 
		 * Apenas alterando os retornos para tipos reativos, como Mono, Flux...
		 * 
		 */
		return webClient.get()
						.uri(uriBuilder.andThen(UriBuilder::build))
						.header(HttpHeaders.AUTHORIZATION, authService.apiAuthorizationHeader())
						.accept(MediaType.APPLICATION_JSON)
						.retrieve()
						.bodyToMono(bodyType)
						.block();
	}
	
	@Override
	public Playlist findBy(MusicGenre genre) {
		LOGGER.info("Looking for {} music.", genre);
		
		PlaylistResponse resp = random(
									findPlaylists(genre)
										.getPlaylists()
										.getItems()
								);
		
		return createPlayList(
					findPlayListItems(resp)
			   );
	}
	
	private PlaylistResponse random(List<PlaylistResponse> playlists) {
		LOGGER.info("Choosing one playlist... ");
		int rand = new Random().nextInt(playlists.size());
		return playlists.get(rand);
	}
	
	private SpotifyResponse findPlaylists(MusicGenre genre) {
		String uri = new StringBuilder()
							.append(props.getBrowseResource())
							.append(props.getCategoriesResource())
							.append("/").append(genre.id())
							.append(props.getPlaylistsResource())
							.toString();
		
		LOGGER.info("Playlist URI Search: {} ", uri);
		
		return get(uriBuilder -> uriBuilder.path(uri)
										   .queryParam("country", props.getCountry()),
				   SpotifyResponse.class);		
	}
	
	private PlaylistItemsResponse findPlayListItems(PlaylistResponse playlistResp) {
		
		String uri = new StringBuilder()
						.append(props.getPlaylistsResource())
						.append("/").append(playlistResp.getId())
						.append(props.getTracksResource())
						.toString();

		LOGGER.info("Tracks URI Search: {} ", uri);
		
		return get(uriBuilder -> uriBuilder.path(uri)
										   .queryParam("fields", "items(track(name,id))") , 
			   	   PlaylistItemsResponse.class);
	}
	
	private Playlist createPlayList(PlaylistItemsResponse items) {
		//TODO poderia se criar uma estrutura de classes MAPPER, para fazer estas conversões
		// seguindo todo um padrão DTO (local) <--> DTO (remote service)... 
		// seria criado um conjunto de DTOs do Spotify, seguindo as estruturas dos objetos presentes aqui:
		// https://developer.spotify.com/documentation/web-api/reference-beta/#objects-index
		LOGGER.info("Creating playlist for: {}", items);
		Set<Track> playlistTracks = items.getItems()
										 .stream()
										 .filter(Objects::nonNull)
										 .map(PlaylistItemResponse::getTrack)
										 .filter(Objects::nonNull)
										 .map(track -> new Track(track.getId(), track.getName()))
										 .collect(Collectors.toSet());
		
		return new Playlist(playlistTracks);	  
	}
	
}
