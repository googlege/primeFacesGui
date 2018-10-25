package de.homedev.primefaces.frontend.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.faces.FactoryFinder;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.homedev.primefaces.api.fassade.IPersonFassade;
import de.homedev.primefaces.api.model.PersonEntity;
import de.homedev.primefaces.api.util.ApiUtil;
import de.homedev.primefaces.frontend.util.RmiClientUtil;

@WebServlet("/all")
public class TestServletAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestServletAll() {

		System.out.println();
	}

	private FacesContext getFacesContext(final ServletRequest request, final ServletResponse response) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null) {
			return facesContext;
		}

		FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
		LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
		Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

		ServletContext servletContext = ((HttpServletRequest) request).getSession().getServletContext();
		facesContext = contextFactory.getFacesContext(servletContext, request, response, lifecycle);
		// FacesInitializer.setFacesContextAsCurrentInstance(facesContext);

		return facesContext;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		final Writer writer = response.getWriter();
		writer.append("<html>");
		writer.append("<body>");
		try {
			IPersonFassade fassade = RmiClientUtil.getPersonFassade(1099, "localhost");

			List<PersonEntity> list = fassade.findAll();
			if (!list.isEmpty()) {
				for (PersonEntity person : list) {
					writer.append("contakt: <br/>").append(person.toString()).append("<br/>");
				}
			} else {
				writer.append("Liste ist lehr");
			}
		} catch (NamingException e) {
			e.printStackTrace();
			writer.append(ApiUtil.stackTrace2String(e));
		}

		writer.append("</body>");
		writer.append("</html>");
	}

	// "abcab" -> "c"
	// "abab" -> ""
	// "abcdab" -> "c"

	public char carRec(String t) {
		if (t == null) {
			throw new RuntimeException("t is NULL");
		}
		char[] values = t.toCharArray();
		for (int i = 0; i < values.length; i++) {
			char ch = values[i];
			int j = 0;
			for (char ch1 : values) {
				if (i == j) {
					continue;
				}
				if (ch1 == ch) {
					return ch;
				}
				j++;
			}
		}
		System.out.println((byte) ' ');
		return ' ';
	}

}
