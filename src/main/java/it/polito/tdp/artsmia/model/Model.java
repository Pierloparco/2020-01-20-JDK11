package it.polito.tdp.artsmia.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private ArtsmiaDAO dao;
	private List<Adiacenza> adiacenze;
	
	public Model() {
		dao = new ArtsmiaDAO();
	}
	
	public void creaGrafo(String ruolo) {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, dao.getArtisti(ruolo));
		
		adiacenze = dao.getArchi(ruolo);
		for(Adiacenza a : dao.getArchi(ruolo)) {
			
			if(this.grafo.getEdge(a.getArt1(), a.getArt2())==null) {
				Graphs.addEdge(this.grafo, a.getArt1(), a.getArt2(), a.getPeso());
			}
		}
		
	}

	
	public List<String> getRuoli() {
		return dao.listofRuoli();
	}

	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<Adiacenza> getAdiacenze() {
		return this.adiacenze;
	}

}
