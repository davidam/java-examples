public class Ej16 {
    public static void main(String[] args) {
	int[] a1 = {1, 2, 3, 4, 5};
	int[] a2 = {0, 0, 0, 0, 0};
	a2 = a1;
	for (int i=0; i < a2.length; i++)
	    ++a2[i];
	for (int i=0; i < a1.length; i++)
	    {
		System.out.print( "a1[" + i + "] = ");
		System.out.println(a1[i]);
	    }
	try { System.in.read();
	}
	catch(Exception e){}
    }
}
