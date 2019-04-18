package bearmaps.proj2c;

import bearmaps.MyTrieSet;
import bearmaps.NewTrieSet;
import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import edu.princeton.cs.algs4.TrieSET;
import edu.princeton.cs.algs4.TrieST;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    //these two are for closest method
    private List<Point> noPlaces;
    private Map<Point, Node> noPlacesMap;


    private TrieSET namesTrie;
    private Map<String, List<Node>> cleanedNamesMappedToNodes;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        List<Node> nodes = this.getNodes();
        noPlaces = new ArrayList<>();
        noPlacesMap = new HashMap<>();

        namesTrie = new TrieSET();
        cleanedNamesMappedToNodes = new HashMap<>();

        for (Node n : nodes) {

            if (!this.neighbors(n.id()).isEmpty()) {
                Point p = new Point(n.lon(), n.lat());
                noPlaces.add(p);
                noPlacesMap.put(p, n);
            }

            String name = n.name();
            if (name != null) {
                // do all this only if the node has a name
                String cleanedName = cleanString(name);
                namesTrie.add(cleanedName);

                if (cleanedNamesMappedToNodes.containsKey(cleanedName)) {
                    cleanedNamesMappedToNodes.get(cleanedName).add(n);
                } else {
                    List<Node> namesInMap = new LinkedList<>();
                    namesInMap.add(n);
                    cleanedNamesMappedToNodes.put(cleanedName, namesInMap);
                }
            }
        }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        WeirdPointSet verteces = new WeirdPointSet(noPlaces);
        return noPlacesMap.get(verteces.nearest(lon, lat)).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the namesTrie of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full namesTrie of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> locations = new LinkedList<>();
        for (String name : namesTrie.keysWithPrefix(prefix)) {
            for (Node n : cleanedNamesMappedToNodes.get(name)) {
                locations.add(n.name());
            }
        }
        return locations;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        locationName = cleanString(locationName);
        List<Map<String, Object>> locations = new LinkedList<>();
        if (cleanedNamesMappedToNodes.get(locationName) != null) {
            for (Node n : cleanedNamesMappedToNodes.get(locationName)) {
                Map<String, Object> map = new HashMap<>();
                map.put("lat", n.lat());
                map.put("lon", n.lon());
                map.put("name", n.name());
                map.put("id", n.id());
                locations.add(map);
            }
        }
        return locations;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
