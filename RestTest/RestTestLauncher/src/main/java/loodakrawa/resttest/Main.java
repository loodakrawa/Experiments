package loodakrawa.resttest;

import java.io.IOException;

import org.elasticsearch.client.Client;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.inject.Injector;

import lombok.extern.slf4j.Slf4j;
import loodakrawa.resttest.dal.SearchDAO;
import loodakrawa.resttest.model.Query;
import loodakrawa.resttest.model.Result;

@Slf4j
public class Main {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Injector injector = new GuiceServletConfig().getInjector();
		
		SearchDAO dao = injector.getInstance(SearchDAO.class);
		Result result = dao.search(new Query(null));
		
		log.info(result.toString());
		
		Client client = injector.getInstance(Client.class);
		client.close();
	}
}
