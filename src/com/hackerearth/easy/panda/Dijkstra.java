package com.hackerearth.easy.panda;

import java.io.*;
import java.util.*;
 
public class Dijkstra {
   private static final Graph.Edge[] GRAPH = {
      new Graph.Edge("0", "1", 8),
      new Graph.Edge("0", "2", 2),
      new Graph.Edge("1", "3", 5),
      new Graph.Edge("2", "3", 3),
      new Graph.Edge("1", "2", 6)
   };
   
   private static final String START = "0";
   private static final String END = "3";
 
   public static void main(String[] args) {
      Graph g = new Graph(GRAPH);
      int [] ios = {1, 0, 2, 3};
      g.dijkstra(START, ios, 5);
      int dist = g.printPath(END);
      if(dist == Integer.MAX_VALUE){
    	  System.out.println(-1);
      } else{
    	  System.out.println(dist);
      }
      //g.printAllPaths();
   }
}
 
class Graph {
   private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges
 
   /** One edge of the graph (only used by Graph constructor) */
   public static class Edge {
      public final String v1, v2;
      public final int dist;
      public Edge(String v1, String v2, int dist) {
         this.v1 = v1;
         this.v2 = v2;
         this.dist = dist;
      }
   }
 
   /** One vertex of the graph, complete with mappings to neighbouring vertices */
   public static class Vertex implements Comparable<Vertex> {
      public final String name;
      public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
      public Vertex previous = null;
      public final Map<Vertex, Integer> neighbours = new HashMap<>();
 
      public Vertex(String name) {
         this.name = name;
      }
 
      private void printPath() {
         if (this == this.previous) {
            System.out.printf("%s", this.name);
         } else if (this.previous == null) {
            System.out.printf("%s(unreached)", this.name);
         } else {
            this.previous.printPath();
            System.out.printf(" -> %s(%d)", this.name, this.dist);
         }
      }
 
      public int compareTo(Vertex other) {
         return Integer.compare(dist, other.dist);
      }
   }
 
   /** Builds a graph from a set of edges */
   public Graph(Edge[] edges) {
      graph = new HashMap<>(edges.length);
 
      //one pass to find all vertices
      for (Edge e : edges) {
         if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
         if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
      }
 
      //another pass to set neighbouring vertices
      for (Edge e : edges) {
         graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
         //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
      }
   }
 
   /** Runs dijkstra using a specified source vertex */ 
   public void dijkstra(String startName, int [] ios, int p) {
      if (!graph.containsKey(startName)) {
         System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
         return;
      }
      final Vertex source = graph.get(startName);
      NavigableSet<Vertex> q = new TreeSet<>();
 
      // set-up vertices
      for (Vertex v : graph.values()) {
         v.previous = v == source ? source : null;
         v.dist = v == source ? 0 : Integer.MAX_VALUE;
         q.add(v);
      }
 
      dijkstra(q, ios, p);
   }
 
   /** Implementation of dijkstra's algorithm using a binary heap. */
   private void dijkstra(final NavigableSet<Vertex> q, final int [] ios, int p) {      
      Vertex u, v;
      while (!q.isEmpty()) {
 
         u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
         if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable
 
         //look at distances to each neighbour
         for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
            v = a.getKey(); //the neighbour in this iteration
 
            final int alternateDist = u.dist + a.getValue();
            if (ios[Integer.parseInt(v.name)]!=alternateDist%p && alternateDist < v.dist) { // shorter path to neighbour found
               q.remove(v);
               v.dist = alternateDist;
               v.previous = u;
               q.add(v);
            } 
         }
      }
   }
 
   /** Prints a path from the source to the specified vertex */
   public int printPath(String endName) {
      return graph.get(endName).dist;      
   }
   /** Prints the path from the source to every vertex (output order is not guaranteed) */
   public void printAllPaths() {
      for (Vertex v : graph.values()) {
         v.printPath();
         System.out.println();
      }
   }
}
