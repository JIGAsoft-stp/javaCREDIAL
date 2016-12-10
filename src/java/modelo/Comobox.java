/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexao.Call;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author AhmedJorge
 */
public class Comobox implements  Serializable{
    private String id;
    private String value;
    private String descricao;
    private String toString;

    public Comobox(String armnet)
    {
        String [] campos = armnet.split(";");
        this.id = campos[0];
        this.value = campos[1];
    }
    public String getId() {
        return id;
    }
    
    public Comobox(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public Comobox() {
    }

    public Comobox(String id, String value, String descricao) {
        this.id = id;
        this.value = value;
        this.descricao = descricao;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getToString() {
        return getId()+";"+getValue()+";"+getDescricao();
    }

    public void setToString(String toString) {
        this.toString = toString;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String toString(){
        return ((this.id == null || this.id.isEmpty()) ? null : this.id);
    }
    @Override
    public boolean equals(Object obj)
    {
        if(obj != null && obj instanceof Comobox)
        {
            Comobox cb = (Comobox) obj;
            return cb.id.equals(this.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.value);
        return hash;
    }
    
    
    /**
     * Funcao para criar uma lista de ComoBox
     * Os dados para a lista vem de uma qualquer view de base de dados
     * @param dbViewName O nome da view que sera usada para buscar a como
     * @param viewId as identificacoes para combo
     * @param viewDesc as descrinco na view que serao utilizadas para a comoBox
     * @return 
     */
    public static ArrayList<Comobox> loadCombo (String dbViewName, String viewId, String viewDesc)
    {
        //FUNCAL_COLLETAR_COMBOS -- Essa funcao esta na base de dados para
            //coletar os registro de uma tabela ou veiew num arary e retirnara esses array
        
        ArrayList<String> list = (ArrayList<String>) Call.callSampleFunction("FUNCAL_COLLETAR_COMBOS", Call.ARRAY, dbViewName, viewId, viewDesc);
        ArrayList<Comobox> listaCombo = new ArrayList<>();
        
        if(list==null)
            list=new ArrayList<>();
        list.stream().forEach((valuesCombo) -> {
            listaCombo.add(new Comobox(valuesCombo));
        });
        return  listaCombo;
    }
    
}
