package ba.rs.udas.database.model.endpoint;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login {

	private static final Logger logger = LogManager.getLogger();

	private class LoginRequest {
		private String username;
		private String password;
	}

	@DoNotCheckMe
	public void login(final HttpServletRequest request, final HttpServletResponse response) {
		try {
			LoginRequest obj = parse(request, LoginRequest.class);

			JsonObject ret = new JsonObject();

			if ("admin".equals(obj.username) && "admin".equals(obj.password)) {
				Token token = new Token();

				RPCServlet.tokens.add(token);

				ret.addProperty("token", token.getToken());
				ret.addProperty("success", true);
			} else {
				ret.addProperty("success", false);
			}

			response.setContentType("application/json");
			response.getWriter().write(ret.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private <T> T parse(final HttpServletRequest request, Class<T> clazz) throws IOException {
		return new Gson().fromJson(request.getReader(), clazz);
	}

}
