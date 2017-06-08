
public class Circulo extends Geometria {
    static int numCirculos = 0; //al no poner nada solo podrán usar el atributo las clases del paquete
    public static final double PI = 3.14159265;
    protected double x,y,r;
    
    public Circulo(double x, double y, double r) {
	this.x=x;
	this.y=y;
	this.r=r;
	numCirculos++;
    }
    
    public Circulo() {
	this(0.0, 0.0, 1.0);	
    }

    public Circulo(double r) {
	this(0.0, 0.0, r);	
    }

    public Circulo(Circulo c) {
	this(c.x, c.y, c.r);	
    }

    
    public double perimetro() {
	return 2*PI*r;
    }

    public double area() {
	return PI*r*r;
    }

    public Circulo elMayor(Circulo c) {
	if (this.r > c.r) {
	    return this;
	} else {
	    return c;
	}
    }

    public Circulo elMayor(Circulo c, Circulo d) {
	if (c.r > d.r) {
	    return c;
	} else {
	    return d;
	}
    }
	
}
