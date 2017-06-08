import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;
import java.awt.*;
import java.awt.Image;
import java.util.*;
public class SpaceInvader extends Applet implements Runnable, KeyListener 
{
	Vector invader_vector=new Vector();
	Vector shot_vector=new Vector();
	ObjetoGrafico nave_espacial= new ObjetoGrafico(150,350,'E');
	int shot=0;
	Thread hilo;
	Image buffer;
	Graphics bufferg;
	Image spaceShip;
	Image shoot;
	Image alienigena;
	public void init()
	{
		hilo= new Thread(this);
		setSize(300,600);
		for (int x=0;x<6;x++)
		{
			invader_vector.addElement(new ObjetoGrafico((x*40)+40,5,'E'));
		}
		
		buffer=createImage(getSize().width,getSize().height);
		bufferg=buffer.getGraphics();
		spaceShip=getImage(getDocumentBase(),"nave.jpg");
		shoot=getImage(getDocumentBase(),"misil.jpg");
		alienigena=getImage(getDocumentBase(),"marciano,jpg");
		setBackground(Color.green);
		addKeyListener(this);
		hilo.start();
	}
	
	public void run()
	{
		while (true)
		{try 
		{
			hilo.sleep(80);
		}
		catch(Exception e)
		{
		}
		for (int x=0;x<shot_vector.size();x++)
		{
			if (((ObjetoGrafico) shot_vector.elementAt(x)).dev_pos_y()<15)
			{
				((ObjetoGrafico) shot_vector.elementAt(x)).estado_alive(false);
			}else
			{
			}
		}
		int tamano_vector_shot=shot_vector.size();
		for (int x=0;x<tamano_vector_shot;x++)
		{
			if (((ObjetoGrafico)shot_vector.elementAt(x)).dev_estado_alive()==false)
			{ 
				shot_vector.removeElementAt(x);
				tamano_vector_shot=shot_vector.size();
			}else
			{
			}
		}
		
		for (int x=0;x<shot_vector.size();x++)
		{
			((ObjetoGrafico)shot_vector.elementAt(x)).mover();
		}
		
		if ((((ObjetoGrafico)invader_vector.elementAt(5)).dev_dir_mov()=='E') && (((ObjetoGrafico) invader_vector.elementAt(5)).dev_pos_x()>270) && (((ObjetoGrafico) invader_vector.elementAt(5)).dev_pos_y()<350))
		{
			for (int x=0;x<6;x++)
			{
				((ObjetoGrafico)invader_vector.elementAt(x)).cambiar_direccion('S');
				((ObjetoGrafico) invader_vector.elementAt(x)).mover();
				((ObjetoGrafico)invader_vector.elementAt(x)).cambiar_direccion('O');
			}
		}
		else
		{
			if ((((ObjetoGrafico) invader_vector.elementAt(0)).dev_dir_mov()=='O') && (((ObjetoGrafico) invader_vector.elementAt(0)).dev_pos_x()<5) && (((ObjetoGrafico) invader_vector.elementAt(0)).dev_pos_y()<350))
			{
				for(int x=0;x<6;x++)
				{
					((ObjetoGrafico) invader_vector.elementAt(x)).cambiar_direccion('S');
					((ObjetoGrafico) invader_vector.elementAt(x)).mover();
					((ObjetoGrafico) invader_vector.elementAt(x)).cambiar_direccion('E');
				}
			}
			else
			{
				if (((ObjetoGrafico) invader_vector.elementAt(0)).dev_pos_y()>350)
				{
					for (int x=0;x<6;x++)
					{
						((ObjetoGrafico) invader_vector.elementAt(x)).mover_posicion((x*40)+40,5);
						((ObjetoGrafico) invader_vector.elementAt(x)).cambiar_direccion('E');
					}
				}else
				{
					for (int x=0;x<6; x++)
					{
					((ObjetoGrafico) invader_vector.elementAt(x)).mover();
					}
				}
			}
		}
		repaint();
		}
	}
	
	public void stop()
	{
		if (hilo!=null)
		{
			hilo.stop();
		}
	}
	
	public void paint(Graphics g)
	{
		update(g);
	}
	
	public void update(Graphics g)
	{

		Dimension d=getSize();
		bufferg.setColor(getBackground());
		bufferg.fillRect(0,0,d.width,d.height);
		bufferg.setColor(Color.green);
		bufferg.drawString("Puntuación: ",3,395);
		bufferg.drawString("Niveles: ", 120,395);
		bufferg.drawString("Misiles: "+shot,220,395);
		bufferg.drawImage(spaceShip,nave_espacial.dev_pos_x(),nave_espacial.dev_pos_y(),this);
		for (int x=0;x<6;x++)
		{
			bufferg.drawImage(alienigena,((ObjetoGrafico) invader_vector.elementAt(x)).dev_pos_x(), ((ObjetoGrafico) invader_vector.elementAt(x)).dev_pos_y(),this);
		}
		for (int x=0;x<shot_vector.size();x++)
		{
			bufferg.drawImage(shoot,((ObjetoGrafico) invader_vector.elementAt(x)).dev_pos_x(), ((ObjetoGrafico) invader_vector.elementAt(x)).dev_pos_y(),this);
		}
		g.drawImage(buffer,0,0,this);

	}
	
	public void keyTyped(KeyEvent e) 
	{
	}
	
	public void keyPressed(KeyEvent e)
	{
		movernave(e);
	}
	
	public void keyReleased(KeyEvent e)
	{
	}
	
	protected void movernave(KeyEvent e)
	{
	
		switch (e.getKeyCode()) 
		{
		case (KeyEvent.VK_ENTER): //65
			shot++;
			shot_vector.addElement(new ObjetoGrafico(nave_espacial.dev_pos_x()+10, nave_espacial.dev_pos_y(),'N'));
		case (KeyEvent.VK_LEFT): //32
			if (nave_espacial.dev_pos_x()>25)
			{
				nave_espacial.cambiar_direccion('O');
				nave_espacial.mover();
			}else
			{ 
				nave_espacial.mover_posicion(0,350);
			}
		case (KeyEvent.VK_RIGHT): //83
			if (nave_espacial.dev_pos_x()<275)
			{
				nave_espacial.cambiar_direccion('E');
				nave_espacial.mover();
			}
			else
			{
				nave_espacial.mover_posicion(275,350);
			}
		default: nave_espacial.mover_posicion(200,200); 

		}

	}


}

