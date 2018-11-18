package de.homedev.primefaces.frontend.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.homedev.primefaces.api.model.PersonEntity;
import de.homedev.primefaces.frontend.bean.SimplePersonBean;

@WebServlet("/curent")
public class TestServletCurent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestServletCurent() {
		System.out.println();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		final Writer writer = response.getWriter();
		writer.append("<html>");
		writer.append("<body>");
		HttpSession session = request.getSession(false);
		if (session != null) {
			SimplePersonBean simplePersonBean = (SimplePersonBean) session.getAttribute("simplePersonBean");
			if (simplePersonBean != null) {
				PersonEntity person = simplePersonBean.getPersonDto();
				writer.append("aktueller contakt: <br/>").append(person.toString()).append("<br/>");
			} else {
				writer.append("simplePersonBean is NULL");
			}
		} else {
			writer.append("session is NULL");
		}

		writer.append("</body>");
		writer.append("</html>");
	}

}
