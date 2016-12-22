/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Export;

import java.util.ArrayList;
import java.util.List;
import modelo.Relatorio;

/**
 *
 * @author ahmedjorge
 */
public class Test {
    public static void main(String[] args) 
    {
        Relatorio r = new Relatorio();
        r.setNome("Ahmed Jorge");
        r.setApelido("Afonso Ferreira");
        r.setNif("10900001");
        r.setValorCredito("10000000");
        r.setLocalTrabalho("Trindade");
        r.export =new RelatorioConverter.DividaPorProduto();
        List<Relatorio> alR  = new ArrayList<>();
        alR.add(r);
        alR.add(r);
        alR.add(r);
        alR.add(r);
        alR.add(r);
        alR.add(r);
// 
    RelatorioConverter.xports((RelatorioConverter.Relatorio) alR.get(0).export, alR, RelatorioConverter.Type.PDF);
    }
    
}
