package ba.rs.udas.database.model.endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RPCServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.info(req.toString());

		String c = req.getHeader("X-UDAS-class");
		String m = req.getHeader("X-UDAS-method");

		logger.info("X-UDAS-class:" + c );
		logger.info("X-UDAS-method:" + m );

		if (c == null || m == null) {
			logger.error("Unable to get class and method from request");

			resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);

			return;
		}

		try {
			final Class<?> clazz = getClass().getClassLoader().loadClass(c);

			final Method method = clazz.getMethod(m, HttpServletRequest.class, HttpServletResponse.class);

			method.invoke(clazz.getDeclaredConstructor().newInstance(), req, resp);
		} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			logger.error(e);

			resp.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
		}

	}
}


