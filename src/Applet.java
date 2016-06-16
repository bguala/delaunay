/*
Héctor García Puigcerver
2004

Kirai.NET
http://kirai.bitacoras.com

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Applet extends JApplet implements ActionListener, ItemListener
{
   public MiCanvas canvas;
   private Button reset,generar,recalcular;
   private Checkbox Delaunay, Voronoi;
   public  Label numPuntos,pun,res;
   public JTextField campoTexto,resultado,ptos;
   

   int ANCHO = 500;
   int ALTO = 500;

   public void init()
   {
	  getContentPane().setLayout(new BorderLayout());
	  this.resize(ANCHO,ALTO);
     
      setBackground(Color.white);
      canvas = new MiCanvas(this);
        
      canvas.setBackground(Color.white);
    
      reset = new Button("Reset");
      reset.addActionListener(this);
      
	  generar = new Button("Generar");
	  generar.addActionListener(this);
	  recalcular = new Button("Recalcular");
	  recalcular.addActionListener(this);
      
      Delaunay = new Checkbox("Delaunay");
      
      Delaunay.setState(true);
      Delaunay.addItemListener(this);
      Voronoi = new Checkbox("Voronoi");
      
	  Voronoi.setState(true);
      Voronoi.addItemListener(this);
      numPuntos = new Label("  0 puntos  ");
      
      pun=new Label(" puntos");
	  res=new Label(" segundos");
	  
      
      campoTexto=new JTextField(5);
      campoTexto.setText("500");
      resultado=new JTextField(8);
      ptos=new JTextField(5);
      //NORTE
	  JPanel norte = new JPanel();
	
      norte.add(Delaunay);
	  norte.add(Voronoi);
	  norte.add(reset);
	 // norte.add(recalcular);
	  norte.add(generar);
	  norte.add(campoTexto);
	  norte.add(pun);
		
	  JPanel sur= new JPanel();
	  //sur.add(numPuntos);
	  sur.add(ptos);
	  sur.add(pun);
	  sur.add(resultado);	
	  sur.add(res);
	  	  
	  //Posiciona todos los componentes
	  getContentPane().add("North",norte);
	  getContentPane().add("Center",canvas);
	  getContentPane().add("South",sur);
	  
	  canvas.Ini();
   }

	//Manejadores de eventos

   public void actionPerformed(ActionEvent e)
   {
	 String arg = e.getActionCommand();
      
	 if(arg.equals("Reset"))
	 {
		  canvas.borrar();
      	  numPuntos.setText(" 0 puntos  ");
	 }
	 if(arg.equals("Recalcular"))
	 {
	 
	 }
	 if(arg.equals("Generar"))
	 {
		//canvas.borrar();
		//numPuntos.setText(" 0 puntos  ");
		double ini=System.currentTimeMillis();
		if(!campoTexto.getText().equals(""))
			canvas.generar(Integer.parseInt(campoTexto.getText()));

		resultado.setText((System.currentTimeMillis()-ini)/1000+"");

	 }
   }

   public void itemStateChanged(ItemEvent e)
   {
      String a = e.getItem().toString();
      
      if(a.equals("Delaunay"))
      {
		canvas.Delaunay = !canvas.Delaunay;
		repaint();
	 	
	  }
      if(a.equals("Voronoi"))
      {
		canvas.Voronoi= !canvas.Voronoi;
		repaint();
	  }
   }
}
