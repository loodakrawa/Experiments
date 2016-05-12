package loodakrawa.resttest;

import javax.inject.Singleton;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import loodakrawa.resttest.dal.DummyDAO;
import loodakrawa.resttest.dal.SearchDAO;
import loodakrawa.resttest.service.SearchService;

public class GuiceServletConfig extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new JerseyServletModule() {
			@Override
			protected void configureServlets() {
				bind(SearchDAO.class).to(DummyDAO.class);
				
				bind(SearchService.class);
				
				serve("/*").with(GuiceContainer.class);
				// Configuring Jersey via Guice:
				bind(JacksonJsonProvider.class).in(Singleton.class);

			}
		});
	}
}