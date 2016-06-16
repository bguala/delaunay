import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class MiCanvas extends JPanel implements MouseListener
{
   private Graphics pantalla;
   public ArrayList vertices;
   Applet app;
   
   public Delaunay d;
   public boolean Delaunay = true, Voronoi = true;
   
   int ancho;
   int alto;
   
   private Image image;
      
   public MiCanvas(Applet app)
   {
   	  vertices=new ArrayList();
   	     
      
      addMouseListener(this);
      d = new Delaunay();
      this.app = app;
   }

   public void Ini()
   {
      ancho = 500;
	  alto = 500;
      image = createImage(ancho, alto);
      pantalla = image.getGraphics();
   }
   
	/*
	* 
	* Genera num numeros aleatorios dentro de los limites del canvas
	* 
	*/
	public void generar(int max)
	{
		//borrar();
		
		Vertice p;
		Random a=new Random();
			
		for(int i=0;i<max;i++)
		{
			int x=a.nextInt(497);
			int y=a.nextInt(432);
			p = new Vertice(x,y);
			vertices.add(p);	
			d.insertar(p);
		}
		app.ptos.setText(""+vertices.size());
		app.repaint();
	}
   
   public void paint(Graphics g)
   {
      pantalla.clearRect(0, 0, ancho, alto);
      
      
		int r = 3;
		pantalla.setColor(Color.red);
		for(int i = 6; i < d.aristas.size(); i = i + 2)
		{
		 pantalla.drawOval(((Arista)d.aristas.get(i)).getDestino().getX() - r,-((Arista)d.aristas.get(i)).getDestino().getY() - r, r*2, r*2);
		 pantalla.drawOval(((Arista)d.aristas.get(i)).getGemelo().getDestino().getX() - r,-((Arista)d.aristas.get(i)).getGemelo().getDestino().getY() - r, r*2, r*2);
		}
        
      
      if(Voronoi)
      {//System.out.println("Se ejecuta Voronoi");
	 	//d.drawVoronoi(pantalla, ancho, alto);
		for(int i = 6; i < d.aristas.size(); i = i + 2)
		{
		  pantalla.setColor(Color.blue);
		  if(!(((Arista)d.aristas.get(i)).getDestino().getX() < 0 ||
				 ((Arista)d.aristas.get(i)).getDestino().getX() > ancho ||
				 ((Arista)d.aristas.get(i)).getGemelo().getDestino().getX() < 0 ||
				 ((Arista)d.aristas.get(i)).getGemelo().getDestino().getX() > ancho ||
					  -((Arista)d.aristas.get(i)).getDestino().getY() < 0 ||
					  -((Arista)d.aristas.get(i)).getDestino().getY() > alto ||
					  -((Arista)d.aristas.get(i)).getGemelo().getDestino().getY() < 0 ||
					  -((Arista)d.aristas.get(i)).getGemelo().getDestino().getY() > alto))
		  {
			  Vertice v1 = centro(((Arista)d.aristas.get(i)).getDestino(),
						  ((Arista)d.aristas.get(i)).getGemelo().getDestino(),
						  ((Arista)d.aristas.get(i)).getNext().getDestino());
			  Vertice v2 = centro(((Arista)d.aristas.get(i)).getDestino(),
						  ((Arista)d.aristas.get(i)).getGemelo().getDestino(),
						  ((Arista)d.aristas.get(i)).getGemelo().getNext().getDestino());
			  pantalla.drawLine(v1.getX(), -v1.getY(), v2.getX(), -v2.getY());
		  }
			 }
	 	
      }
      
      if(Delaunay)
      {System.out.println("**********************");
	 	 	pantalla.setColor(Color.black);
	 	 	
	 	 	
			for(int i = 0; i < d.aristas.size(); i++)
			{
			
				//No mostramos las aristas que triangulan con el triangulo general imaginario 
				if(!(((Arista)d.aristas.get(i)).getDestino().getX() < 0 ||
				 ((Arista)d.aristas.get(i)).getDestino().getX() > ancho ||
			 	 ((Arista)d.aristas.get(i)).getGemelo().getDestino().getX() < 0 ||
			 	 ((Arista)d.aristas.get(i)).getGemelo().getDestino().getX() > ancho ||
				 -((Arista)d.aristas.get(i)).getDestino().getY() < 0 ||
				 -((Arista)d.aristas.get(i)).getDestino().getY() > alto ||
				 -((Arista)d.aristas.get(i)).getGemelo().getDestino().getY() < 0 ||
				 -((Arista)d.aristas.get(i)).getGemelo().getDestino().getY() > alto))
				{
			System.out.println("Dibujamos una linea");
				pantalla.drawLine(((Arista)d.aristas.get(i)).getDestino().getX(),
							 -((Arista)d.aristas.get(i)).getDestino().getY(),
							  ((Arista)d.aristas.get(i)).getGemelo().getDestino().getX(),
							 -((Arista)d.aristas.get(i)).getGemelo().getDestino().getY());
				}
				i++; //Nos saltamos las aristas gemelas
			}System.out.println("**********************");
      }
     		
      g.drawImage(image, 0, 0, null);
   }

	//Calcula el centro del triangulo seg�n la f�rmula de los apuntes

	private Vertice centro(Vertice a, Vertice b, Vertice c)
	{
		  //Precalculamos los valores para ganar algo en eficiencia
		  double px = (double)a.getX();
		  double py = -(double)a.getY();
		  double qx = (double)b.getX();
		  double qy = -(double)b.getY();
		  double rx = (double)c.getX();
		  double ry = -(double)c.getY();
		  
		  double a1 = -0.5 * ((px * px) - (qx * qx) + (py * py) - (qy * qy));
		  double b1 = px - qx;
		  double c1 = py - qy;
		  double a2 = -0.5 * ((qx * qx) - (rx * rx) + (qy * qy) - (ry * ry));
		  double b2 = qx - rx;
		  double c2 = qy - ry;
		  double w = (b1 * c2) - (b2 * c1);
		  double x = -((a1 * c2) - (a2 * c1));
		  double y = (a1 * b2) - (a2 * b1);
		  
		  Vertice v = new Vertice((int)(x/w), (int)(y/w));
		  
		  return v;
	}

   public void borrar()
   {
      d = new Delaunay();
      vertices.clear();

      repaint();
   }
   	
   //Al hacer click a�adimos un nuevo vertice al diagrama de voronoi

   public void mousePressed(MouseEvent e)
   {
	 int x = e.getX();
	 int y = e.getY();
	 Vertice v = new Vertice(x, y);
   
   	 //A�adimos vertice a la lista  
	 vertices.add(v);
	 
	 //Insertamos en Delaunay
	 d.insertar(v);
	 
	 //Cambiamos el valor del TextField del applet mostrando el numero de puntos
	 app.ptos.setText(""+vertices.size());
   	 
   	 repaint();
   }

	//Obligatorio por el implements
	public void mouseClicked(MouseEvent e){}
   	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
}

