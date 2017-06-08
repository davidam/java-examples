//package practica0405;
import java.util.*;

public class ListaPiezas {
    private ArrayList list = new ArrayList();
    public void add(Pieza p) { list.add(p); }
    public Pieza get(int index) {
	return (Pieza)list.get(index);
    }
    public Iterator iterator() { return list.iterator(); }
}
