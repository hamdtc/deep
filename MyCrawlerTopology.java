import java.io.*;
import java.net.*;

public class MyCrawlerTopology {

    public static void main(String[] args) throws Exception {
        ObjectInputFilter.Config config = new ObjectInputFilter.Config();

        // Set the configuration for Stormcrawler
        config.put("stormcrawler.seedlist.file", "/path/to/seed_urls.json");
        config.put("stormcrawler.bolt.parse.emit.images", true);

        TopologyBuilder builder = new TopologyBuilder();

        // Define the spout for reading the URLs from seed_urls.json
        builder.setSpout("spout", new FileSpout());

        // Define the bolt for parsing the HTML data and images
        builder.setBolt("parse", new JSoupParserBolt()).shuffleGrouping("spout");

        // Define the bolt for storing the data in the database
        builder.setBolt("store", new StoreDataBolt()).shuffleGrouping("parse");

        // Create a local cluster and submit the topology
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("my-crawler", config, builder.createTopology());

        // Wait for the topology to finish executing
        Utils.sleep(10000);
        cluster.killTopology("my-crawler");
        cluster.shutdown();
    }
}

