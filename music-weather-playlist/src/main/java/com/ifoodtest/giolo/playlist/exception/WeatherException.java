package com.ifoodtest.giolo.playlist.exception;

/**
 * Exceção ao tentar obter informações de clima atuais, em alguma região
 * 
 * @author <a href="mailto:m.eduardo5@gmail.com">Mario Eduardo Giolo</a>
 *
 */
public class WeatherException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WeatherException(Throwable cause) {
		super(cause);
	}
	
    public WeatherException() {
        super();
    }

    public WeatherException(String message) {
        super(message);
    }

}
