import java.awt.Graphics;
import java.awt.Color;

class RectanguloGrafico extends Rectangulo implements Dibujable {
    Color color;
    
    public RectanguloGrafico(double x1, double y1, double x2, double y2, Color unColor) {
	super(x1, y1, x2, y2);
	this.color = unColor;
    }

//     public Rectangulo() {
// 	super();
// 	this.color = green;
//     }


    public void dibujar(Graphics dw) {
	dw.setColor(color);
	dw.drawRect((int)x1, (int)y1, (int)(x2-x1), (int)(y2-y1));
    }

    public void setPosicion(double x, double y) {
	; //método vacío q hay q definir para terminar de implemetar el interfaz Dibujable
    }
}
