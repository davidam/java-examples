//package practica0405;
import java.sql.*;
import java.util.*;


public class Gestion {
  /** Attributes */
  public String codProv ;
  public String codPieza;
  public String codProy;
  public int cantidad;

  /**
   * Operation
   *
   * @param codProv
   * @param codPiez
   * @param codProy
   * @param cantidad
   * @param 
   * @return boolean
   */

    public Gestion (String vcodProv, String vcodPieza, String vcodProy, int vcantidad) {
  	this.codProv = vcodProv;
  	this.codPieza = vcodPieza;
  	this.codProy = vcodProy;
	this.cantidad = vcantidad;
    }

    public Gestion (String vcodProv, String vcodPieza, String vcodProy) {
  	this.codProv = vcodProv;
  	this.codPieza = vcodPieza;
  	this.codProy = vcodProy;
	this.cantidad = 0;
    }


    public boolean alta ( String codProv, String codPiez,  codProy, int cantidad,   ){

    }
    /**
     * Operation
     *
     * @param codProv
     * @param codPiez
     * @param codProy
     * @return boolean
     */
    public boolean baja ( String codProv, String codPiez, String codProy ){
    }
    /**
     * Operation
     *
     * @param codProv
     * @param codPiez
     * @param codProy
     * @return boolean
     */
    public boolean existe ( String codProv, String codPiez, String codProy ){
    }
    /**
     * Operation
     *
     * @param codProv
     * @return int
     */
    public int piezasXProv ( String codProv ){
    }
    /**
     * Operation
     *
     * @param codProv
     * @return Pieza
     */
    public Pieza piezas+Sumin ( String codProv ){
    }
    /**
     * Operation
     *
     * @return Proveedor
     */
    public Proveedor proveedor+Sumin (  ){
    }
    /**
     * Operation
     *
     * @param codProv
     * @param codPiez
     * @param codProy
     * @return int
     */
    public int aumentar ( String codProv, String codPiez, String codProy ){
    }
}

