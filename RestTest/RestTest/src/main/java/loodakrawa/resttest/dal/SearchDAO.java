package loodakrawa.resttest.dal;

import loodakrawa.resttest.model.Query;
import loodakrawa.resttest.model.Result;

public interface SearchDAO {
	Result search(Query query);
}
