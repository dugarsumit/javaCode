package tutorials;

import java.util.ArrayList;
import java.util.List;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;

public class Aerospike {

	private static AerospikeClient client;
	private static List<String> NODES = new ArrayList<String>();

	public static void main(String[] args) {
		initalizeClients(fetchAerospikeNodes());
		put();
		get();
	}

	public static void put() {
		Key key = new Key("myNameSpace", "myCache", "mykey");
		Bin bin1 = new Bin("value", "data");
		Bin bin2 = new Bin("time", "2334");
		client.put(setWritePolicy(), key, bin1, bin2);
	}

	public static void get() {
		Key key = new Key("myNameSpace", "myCache", "mykey");
		Record record = client.get(null, key);
		System.out.println(record.toString());
	}

	/**
	 * Creates connection to aerospike clients.
	 * 
	 * @return
	 */
	public static void initalizeClients(List<String> nodes) {
		Host[] hosts = new Host[nodes.size()];
		int i= 0;
		for(String node : nodes){
			String[] host = node.split(":");
			hosts[i] = new Host(host[0], Integer.parseInt(host[1]));
			i++;
		}
		client = new AerospikeClient(null,hosts);
	}
	
	public static WritePolicy setWritePolicy(){
		WritePolicy writePolicy = new WritePolicy();
		writePolicy.expiration = 200;
		return writePolicy;
	}
	
	public static ClientPolicy setClientPolicy(){
		ClientPolicy clientPolicy = new ClientPolicy();
		return clientPolicy;
	}
	
	public static List<String> fetchAerospikeNodes(){
		String node = "127.0.0.1:3000";
		NODES.add(node);
		return NODES;
	}
}
