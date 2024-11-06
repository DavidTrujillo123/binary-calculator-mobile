public class Binary {
    private String binNumA;
    private String binNumB;

    public Binary(String binNumA, String binNumB) {
        this.binNumA = binNumA;
        this.binNumB = binNumB;
    }

    public int binarioADecimal(String binario) {
        int decimal = 0;
        int longitud = binario.length();
        for (int i = 0; i < longitud; i++) {
            if (binario.charAt(i) == '1') {
                decimal += Math.pow(2, longitud - 1 - i);
            }
        }
        return decimal;
    }

    public String decimalABinario(int decimal) {
        if (decimal == 0) return "0";
        String binario = "";
        while (decimal > 0) {
            binario = (decimal % 2) + binario;  // Concatenamos el resultado al inicio de la cadena
            decimal /= 2;
        }
        return binario;
    }
    public String suma() {
        int decimal1 = binarioADecimal(this.binNumA);
        int decimal2 = binarioADecimal(this.binNumB);
        int suma = decimal1 + decimal2;
        return decimalABinario(suma);
    }

    // Resta de binarios
    public String resta() {
        int decimal1 = binarioADecimal(this.binNumA);
        int decimal2 = binarioADecimal(this.binNumB);
        int resta = decimal1 - decimal2;
        return decimalABinario(resta >= 0 ? resta : 0);  // Evita resultados negativos
    }

    // Multiplicaci?n de binarios
    public String multiplicacion() {
        int decimal1 = binarioADecimal(this.binNumA);
        int decimal2 = binarioADecimal(this.binNumB);
        int multiplicacion = decimal1 * decimal2;
        return decimalABinario(multiplicacion);
    }

    // Divisi?n de binarios
    public String division() {
        int decimal1 = binarioADecimal(this.binNumA);
        int decimal2 = binarioADecimal(this.binNumB);
        if (decimal2 == 0) {
            return "ERROR: Divisi?n por cero";
        }
        int division = decimal1 / decimal2;
        return decimalABinario(division);
    }

    @Override
    public String toString() {
        return this.binNumA+" - "+this.binNumB;
    }
}
