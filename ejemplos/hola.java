class Hola {
    public static void main (String argumentos[]) {
	System.out.println("hola mundo");
    }
}

class Bucle {
    public static void main (String argumentos[]) {
	int i;
	for (i=1; i<=4; i++) {
	    if (i==3) continue;
	    System.out.println("Iteraci�n: "+i);
	}
    }
}

class Condicional {
    public static void main (String argumentos[]) {
	int cont=1;
	if (cont == 0)
	    System.out.println("he llegado a cero");
	else
	    System.out.println("aun estoy distinto de cero");
	}
}

class MuchoCondicional {
    public static void main (String argumentos[]) {
	int d�a=3;
	switch (d�a) {
	case 1: System.out.println("Lunes");
	    break;
	case 2: System.out.println("Martes");
	    break;
	case 3: System.out.println("Mi�rcoles");
	    break;
	case 4: System.out.println("Jueves");
	    break;
	default:System.out.println("�Finde!");
	}
    }
}


