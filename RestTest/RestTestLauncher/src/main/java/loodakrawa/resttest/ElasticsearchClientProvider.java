package loodakrawa.resttest;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.inject.Singleton;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.google.inject.Provider;

import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class ElasticsearchClientProvider implements Provider<Client> {

	public Client get() {
		Client client = null;
		log.info("Creating Elasticsearch Transport Client " + System.identityHashCode(this));
		try {
			client = TransportClient.builder().build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			log.info("Elasticsearch Transport Client created");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		return client;
	}
}
