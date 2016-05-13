package loodakrawa.resttest.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;
import loodakrawa.resttest.dal.SearchDAO;
import loodakrawa.resttest.model.Query;
import loodakrawa.resttest.model.Result;

@Slf4j
@Path("search")
public class SearchService {

	private SearchDAO dao;

	@Inject
	public SearchService(SearchDAO dao) {
		this.dao = dao;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Result process(@QueryParam("query") String searchString) {
		log.info("Searching for: " + searchString);
		Query query = new Query(searchString);
		return dao.search(query);
	}
}
