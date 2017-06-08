import java.awt.Graphics; 
import java.awt.event.*; 
import java.awt.Color; 
import java.awt.Font; 
import java.applet.Applet; 
import java.awt.*;  
import java.awt.Image; 
import java.util.*; 
 
public class nosotros extends Applet implements Runnable, KeyListener  
{ // al extender heredamos todos los metodos de la clase Applet y al implementar estamos obligados a escribir los metodos de las interfaces Runnable, KeyListener 
	Vector invader_vector=new Vector(); //es el array de invasores 
	Vector shot_vector=new Vector(); //es el array de disparos nuestros disparados 
	ObjetoGrafico nave_espacial= new ObjetoGrafico(150,350,'E'); //nuestra nave 
	int shot=0; //es un contador de nuestros disparos	 
	Thread hilo; //un hilo para que se muevan automáticamente todos los marcianos 
	Image buffer; //son los jpg q importamos 
	Image spaceShip; //las librerías de imágenes seguramente, solo sea el fondo del programa 
	Graphics bufferg; 
	Image shoot; 
	Image alienigena; //todos los jpg 
	public void init() //este es el metodo constructor 
	{ 
		hilo= new Thread(this); //aquí crea el hilo 
		setSize(300,400);	    //esto te dice el tamaño de la pantalla del juego 
		for (int x=0;x<6;x++) //crea el vector de marcianos 
		{ 
			invader_vector.addElement(new ObjetoGrafico((x*40)+40,5,'E')); 
		} 
		buffer=createImage(getSize().width,getSize().height);  
		bufferg=buffer.getGraphics(); 
		spaceShip=getImage(getDocumentBase(),"nave.jpg"); 
		shoot=getImage(getDocumentBase(),"misil2.jpg"); 
		alienigena=getImage(getDocumentBase(),"marciano.jpg"); 
		setBackground(Color.black); //el color de fondo del juego 
		addKeyListener(this); 
		hilo.start(); 
	}	 
 
	public void run()//hace que se mueva el juego  
	{ 
		while (true) //el juego se mueve cada 80 milisegundos 
		{try  
		{ 
			hilo.sleep(80); 
		} 
		catch(Exception e) //esto es para que el programa no se quede tonto cuando falle el try 
		{ 
		} 
		 
//		boolean colision = false; 
// 		for ((int x=0; x<shot_vector.size() && !colision;x++) // si algún disparo toca a algún invasor, el invasor muere y el disparo desaparece
// 		{ 
// 			for (int y=0;y<invader_vector.size() && !colision;y++) 
// 			{ 
// 				if ((((ObjetoGrafico) shot_vector.elementAt(x)).dev_pos_y()) - (((ObjetoGrafico)invader_vector.elementAt(y)).dev_pos_y())<5) 
// 				{ 
// 					if ((((ObjetoGrafico) shot_vector.elementAt(x)).dev_pos_x()) - (((ObjetoGrafico)invader_vector.elementAt(y)).dev_pos_x())<5) 
// 					{ 
// 					((ObjetoGrafico) shot_vector.elementAt(x)).estado_alive(false); 
// 					((ObjetoGrafico) invader_vector.elementAt(y)).estado_alive(false);	 
// 					colision = true; 
// 					} 
// 				} 
// 			} 
// 		} 

		int rango = 5;
		for (int x=0; x<shot_vector.size();x++)
		    {
			for (int y=0; y<invader_vector.size(); y++)
			    {
				if ((Math.abs(((ObjetoGrafico) shot_vector.elementAt(x).dev_pos_y()) - (((ObjetoGrafico) invader_vector.elementAt(y)).dev_pos_y()))) < rango)
				    {
					if ((Math.abst((((ObjetoGrafico) shot_vector.elementAt(x)).dev_pos_x()) - (((ObjetoGrafico)invader_vector.elementAt(y)).dev_pos_x())) < rango)
					    {
				    
					((ObjetoGrafico) shot_vector.elementAt(x)).estado_alive(false); 
 					((ObjetoGrafico) invader_vector.elementAt(y)).estado_alive(false);
					    }
				    }
			    }
		    }

		 
		for (int x=0;x<shot_vector.size();x++) //cuando el disparo está muy arriba, el disparo muere 
		{ 
			if (((ObjetoGrafico) shot_vector.elementAt(x)).dev_pos_y()<5) 
			{ 
				((ObjetoGrafico) shot_vector.elementAt(x)).estado_alive(false); 
			}else 
			{ 
			} 
		} 
		 
		 
		int tamano_vector_shot=shot_vector.size(); 
		for (int x=0;x<tamano_vector_shot;x++) //cuando un disparo muere se elimina del vector de disparos 
		{ 
			if (((ObjetoGrafico)shot_vector.elementAt(x)).dev_estado_alive()==false) 
			{  
				shot_vector.removeElementAt(x); 
				tamano_vector_shot=shot_vector.size(); 
			}else 
			{ 
			} 
		} 
 
		 
		int tamano_vector_invader=invader_vector.size(); 
		for (int cont=0;cont<tamano_vector_invader;cont++) //bucle para eliminar los invader 
		{ 
			if (((ObjetoGrafico)invader_vector.elementAt(cont)).dev_estado_alive()==false) 
			{ 
				invader_vector.removeElementAt(cont); 
				tamano_vector_invader=invader_vector.size(); 
			}else 
			{ 
			} 
		} 
 
				 
		for (int x=0;x<shot_vector.size();x++) //mueve los disparos disparados por nosotros ;) 
		{ 
			((ObjetoGrafico) shot_vector.elementAt(x)).mover(); 
		} 
 
 
		int ultimo_elemento_vector_invader = invader_vector.size(); 
		if ((((ObjetoGrafico)invader_vector.lastElement()).dev_dir_mov()=='E') && (((ObjetoGrafico) invader_vector.lastElement()).dev_pos_x()>270) && (((ObjetoGrafico) invader_vector.lastElement()).dev_pos_y()<350)) 
		{ 
			for (int x=0;x<invader_vector.size();x++) //cuando está a la derecha lo bajas y cambia de dirección 
			{ 
				((ObjetoGrafico)invader_vector.elementAt(x)).cambiar_direccion('S'); 
				((ObjetoGrafico) invader_vector.elementAt(x)).mover(); 
				((ObjetoGrafico)invader_vector.elementAt(x)).cambiar_direccion('O'); 
			} 
		} 
		else 
		{ 
			if ((((ObjetoGrafico) invader_vector.firstElement()).dev_dir_mov()=='O') && (((ObjetoGrafico) invader_vector.firstElement()).dev_pos_x()<5) && (((ObjetoGrafico) invader_vector.firstElement()).dev_pos_y()<350)) 
			{ 
				for(int x=0;x<invader_vector.size();x++) //cuando está a la izda del todo lo baja y cambia de direcc 
				{ 
					((ObjetoGrafico) invader_vector.elementAt(x)).cambiar_direccion('S'); 
					((ObjetoGrafico) invader_vector.elementAt(x)).mover(); 
					((ObjetoGrafico) invader_vector.elementAt(x)).cambiar_direccion('E'); 
				} 
			} 
			else 
			{ 
				if (((ObjetoGrafico) invader_vector.firstElement()).dev_pos_y()>350) 
				{ 
					/*for (int x=0;x<6;x++) // este bucle es para que los invasores vuelvan a salir desde arriba 
					{ 
						((ObjetoGrafico) invader_vector.elementAt(x)).mover_posicion((x*40)+40,5); 
						((ObjetoGrafico) invader_vector.elementAt(x)).cambiar_direccion('E'); 
					}*/ 
				}else 
				{ 
					for (int x=0;x<invader_vector.size(); x++) //por defecto mueve los marcianos a la dirección definida 
					{ 
					((ObjetoGrafico) invader_vector.elementAt(x)).mover(); 
					} 
				} 
			} 
		} 
		repaint(); //para volver a pintar el applet y haya animación 
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
 
 
		for (int x=0;x<invader_vector.size();x++) 
		{ 
			if (((ObjetoGrafico) invader_vector.elementAt(x)).dev_estado_alive() == true) 
			{ 
				bufferg.drawImage(alienigena,((ObjetoGrafico) invader_vector.elementAt(x)).dev_pos_x(), ((ObjetoGrafico) invader_vector.elementAt(x)).dev_pos_y(),this); 
			}else 
			{ 
			} 
		} 
		 
		for (int x=0;x<shot_vector.size();x++) 
		{ 
				if (((ObjetoGrafico) shot_vector.elementAt(x)).dev_estado_alive() == true) 
				{ 
					 bufferg.drawImage(shoot,((ObjetoGrafico) shot_vector.elementAt(x)).dev_pos_x(), ((ObjetoGrafico) shot_vector.elementAt(x)).dev_pos_y(),this); 
				}else 
				{ 
				} 
		} 
		g.drawImage(buffer,0,0,this); //llamada final para pintar la pantalla 
 
	} 
	 
	//estos métodos se redefinen porque se ha implementado KeyListener  
	 
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
		case (KeyEvent.VK_SPACE): //32 
			shot++; 
			shot_vector.addElement(new ObjetoGrafico(nave_espacial.dev_pos_x()+5, nave_espacial.dev_pos_y()-10,'N')); 
			break; 
		case (KeyEvent.VK_O): //79 
			if (nave_espacial.dev_pos_x()>25) 
			{ 
				nave_espacial.cambiar_direccion('O'); 
				nave_espacial.mover(); 
			}else 
			{  
				nave_espacial.mover_posicion(0,350); 
			} 
			break; 
		case (KeyEvent.VK_P): //80 
			if (nave_espacial.dev_pos_x()<275) 
			{ 
				nave_espacial.cambiar_direccion('E'); 
				nave_espacial.mover(); 
			} 
			else 
			{ 
				nave_espacial.mover_posicion(275,350); 
			} 
			break; 
		// default: nave_espacial.mover_posicion(200,200);  
 
		} 
 
	} 
	
} 
 
