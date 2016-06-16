
class Arista
{
	private Vertice dest = null;	//Destino de la arista
	private Arista next = null;	//Siguiente arista de la lista DCEL
	private Arista gemelo = null;	//Arista gemela (Con origen y destino intercambiados

	//Constructores
	
	public Arista(Vertice v,Arista n, Arista t)
	{
		this.dest = v;
		this.next = n;
		this.gemelo = t;
	}

	public Arista(Arista e)
	{
		this.dest = e.dest;
		this.next = e.next;
		this.gemelo = e.gemelo;
	}

	//Metodos set
	public void setDestino(Vertice v)
	{
		this.dest = v;
	}

	public void setNext(Arista e)
	{
		this.next = e;
	}

	public void setGemelo(Arista e)
	{
		this.gemelo = e;
		e.gemelo = this;
	}

	//Metodos get
	public Arista getNext()
	{
		return this.next;
	}

	public Arista getGemelo()
	{
		return this.gemelo;
	}

	public Vertice getDestino()
	{
		return this.dest;
	}

}
