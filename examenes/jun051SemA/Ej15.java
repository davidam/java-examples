
public class Ej15 {
    public static void main (String [] args) throws Exception {
	try {
	    throw new Exception();
	    //    System.out.print(" uno ");
	} catch(Exception e) {
	    System.out.print(" dos ");
	    System.exit(0);
	} finally {
	    System.out.print(" final ");
	    throw new Exception();
	}
    }
}
