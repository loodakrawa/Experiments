package loodakrawa.resttest.dal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.databind.ObjectMapper;

import loodakrawa.resttest.model.Item;
import loodakrawa.resttest.model.Query;
import loodakrawa.resttest.model.Result;

public class ElasticsearchDAO implements SearchDAO {

	private final Client client;
	private final ObjectMapper objectMapper;
	
	@Inject
	public ElasticsearchDAO(Client client, ObjectMapper objectMapper) {
		this.client = client;
		this.objectMapper = objectMapper;
	}
	
	@Override
	public Result search(Query query) {
		BoolQueryBuilder bqb = new BoolQueryBuilder();
		bqb.should(QueryBuilders.matchQuery("name", query.getTerms()));
		bqb.should(QueryBuilders.matchQuery("description", query.getTerms()));
		
		SearchResponse response = client.prepareSearch("items")
				.setTypes("item")
				.setQuery(bqb)
				.execute()
				.actionGet();
		
		List<Item> matches = new ArrayList<>();
		
		for(SearchHit hit : response.getHits().getHits()){
			try {
				Item item = objectMapper.readValue(hit.getSourceRef().toBytes(), Item.class);
				matches.add(item);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new Result(matches);
	}

}
