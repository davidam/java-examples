public class ObjetoGrafico
{
	boolean alive;
	int pos_x;
	int pos_y; 
	char dir_mov;

	public ObjetoGrafico(int pos_x, int pos_y, char dir_mov)
	{
		mover_posicion(pos_x,pos_y);
		estado_alive(true);
		cambiar_direccion(dir_mov);
	}
	
	public void mover_posicion(int pos_x, int pos_y)
	{
		this.pos_x=pos_x;
		this.pos_y=pos_y;
	}
	
	public void estado_alive(boolean alive)
	{
		this.alive=alive;
	}
	
	public boolean dev_estado_alive()
	{
		return alive;
	}

	public int dev_pos_x()
	{
		return pos_x;
	}

	public int dev_pos_y()
	{
		return pos_y;
	}

	public char dev_dir_mov()
	{
		return dir_mov;
	}
	
	public void cambiar_direccion(char div_mov)
	{
		this.dir_mov=dir_mov;
	}
	
	public void mover()
	{
		switch (dir_mov)
		{
			case 'E':
				mover_este();
				break;
			case 'O':
				mover_oeste();
				break;
			case 'S':
				mover_sur();
				break;
			case 'N':
				mover_norte();
				break;

		}
	}
	
	void mover_este()
	{
		pos_x=pos_x+10;
	}
	
	void mover_oeste()
	{
		pos_x=pos_x-10;
	}
	
	void mover_sur()
	{
		pos_y=pos_y+12;
	}
	
	void mover_norte()
	{
		pos_x=pos_x-10;
	}
}