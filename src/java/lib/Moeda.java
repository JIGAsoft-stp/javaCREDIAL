/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author projecto1
 */
public class Moeda
{
    private double realValue;
    private int decimalCase;
    private int numPorCasas;
    private char sepInteiroDecimal;
    private char sepCasasCasas;
    
    /**
     * Inicializar a moeda com as caracteristicas default
     * O separador entre as casas inteira é: ponto(.)
     * O separador entre a parate interira e decimal é: virgula(,)
     * A quantidade de casa decimal é: 2 (duas casas decimais)
     * A quantidade de numeros inteiros por casa é 3: (trez casas decimais)
     * Exemplo  1000000 = 1.000.000,00
     */
    public Moeda()
    {
        this.sepInteiroDecimal = ',';
        this.sepCasasCasas = '.';
        this.numPorCasas = 3;
        this.decimalCase = 2;
        this.realValue = 0.0;
    }
    
    
    /**
     * Inicializar a moeda com as caracteristicas default
     * O separador entre as casas inteira é: ponto(.)
     * O separador entre a parate interira e decimal é: virgula(,)
     * A quantidade de casa decimal é: 2 (duas casas decimais)
     * A quantidade de numeros inteiros por casa é 3: (trez casas decimais)
     * Exemplo  realValue é 1000000 = 1.000.000,00
     * @param realValue
     */
    public Moeda(double realValue)
    {
        this();
        this.realValue = realValue;
    }

    /**
     * 
     * @param realValue O valor original em double
     * @param decimalCase O total de casas decimais que deveraao aparecer
     * @param numPorCasas A quantidade de caractere em  dasa casa inteiros
     * @param sepInteiroDecimal O caracter que ira separar a casa decimal
     * @param sepCasasCasas O caracter que ira separar cada casa
     */
    public Moeda(double realValue, int decimalCase, int numPorCasas, char sepInteiroDecimal, char sepCasasCasas)
    {
        this.realValue = realValue;
        this.decimalCase = decimalCase;
        this.numPorCasas = numPorCasas;
        this.sepInteiroDecimal = sepInteiroDecimal;
        this.sepCasasCasas = sepCasasCasas;
    }
    
    
    
    /**
     * 
     * @param formatValues O valor formatado
     * @param decimalCase O total de casas decimais que deveraao aparecer
     * @param numPorCasas A quantidade de caractere em  dasa casa inteiros
     * @param sepInteiroDecimal O caracter que ira separar a casa decimal
     * @param sepCasasCasas O caracter que ira separar cada casa
     */
    public Moeda(String formatValues, int decimalCase, int numPorCasas, char sepInteiroDecimal, char sepCasasCasas)
    {
        this.decimalCase = decimalCase;
        this.numPorCasas = numPorCasas;
        this.sepInteiroDecimal = sepInteiroDecimal;
        this.sepCasasCasas = sepCasasCasas;
       formatValues = formatValues.replace(sepCasasCasas+"", "");
       formatValues = formatValues.replace(sepInteiroDecimal, '.');
       this.realValue = Double.parseDouble(formatValues);
       
    }
    
    
    public double getRealValue() {
        return realValue;
    }

    public void setRealValue(double realValue) {
        this.realValue = realValue;
    }

    public int getDecimalCase() {
        return decimalCase;
    }

    public void setDecimalCase(int decimalCase) {
        this.decimalCase = decimalCase;
    }

    public int getNumPorCasas() {
        return numPorCasas;
    }

    public void setNumPorCasas(int numPorCasas) {
        this.numPorCasas = numPorCasas;
    }

    public char getSepInteiroDecimal() {
        return sepInteiroDecimal;
    }

    public void setSepInteiroDecimal(char sepInteiroDecimal) {
        this.sepInteiroDecimal = sepInteiroDecimal;
    }

    public char getSplitChar() {
        return sepCasasCasas;
    }

    public void setSplitChar(char splitChar) {
        this.sepCasasCasas = splitChar;
    }
    
    
    @Override
    public String toString()
    {
        return this.format();
    }
    
    /**
     * Funcoa para cria a formatacao a parit do valor actual
     * @return 
     */
    public String format()
    {
        String format = "";
        long intPart = (long) this.realValue;
        double doubepart = this.realValue - intPart;
        String sDoublePart = (doubepart+"").substring(2);
        
        int count =1;
        //Formatar a parte inteira
        for (int i =(intPart+"").length()-1; i>=0; i--)
        {
            format = (intPart+"").charAt(i)+format;
            
            if (count == this.numPorCasas && i>0)
            {
                count = 1;
                format = this.sepCasasCasas+format;
            }
            else count++;
        }
        
        if (this.decimalCase>0)
            format=format+this.sepInteiroDecimal;
        //Foramatar a parte decimal
        for (int i =0; i<this.decimalCase; i++)
        {
            if (i< sDoublePart.length())
                format = format + (doubepart+"").charAt(i+2);
            else format = format +'0';
        }
        return format;
    }
    
    /**
     * Utilizar a formatacao defaulte para o valor do parametro
     * @param value O valor que sera formatado
     * @return O valor formatado
     */
    public static String format (double value)
    {
        Moeda m = new Moeda(value);
        return m.format();
    }
    
    
    /**
     * Criara a foarmatacao a parir do valor fornecido definido o numero o que ira 
     * separa as casas entre os numeros inteiros e o que ira separar as casas inteirars das casas decimais
     * @param value O valor que sera formatdo
     * @param separadorCasas O separadores entre as casas
     * @param separadorInteiroDecimal O separador entre a casa inteira e a casa decimal
     * @return O valor formatado nas condecoes fornecidas
     */
    public static String format (double value, char separadorCasas, char separadorInteiroDecimal)
    {
        Moeda m = new Moeda(value, 2, 3, separadorInteiroDecimal, separadorCasas);
        return m.format();
    }
    
    /**
     * Criar a formatacao usando o valor dado definindo:
     * A quantidade dos numeros por casa
     * A quantidade de casas decimais
     * O caracter que separa cada casa
     * O caracetr que separa a parte inteira da parte decimal
     * @param value O valor a ser formatado
     * @param numerosPorCasas A quantidade de numeros em cada casa
     * @param casasDecimais O umeros de casas decimais que sera apresentada na formatacao
     * @param separadorCasas O caracter que ira separas as casas inteiras
     * @param separadorInteiroDecimal O caracter que ira separar as casas inteirar da casa decimal
     * @return 
     */
    public static String format (double value, int numerosPorCasas, int casasDecimais, char separadorCasas, char separadorInteiroDecimal)
    {
        Moeda m = new Moeda(value, casasDecimais, numerosPorCasas, separadorInteiroDecimal, separadorCasas);
        return m.format();
    }
    
}
