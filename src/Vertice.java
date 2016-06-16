
class Vertice
{
   public int x;
   public int y;

	//Constructores
   public Vertice(int xVal, int yVal)
   {
      this.x = xVal;
      this.y = yVal;
   }

   public Vertice(Vertice v)
   {
      this.x = v.x;
      this.y = v.y;
   }

	//Metodos get
	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return -this.y;
	}

	//Metodos set
   public void setX(int xVal)
   {
      this.x = xVal;
   }

   public void setY(int yVal)
   {
      this.y = yVal;
   }

   public void setPosicion(int xVal, int yVal)
   {
      x = xVal;
      y = yVal;
   }


 
}
