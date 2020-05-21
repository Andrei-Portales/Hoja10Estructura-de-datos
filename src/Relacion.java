
public class Relacion {
	
	private String ciudad1;
	private String ciudad2;
	private long distancia;
	
	public Relacion(String c1,String c2, long d) {
		this.ciudad1 = c1;
		this.ciudad2 = c2;
		this.distancia = d;
	}

	public String getCiudad1() {
		return ciudad1;
	}

	public void setCiudad1(String ciudad1) {
		this.ciudad1 = ciudad1;
	}

	public String getCiudad2() {
		return ciudad2;
	}

	public void setCiudad2(String ciudad2) {
		this.ciudad2 = ciudad2;
	}

	public long getDistancia() {
		return distancia;
	}

	public void setDistancia(long distancia) {
		this.distancia = distancia;
	}
	
	
	public boolean equals(Relacion r) {
		return (ciudad1.equals(r.ciudad1) && ciudad2.equals(r.ciudad2) && (distancia == r.distancia)) ||(ciudad1.equals(r.ciudad2) && ciudad2.equals(r.ciudad1) && (distancia == r.distancia));
	}
	
	
	
	

}
