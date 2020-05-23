import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class Controller {
	
	
	private Graph grafo;
	private ArrayList<String> ciudades;
	private long[][] matrix;
	private ArrayList<Relacion> relaciones;
	private HashMap<String,String> mapa = new HashMap<String,String>();
	private HashMap<String,Integer> mapa1 = new HashMap<String,Integer>();
	private ArrayList<Alteracion> alteraciones;
	
	public Controller() {
		grafo = new Graph();
		alteraciones = new ArrayList<Alteracion>();
	}
	
	/**
	 * metodo que retorna la lista de las alteraciones que se realizaron
	 * @return
	 */
	public Alteracion[] getAlteraciones() {
		return alteraciones.toArray(new Alteracion[alteraciones.size()]);
	}
	
	/**
	 * metodo para obtener la matriz
	 * @return
	 */
	public String getMatrix() {
		String matris = "";
		
		for (long[] m:Logica.floyd(matrix)) {
			matris += Arrays.toString(m)+"\n";
		}
		
		
		return matris;
	}
	
	/**
	 * metodos para cambiar la alteracion y asi volver a meter la ruta al grafo
	 * @param alteracion
	 */
	public void changeAlteracion(Alteracion alteracion) {
		for (int i = 0;i<alteraciones.size();i++) {
			if (alteraciones.get(i).equals(alteracion)) {
				relaciones.add(alteracion.getRelacion());
				alteraciones.remove(i);
				crearGrafo();
			}
		}
		JOptionPane.showMessageDialog(null, "Alteracion se elimino con exito");
	}
	
	
	/**
	 * Metodo para alterar las rutas del grafo
	 * @param alteracion
	 */
	public void setAlteracion(Alteracion alteracion) {
		for (int i =0;i<relaciones.size();i++) {
			if (relaciones.get(i).equals(alteracion.getRelacion())) {
				alteraciones.add(alteracion);
				relaciones.remove(i);
				crearGrafo();
			}
		}
		JOptionPane.showMessageDialog(null, "Se altero con exito");
	}
	
	/**
	 * Funcion para obtener todas las relaciones posibles entre par de ciudades
	 * @param c1
	 * @param c2
	 * @return
	 */
	public Relacion[] getRelacion(String c1,String c2) {
		ArrayList<Relacion> relacion = new ArrayList<Relacion>();
		
		for (Relacion r: this.relaciones) {
			if ((r.getCiudad1().equals(c1) && r.getCiudad2().equals(c2)) || (r.getCiudad1().equals(c2) && r.getCiudad2().equals(c1)) ) {
				relacion.add(r);
			}
		}
		return relacion.toArray(new Relacion[relacion.size()]);
	}
	
	
	/**
	 * Funcion para obtener las relaciones que se realizaron en el txt
	 */
	public void grafoTXT() {
		relaciones = ArchivoTXT.getRelaciones();
	}
	
	/**
	 * Funcion para obtener la ruta que se tendria que hacer de un nodo a otro
	 * @param p1
	 * @param p2
	 * @return
	 */
	public String[] getRuta(int p1,int p2) {
		String[] resp;
		String[] lugares;
		crearGrafo();
		if (p1<= p2) {
			resp =  Logica.floyd(matrix,p1,p2).split(", ");
			lugares= new String[resp.length];
			for (int i=0;i<resp.length;i++) {
				lugares[i] =  getNombre(resp[i]);
			}
		}else {
			resp =  Logica.floyd(matrix,p2,p1).split(", ");
			lugares= new String[resp.length];
			int pos = 0;
			for (int i=resp.length-1;i>=0;i--) {
				lugares[pos] =  getNombre(resp[i]);
				pos++;
			}
		}
		return lugares;
	}
	
	
	/**
	 * Funcion para agregar una nueva relacion
	 */
	public void addRelation(Relacion relation) {
		relaciones.add(relation);
	}
	
	
	/**
	 * Fucion para obtener un nombre segun un numero(posicion)
	 */
	public String getNombre(String num) {
		return mapa.get(num);
	}
	
	/**
	 * Funcion para generar el grafo en base a las relaciones que se obtuvieron en el txt
	 */
	public void crearGrafo() {
		ArrayList<Node> nodos = new ArrayList<Node>();
		ciudades = new ArrayList<String>();
		
		
		for (Relacion n : relaciones) {
			if (!ciudades.contains(n.getCiudad1())) {
				ciudades.add(n.getCiudad1());
			}
			if (!ciudades.contains(n.getCiudad2())){
				ciudades.add(n.getCiudad2());
			}
		}
		
		
		mapa.clear();
		mapa1.clear();
		for(int i = 0;i<ciudades.size();i++) {
			mapa.put((i+1)+"", ciudades.get(i));
			mapa1.put(ciudades.get(i), i);
		}
		
		
		
		// agregar nodos al arreglo
		for (String ciudad:ciudades) {
			nodos.add(new Node(ciudad));
		}
		
		// se crean las relaciones entre los nodos
		for (Node n : nodos) {
			for (Relacion r: relaciones) {
				if (n.getCity().equals(r.getCiudad1())) {
					n.addEdge(new Edge(getNode(nodos,r.getCiudad2()),r.getDistancia()));
				}
			}
			
			for (Relacion r: relaciones) {
				if (n.getCity().equals(r.getCiudad2())) {
					n.addEdge(new Edge(getNode(nodos,r.getCiudad1()),r.getDistancia()));
				}
			}
			
			grafo.addNode(n);
		}
		crearMatriz();
	}
	
	
	
	/**
	 * Funcion para crear la matriz adyacente
	 */
	public void crearMatriz() {
		matrix = new long[ciudades.size()][ciudades.size()];
		rellenarMatriz();
		
		
	}
	
	/**
	 * Metodo para obtener el centro del grafo
	 * @return
	 */
	public String getCenter() {
		//crearMatriz();
		return mapa.get(Logica.graphCenter(matrix)+"");
	}
	
	
	
	/**
	 * Funcion que rellena la matriz segun las relaciones que se encuentren
	 */
	private void rellenarMatriz() {
		for (int i = 0; i<ciudades.size();i++) {
			String ciudad1 = ciudades.get(i);
			for (int j = 0;j<ciudades.size();j++) {
				String ciudad2 = ciudades.get(j);
				matrix[i][j] = hasRelation(ciudad1, ciudad2);
			}
		}
	}
	
	
	/**
	 * Metodo que retorna las ciudades actuales las cuales se leyeron en el txt
	 * @return
	 */
	public String[] getCiudades() {
		return ciudades.toArray(new String[ciudades.size()]);
	}
	
	
	/**
	 * Funcion que retorna la distancia que hay entre nodos si hay relacion o no
	 * @param ciudad1
	 * @param ciudad2
	 * @return
	 */
	private long hasRelation(String ciudad1, String ciudad2) {
		if(ciudad1.equals(ciudad2)) {
			return 0;
		}
		for (Node n : grafo.getNodes()) {
			for (Edge e : n.getEdges()) {
				if (n.getCity().equals(ciudad1) && e.getDestination().getCity().equals(ciudad2)) {
					return  e.getDistance();
				}
			}
		}
		
		return Long.MAX_VALUE;
	}
	
	/**
	 * Funcion para imprimir la matriz
	 */
	public String matrixPrint() {
		String matris = "";
		for (int i = 0; i<matrix.length;i++) {
			matris += Arrays.toString(matrix[i])+"\n";
		}
		return matris;
	}
	
	
	/**
	 * Funcion que devuelve un nodo segun la ciudad con que tenga relacion
	 * @param nodos
	 * @param nodo
	 * @return
	 */
	private Node getNode(ArrayList<Node> nodos, String nodo) {
		for (Node n: nodos) {
			if (n.getCity().equals(nodo)) {
				return new Node(nodo);
			}
		}
		return null;
	}
	
	/**
	 * Metodo para obtener la distancia en total de todos los nodos
	 * @param lugares
	 * @return
	 */
	public String getDistance(String[] lugares) {
		long distancia = 0;
		int pre = 0;
		int post = 1;
		
		try {
			while(post < lugares.length) {
				for (int i=0;i<relaciones.size();i++) {
					if (relaciones.get(i).getCiudad1().equals(lugares[pre]) && relaciones.get(i).getCiudad2().equals(lugares[post]) || 
							relaciones.get(i).getCiudad1().equals(lugares[post]) && relaciones.get(i).getCiudad2().equals(lugares[pre])) {
						
						distancia += relaciones.get(i).getDistancia();
					}
				}
				pre++;
				post++;
			}
		}catch(Exception e) {
			return "0km";
		}
		
		
		
		
		return distancia+"km";
	}
	
	
	
	
}
