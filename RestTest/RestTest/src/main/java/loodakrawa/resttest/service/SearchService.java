package loodakrawa.resttest.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import loodakrawa.resttest.dal.SearchDAO;
import loodakrawa.resttest.model.Query;
import loodakrawa.resttest.model.Result;

@Path("search")
public class SearchService {

	@Inject
	private SearchDAO dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Result process(@QueryParam("query") String searchString) {
		Query query = new Query(searchString);
		return dao.search(query);
	}
}
