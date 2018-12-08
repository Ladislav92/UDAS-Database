package ba.rs.udas.database.model.endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

class Token {
	private static final Logger logger = LogManager.getLogger();
	private String token;

	Token() {
		this.token = UUID.randomUUID().toString();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			byte[] buff = md.digest(this.token.getBytes(StandardCharsets.UTF_8));

			this.token = Base64.getEncoder().encodeToString(buff);
		} catch (NoSuchAlgorithmException e) {
			logger.warn("Unable to use SHA-256 to generate token");
		}
	}

	String getToken() {
		return token;
	}
}
