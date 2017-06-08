import java.awt.Graphics;
import java.awt.Color;

class CirculoGrafico extends Circulo implements Dibujable {
    Color color;
    
    public CirculoGrafico(double x, double y, double r, Color unColor) {
	super(x, y, r);
	this.color = unColor;
    }

    public void dibujar(Graphics dw) {
	dw.setColor(color);
	dw.drawOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
    }

    public void setPosicion(double x, double y) {
	; //método vacío q hay q definir para terminar de implemetar el interfaz Dibujable
    }
} // fin de la clase CirculoGrafico
