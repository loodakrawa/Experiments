package loodakrawa.resttest;

import javax.servlet.ServletContextEvent;

import org.elasticsearch.client.Client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuiceServletConfig extends GuiceServletContextListener {

	private final Injector injector = Guice.createInjector(new RestTestModule());
	
	@Override
	protected Injector getInjector() {
		return injector;
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		
		log.info("Closing Elasticsearch Transport Client");
		Client client = injector.getInstance(Client.class);
		client.close();
	}

	
}