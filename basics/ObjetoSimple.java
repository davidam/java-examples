class ObjetoSimpleCreado {
    String variable = "Una variable";
    int entero = 14;
    public String obtenerString() {
	return variable;
    }
}

class ObjetoSimple {
    public static void main(String argumentos[]) {
	ObjetoSimpleCreado varObj = new ObjetoSimpleCreado();
	System.out.println(varObj.obtenerString());
    }
}
