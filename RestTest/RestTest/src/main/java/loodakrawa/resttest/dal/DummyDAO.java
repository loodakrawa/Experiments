package loodakrawa.resttest.dal;

import java.util.ArrayList;
import java.util.List;

import loodakrawa.resttest.model.Query;
import loodakrawa.resttest.model.Result;

public class DummyDAO implements SearchDAO {

	@Override
	public Result search(Query query) {
		List<String> results = new ArrayList<>();
		results.add("Result for " + query.getTerms());
		return new Result(results);
	}

}
