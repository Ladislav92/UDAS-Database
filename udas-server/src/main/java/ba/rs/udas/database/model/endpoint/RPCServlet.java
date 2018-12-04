package ba.rs.udas.database.model.endpoint;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RPCServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger();

	private static final String RPC_CLASS = "X-UDAS-class";
	private static final String RPC_METHOD = "X-UDAS-method";
	private static final String RPC_TOKEN = "X-UDAS-token";
	private final JsonObject responseTemplate = new JsonObject();

	static final List<Token> tokens = new ArrayList<>();

	{
		responseTemplate.addProperty("status", false);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) {

		String c = request.getHeader(RPC_CLASS);
		String m = request.getHeader(RPC_METHOD);

		if (c == null || m == null) {
			logger.error("Unable to get class and method from request");

			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);

			return;
		}

		try {
			final Class<?> clazz = getClass().getClassLoader().loadClass(c);

			final Method method = clazz.getMethod(m, HttpServletRequest.class, HttpServletResponse.class);

			if (!method.isAnnotationPresent(DoNotCheckMe.class) && !this.isLoggedIn(request)) {
				JsonObject responseObj = new JsonObject();

				responseObj.addProperty("success", false);
				responseObj.addProperty("msg", "You are not logged in!");

				response.setContentType("application/json");
				response.getWriter().write(responseObj.toString());

				return;
			}

			method.invoke(clazz.getDeclaredConstructor().newInstance(), request, response);
		} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | IOException e) {
			logger.error(e);

			response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		}

	}

	private boolean isLoggedIn(final HttpServletRequest request) {
		Optional<String> token = Optional.ofNullable(request.getHeader(RPC_TOKEN));

		if (token.isEmpty())
			return false;

		return tokens.stream().anyMatch(item -> token.get().equals(item.getToken()));
	}
}


