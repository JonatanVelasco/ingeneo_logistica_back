package com.ingeneo.logistica.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityMixin {

	/**
	 * Se crea este clase MIX para poder hacer el parseo de los Authorities a el objeto de respuesta JSON
	 * @param role
	 */
	@JsonCreator
	public SimpleGrantedAuthorityMixin(@JsonProperty("authority") String role) {}

}
