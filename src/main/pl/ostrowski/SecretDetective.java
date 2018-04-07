package main.pl.ostrowski;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SecretDetective {


    public HashMap<Character, Set<Character>> getGraphStructure(char[][] triplets) {
        HashMap<Character, Set<Character>> graph = new HashMap<>();
            for(int i=0; i<triplets.length; i++) {
                if(!graph.containsKey(triplets[i][0])) {
                    graph.put(triplets[i][0], new HashSet<>());
                }
                if(!graph.containsKey(triplets[i][1])) {
                    graph.put(triplets[i][1], new HashSet<>());
                }
                graph.get(triplets[i][0]).add(triplets[i][1]);
                graph.get(triplets[i][1]).add(triplets[i][2]);
            }
        return graph;
    }


    public Character findLastNode(HashMap<Character, Set<Character>> graph) {
        for(Map.Entry<Character, Set<Character>> e : graph.entrySet()) {
            if(e.getValue().size() == 0) {
                return e.getKey();
            }
            for(Character c : e.getValue()) {
                if(!graph.containsKey(c)) {
                    return c;
                }
            }
        }
        throw new RuntimeException("No end of graph");
    }


    public void removeNodeFromGraph(Character node, HashMap<Character, Set<Character>> graph) {
        for (Map.Entry<Character, Set<Character>> e : graph.entrySet()) {
            if(e.getValue().contains(node)) {
                e.getValue().remove(node);
            }
        }
        graph.remove(node);
    }


    public String recoverSecret(char[][] triplets) {

        StringBuilder sb = new StringBuilder();
        char previous = 0;

        HashMap<Character, Set<Character>> graph = getGraphStructure(triplets);

        while(graph.size() > 0) {
            Character c = findLastNode(graph);
            sb.append(c);
            removeNodeFromGraph(c, graph);
        }

        return sb.reverse().toString();
    }
}
