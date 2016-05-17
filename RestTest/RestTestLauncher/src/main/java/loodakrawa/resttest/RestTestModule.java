package loodakrawa.resttest;

import javax.inject.Singleton;

import org.elasticsearch.client.Client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import loodakrawa.resttest.dal.ElasticsearchDAO;
import loodakrawa.resttest.dal.SearchDAO;
import loodakrawa.resttest.service.SearchService;

public class RestTestModule extends JerseyServletModule{

	@Override
	protected void configureServlets() {
		bind(SearchDAO.class).to(ElasticsearchDAO.class);
		bind(Client.class).toProvider(ElasticsearchClientProvider.class).asEagerSingleton();
		bind(ObjectMapper.class).toInstance(createMapper());
		
		bind(SearchService.class);

		serve("/*").with(GuiceContainer.class);

		// Configuring Jersey via Guice:
		bind(JacksonJsonProvider.class).in(Singleton.class);

	}
	
	private ObjectMapper createMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return objectMapper;
	}
	
}
