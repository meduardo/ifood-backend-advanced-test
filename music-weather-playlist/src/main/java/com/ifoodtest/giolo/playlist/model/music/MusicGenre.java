package com.ifoodtest.giolo.playlist.model.music;

public interface MusicGenre {

	String id();
	
	public enum Genres implements MusicGenre {
		
		//Vamos os basear por hora em um Enum, mas genero pode ser algo mais amplo, por isto a interface
		
		PARTY("party"),
		POP("pop"),
		ROCK("rock"),
		CLASSICAL("classical");
		
		private String id ;

		private Genres(final String id) {
			this.id = id;
		}
		
		@Override
		public String id() {
			return id;
		}
	}
}
