//package practica0405;
import java.util.*;

public class ListaProveedores {
    private ArrayList list = new ArrayList();
    public void add(Proveedor p) { list.add(p); }
    public Proveedor get(int index) {
	return (Proveedor)list.get(index);
    }
    public Iterator iterator() { return list.iterator(); }
}
