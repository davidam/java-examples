class Hola {
    public static void main (String argumentos[]) {
	System.out.println("hello world");
    }
}

class Bucle {
    public static void main (String argumentos[]) {
	int i;
	for (i=1; i<=4; i++) {
	    if (i==3) continue;
	    System.out.println("Iteration: "+i);
	}
    }
}

class Conditional {
    public static void main (String argumentos[]) {
	int cont=1;
	if (cont == 0)
	    System.out.println("zero!");
	else
	    System.out.println("different to zero");
	}
}

class VeryConditional {
    public static void main (String argumentos[]) {
	int day=3;
	switch (day) {
	case 1: System.out.println("Monday");
	    break;
	case 2: System.out.println("Tuesday");
	    break;
	case 3: System.out.println("Wednesday");
	    break;
	case 4: System.out.println("Thursday");
	    break;
	default:System.out.println("Weekend!");
	}
    }
}



