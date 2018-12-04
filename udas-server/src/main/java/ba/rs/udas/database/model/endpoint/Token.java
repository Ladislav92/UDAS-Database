package ba.rs.udas.database.model.endpoint;

import java.util.UUID;

class Token {
	private String token;

	Token() {
		this.token = UUID.randomUUID().toString();
	}

	String getToken() {
		return token;
	}
}
