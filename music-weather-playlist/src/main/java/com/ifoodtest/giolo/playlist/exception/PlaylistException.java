package com.ifoodtest.giolo.playlist.exception;

/**
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public class PlaylistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlaylistException(Throwable cause) {
		super(cause);
	}
	
    public PlaylistException() {
        super();
    }

    public PlaylistException(String message) {
        super(message);
    }

}
