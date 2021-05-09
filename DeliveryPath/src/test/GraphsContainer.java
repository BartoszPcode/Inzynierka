package test;

import java.util.ArrayList;
import Model.Edge;
import Model.Graph;
import Model.Node;

public class GraphsContainer {
	
	public Graph graph1() {	
		Edge aEdge = new Edge("a", 125);
		Edge bEdge = new Edge("b", 321);
		Edge cEdge = new Edge("c", 54);
		Edge dEdge = new Edge("d", 111);
		Edge eEdge = new Edge("e", 98);

		Node aNode = new Node("A", 0, 0);
		aNode.addEdge(aEdge);
		aNode.addEdge(bEdge);
		aNode.addEdge(dEdge);
		
		Node bNode = new Node("B", 33);
		bNode.addEdge(aEdge);
		bNode.addEdge(eEdge);
		bNode.addEdge(cEdge);
		
		Node cNode = new Node("C", 21);
		cNode.addEdge(bEdge);
		cNode.addEdge(eEdge);
		
		Node dNode = new Node("D", 54);
		dNode.addEdge(dEdge);
		dNode.addEdge(cEdge);

		ArrayList<Node> nodesList = new ArrayList<Node>();
		nodesList.add(aNode);
		nodesList.add(bNode);
		nodesList.add(cNode);
		nodesList.add(dNode);

		ArrayList<Edge> edgesList = new ArrayList<Edge>();
		edgesList.add(aEdge);
		edgesList.add(bEdge);
		edgesList.add(cEdge);
		edgesList.add(dEdge);
		edgesList.add(eEdge);

		Graph graph = new Graph(nodesList, edgesList);
		return graph;
	}

	public Graph graph2() {
		/*
		 * 			(K3)
		 *     		/  \
		 *     	   d    e
		 *     	  /      \
		 *     (K1)--c--(K2)--f--(K4)
		 *     	  \      /
		 *         a    b
		 *          \  /
		 *     		(M)		
		 * 
		 * 
		 */
		
		//Edges
		Edge a = new Edge("a", 5);
		Edge b = new Edge("b", 10);
		Edge c = new Edge("c", 20);
		Edge d = new Edge("d", 15);
		Edge e = new Edge("e", 5);
		Edge f = new Edge("f", 5);
		
		//Nodes:
		// M
		Node M = new Node("M", 0, 0);
		M.addEdge(a);
		M.addEdge(b);
		
		// K1
		Node K1 = new Node("K1", 31);
		K1.addEdge(a);
		K1.addEdge(c);
		K1.addEdge(d);
		
		// K2
		Node K2 = new Node("K2",14);
		K2.addEdge(b);
		K2.addEdge(c);
		K2.addEdge(e);
		K2.addEdge(f);
		
		// K3
		Node K3 = new Node("K3",42);
		K3.addEdge(d);
		K3.addEdge(e);
		
		// K4
		Node K4 = new Node("K4", 11);
		K4.addEdge(f);
		
		// Nodes List
		ArrayList<Node> nodesList = new ArrayList<Node>();
/*0*/	nodesList.add(M);
/*1*/	nodesList.add(K1);
/*2*/	nodesList.add(K2);
/*3*/	nodesList.add(K3);
/*4*/	nodesList.add(K4);

		ArrayList<Edge> edgesList = new ArrayList<Edge>();
		edgesList.add(a);
		edgesList.add(b);
		edgesList.add(c);
		edgesList.add(d);
		edgesList.add(e);
		edgesList.add(f);


		//Graph from nodesList
		Graph graph = new Graph(nodesList, edgesList);
		//graph.createConnections();
		
		return graph;
	}
	
	
	public Graph graphMain5() {
		//Edges
		Edge a = new Edge("a", 365);
		Edge b = new Edge("b", 54);
		Edge c = new Edge("c", 62);
		Edge d = new Edge("d", 357);
		Edge e = new Edge("e", 98);
		
		Edge f = new Edge("f", 389);
		Edge g = new Edge("g", 130);

		Edge k = new Edge("k", 341);
		Edge u = new Edge("u", 333);
		Edge p = new Edge("p", 74);
		
		//Nodes
		// M
		Node M = new Node("M", 0, 0);
		M.addEdge(a);
		M.addEdge(f);
		M.addEdge(d);
		M.addEdge(k);
		M.addEdge(u);
		
		Node K1 = new Node("K1", 23);
		K1.addEdge(a);
		K1.addEdge(e);
		K1.addEdge(b);
		
		Node K2 = new Node("K2", 17);
		K2.addEdge(b);
		K2.addEdge(f);
		K2.addEdge(c);
		
		Node K3 = new Node("K3", 19);
		K3.addEdge(c);
		K3.addEdge(e);
		K3.addEdge(d);
		K3.addEdge(g);
	
		Node K4 = new Node("K4", 17);
		K4.addEdge(g);
		K4.addEdge(k);
		K4.addEdge(p);
		
		Node K5 = new Node("K5", 14);
		K5.addEdge(u);
		K5.addEdge(p);
		
		ArrayList<Node> nodesList = new ArrayList<Node>();
		nodesList.add(M);
		nodesList.add(K1);
		nodesList.add(K2);
		nodesList.add(K3);
		nodesList.add(K4);
		nodesList.add(K5);		
		
		ArrayList<Edge> edgesList = new ArrayList<Edge>();
		edgesList.add(a);
		edgesList.add(b);
		edgesList.add(c);
		edgesList.add(d);
		edgesList.add(e);
		edgesList.add(f);
		edgesList.add(g);
		edgesList.add(k);
		edgesList.add(u);
		edgesList.add(p);
		
		Graph graph = new Graph(nodesList, edgesList);
		graph.createConnections();
		
		return graph;		
	}
	
	public Graph graphMain6() {
		//Edges
		Edge a = new Edge("a", 365);
		Edge b = new Edge("b", 54);
		Edge c = new Edge("c", 62);
		Edge d = new Edge("d", 357);
		Edge e = new Edge("e", 98);
		
		Edge f = new Edge("f", 389);
		Edge g = new Edge("g", 130);
		Edge j = new Edge("j", 345);
		
		Edge k = new Edge("k", 341);
		Edge n = new Edge("n", 50);
		Edge u = new Edge("u", 333);
		Edge p = new Edge("p", 74);
		
		//Nodes
		Node M = new Node("M", 0, 0);
		M.addEdge(a);
		M.addEdge(f);
		M.addEdge(d);
		M.addEdge(k);
		M.addEdge(u);
		M.addEdge(j);
		
		Node K1 = new Node("K1", 23);
		K1.addEdge(a);
		K1.addEdge(e);
		K1.addEdge(b);
		
		Node K2 = new Node("K2", 17);
		K2.addEdge(b);
		K2.addEdge(f);
		K2.addEdge(c);
		
		Node K3 = new Node("K3", 19);
		K3.addEdge(c);
		K3.addEdge(e);
		K3.addEdge(d);
		K3.addEdge(g);
	
		Node K4 = new Node("K4", 17);
		K4.addEdge(g);
		K4.addEdge(k);
		K4.addEdge(p);
		
		Node K5 = new Node("K5", 14);
		K5.addEdge(u);
		K5.addEdge(p);
		K5.addEdge(n);
		
		Node K6 = new Node("K6", 18);
		K6.addEdge(j);
		K6.addEdge(n);
		
		ArrayList<Node> nodesList = new ArrayList<Node>();
		nodesList.add(M);
		nodesList.add(K1);
		nodesList.add(K2);
		nodesList.add(K3);
		nodesList.add(K4);
		nodesList.add(K5);		
		nodesList.add(K6);
		
		ArrayList<Edge> edgesList = new ArrayList<Edge>();
		edgesList.add(a);
		edgesList.add(b);
		edgesList.add(c);
		edgesList.add(d);
		edgesList.add(e);
		edgesList.add(f);
		edgesList.add(g);
		edgesList.add(j);
		edgesList.add(k);
		edgesList.add(n);
		edgesList.add(u);
		edgesList.add(p);
		
		Graph graph = new Graph(nodesList, edgesList);
		graph.createConnections();
		
		return graph;		
	}
	
	public Graph graphMain7() {
		//Edges
		Edge a = new Edge("a", 365);
		Edge b = new Edge("b", 54);
		Edge c = new Edge("c", 62);
		Edge d = new Edge("d", 357);
		Edge e = new Edge("e", 98);
		
		Edge f = new Edge("f", 389);
		Edge g = new Edge("g", 130);
		Edge h = new Edge("h", 93);
		Edge i = new Edge("i", 93);
		Edge j = new Edge("j", 345);
		
		Edge k = new Edge("k", 341);
		Edge m = new Edge("m", 59);
		Edge n = new Edge("n", 50);
		Edge u = new Edge("u", 333);
		Edge p = new Edge("p", 74);
		
		//Nodes
		Node M = new Node("M", 0, 0);
		M.addEdge(a);
		M.addEdge(f);
		M.addEdge(d);
		M.addEdge(k);
		M.addEdge(u);
		M.addEdge(j);
		
		Node K1 = new Node("K1", 23);
		K1.addEdge(a);
		K1.addEdge(e);
		K1.addEdge(b);
		
		Node K2 = new Node("K2", 17);
		K2.addEdge(b);
		K2.addEdge(f);
		K2.addEdge(c);
		
		Node K3 = new Node("K3", 19);
		K3.addEdge(c);
		K3.addEdge(e);
		K3.addEdge(d);
		K3.addEdge(g);
	
		Node K4 = new Node("K4", 17);
		K4.addEdge(g);
		K4.addEdge(k);
		K4.addEdge(p);
		K4.addEdge(h);
		
		Node K5 = new Node("K5", 14);
		K5.addEdge(u);
		K5.addEdge(p);
		K5.addEdge(m);
		K5.addEdge(n);
		
		
		Node K6 = new Node("K6", 18);
		K6.addEdge(j);
		K6.addEdge(n);
		K6.addEdge(i);
		
		Node K7 = new Node("K7", 15);
		K7.addEdge(h);
		K7.addEdge(m);
		K7.addEdge(i);
		
		ArrayList<Node> nodesList = new ArrayList<Node>();
		nodesList.add(M);
		nodesList.add(K1);
		nodesList.add(K2);
		nodesList.add(K3);
		nodesList.add(K4);
		nodesList.add(K5);		
		nodesList.add(K6);
		nodesList.add(K7);
		
		ArrayList<Edge> edgesList = new ArrayList<Edge>();
		edgesList.add(a);
		edgesList.add(b);
		edgesList.add(c);
		edgesList.add(d);
		edgesList.add(e);
		edgesList.add(f);
		edgesList.add(g);
		edgesList.add(h);
		edgesList.add(i);
		edgesList.add(j);
		edgesList.add(k);
		edgesList.add(m);
		edgesList.add(n);
		edgesList.add(u);
		edgesList.add(p);
		
		Graph graph = new Graph(nodesList, edgesList);
		graph.createConnections();
		
		return graph;		
	}
	
}
