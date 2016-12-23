/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Export;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.context.RequestContext;
import sessao.SessionUtil;

/**
 *
 * @author ahmedjorge
 */
public class RelatorioConverter {

    public ArrayList<Object[]> list;

    RelatorioConverter(List<modelo.Relatorio> listRel) {
        list = new ArrayList<>();
        list.add(title());
        for (modelo.Relatorio relatorio : listRel) {
            String[] content = new String[]
            {
             /*"Nº Dossier"*/toString(relatorio.getDossier()),
            /*"NIF"*/ toString(relatorio.getNif()),
            /*"Nome"*/ toString(relatorio.getNome())
            + /*"Apelido"*/ " " + toString(relatorio.getApelido()),
            /*"Valor Credito"*/ toString(relatorio.getValorCredito()),
            /*"Local Trabalho"*/ toString(relatorio.getLocalTrabalho()),
            /*"Q. Credtido"*/ toString(relatorio.getValorCredito()),
            /*"Ano Actual"*/ toString(relatorio.getAnoAtual()),
            /*"Ano Passado"*/ toString(relatorio.getAnoPassado()),
            /*"Deferença"*/ toString(relatorio.getDiferenca()),
            /*"Capital Concebido"*/ toString(relatorio.getCapitalConcebido()),
            /*"Montante em divida"*/ toString(relatorio.getMontanteDivida()),
            /*"TAEG"*/ toString(relatorio.getTaeg()),
            /*"Valor Reembolso"*/ toString(relatorio.getReembolso()),
            /*"Nº Doc Real"*/ toString(relatorio.getNumDocReal()),
            /*"Nº Doc Previsto"*/ toString(relatorio.getNumDocPrevisao()),
            /*"Data Real"*/ toString(relatorio.getDataReal()),
            /*"Data Prevista"*/ toString(relatorio.getDataPrevista()),
            /*"Estado"*/ toString(relatorio.getEstado()),
            /*"Credito Solicitado"*/ toString(relatorio.getCreditoSolicitado()),
            /*"Montante Total Credito"*/ toString(relatorio.getTotalCredito()),
            /*"Valor Pago"*/ toString(relatorio.getValorPago()),
            /*"Valor Cheque"*/ toString(relatorio.getValorCheque()),
            /*"Data de Endose"*/ toString(toString(relatorio.getEndossado()))
            };
            list.add(content);
        }

        GenericPDFs.showItem = new MapParam[]{
            MapParam.NIF,
            MapParam.NomeCompleto,
            MapParam.ValorCredito,
            MapParam.LocalTrabalho,};
    }

    private String[] title() {
        String[] title = new String[]{
            "Nº Dossier",
            "NIF",
            "Nome Completo",
            "Valor Credito",
            "Local Trabalho",
            "Q. Credtido",
            "Ano Actual",
            "Ano Passado",
            "Deferença",
            "Capital Concebido",
            "Montante em divida",
            "TAEG",
            "Valor Reembolso",
            "Nº Doc Real",
            "Nº Doc Previsto",
            "Data Real",
            "Data Prevista",
            "Estado",
            "Credito Solicitado",
            "Montante Total Credito",
            "Valor Pago",
            "Valor Cheque",
            "Data de Endose"
        };
        return title;
    }

    private String toString(Object s) {
        return (s != null) ? s.toString() : " ";
    }

    public ArrayList<Object[]> getList() {
        return list;
    }

    public static enum MapParam {
        NUMDossier(0),
        NIF(1),
        NomeCompleto(2),
        ValorCredito(3),
        LocalTrabalho(4),
        QCredtido(5),
        AnoActual(6),
        AnoPassado(7),
        Deferenca(8),
        CapitalConcebido(9),
        MontanteEmDivida(10),
        TAEG(11),
        ValorReembolso(12),
        NUMDocReal(13),
        NUMDocPrevisto(14),
        DataReal(15),
        DataPrevista(16),
        Estado(17),
        CreditoSolicitado(18),
        MontanteTotalCredito(19),
        ValorPago(20),
        ValorCheque(21),
        DataDeEndose(22);

        MapParam(int map) {
            this.map = map;
        }
        public final int map;
    }

    public static interface Relatorio {

        public void export(List<modelo.Relatorio> relList, Type t);
    }

    public static class Cliente implements Relatorio {

        @Override
        public void export(List<modelo.Relatorio> relList, Type t) {
            if (t == Type.PDF) {
                GenericPDFs.showItem = new MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.ValorCredito,
                    RelatorioConverter.MapParam.LocalTrabalho,};
                GenericPDFs.createDoc(SessionUtil.getUserlogado().getNif(), "cliente", "cliente", new RelatorioConverter(relList).getList(), GenericPDFs.OrientacaoPagina.HORIZONTAL, -1);
            } else {
                GenericExcel.showItem = new MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.ValorCredito,
                    RelatorioConverter.MapParam.LocalTrabalho,};
                GenericExcel.createDoc(SessionUtil.getUserlogado().getNif(), "cliente", "cliente", new RelatorioConverter(relList).getList(), -1);
            }
        }

    }

    public static class CreditoObtido implements Relatorio {

        @Override
        public void export(List<modelo.Relatorio> relList, Type t) {

            //Clientes Obtido
            if (Type.PDF == t) {
                GenericPDFs.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.QCredtido,};
                GenericPDFs.createDoc(SessionUtil.getUserlogado().getNif(), "Credito Obtido", "Credito Obtido", new RelatorioConverter(relList).getList(), GenericPDFs.OrientacaoPagina.HORIZONTAL, -1);
            } else {
                GenericExcel.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.QCredtido,};
                GenericExcel.createDoc(SessionUtil.getUserlogado().getNif(), "Credito Obtido", "Credito Obtido", new RelatorioConverter(relList).getList(), -1);
            }

        }

    }

    public static class ClienteHomologo implements Relatorio {

        @Override
        public void export(List<modelo.Relatorio> relList, Type t) {

            //Clientes Homologo
            if (Type.PDF == t) {
                GenericPDFs.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.AnoActual,
                    RelatorioConverter.MapParam.AnoPassado,
                    RelatorioConverter.MapParam.Deferenca,};
                GenericPDFs.createDoc(SessionUtil.getUserlogado().getNif(), "Clientes Homologo", "Clientes Homologo", new RelatorioConverter(relList).getList(), GenericPDFs.OrientacaoPagina.VERTICAL, -1);

            } else {
                GenericExcel.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.AnoActual,
                    RelatorioConverter.MapParam.AnoPassado,
                    RelatorioConverter.MapParam.Deferenca,};
                GenericExcel.createDoc(SessionUtil.getUserlogado().getNif(), "Clientes Homologo", "Clientes Homologo", new RelatorioConverter(relList).getList(), -1);

            }
        }

    }

    public static class CreditoConebido implements Relatorio {

        @Override
        public void export(List<modelo.Relatorio> relList, Type t) {

            //Credito Concebito
            if (Type.PDF == t) {
                GenericPDFs.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NUMDossier,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.Estado,
                    RelatorioConverter.MapParam.CapitalConcebido,
                    RelatorioConverter.MapParam.MontanteEmDivida,
                    RelatorioConverter.MapParam.TAEG,};
                GenericPDFs.createDoc(SessionUtil.getUserlogado().getNif(), "Credito Concebito", "Credito Concebito", new RelatorioConverter(relList).getList(), GenericPDFs.OrientacaoPagina.HORIZONTAL, -1);

            }
            else {
                 GenericExcel.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NUMDossier,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.Estado,
                    RelatorioConverter.MapParam.CapitalConcebido,
                    RelatorioConverter.MapParam.MontanteEmDivida,
                    RelatorioConverter.MapParam.TAEG,};
                GenericExcel.createDoc(SessionUtil.getUserlogado().getNif(), "Credito Concebito", "Credito Concebito", new RelatorioConverter(relList).getList(), -1);

            }
        }

    }
   
    public static class Cobranca implements Relatorio {

        @Override
        public void export(List<modelo.Relatorio> relList, Type t) {

            //Credito Concebito
            if (Type.PDF == t) {
                GenericPDFs.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.ValorReembolso,
                    RelatorioConverter.MapParam.NUMDocReal,
                    RelatorioConverter.MapParam.NUMDocPrevisto,
                    RelatorioConverter.MapParam.DataReal,
                    RelatorioConverter.MapParam.DataPrevista
                };
                GenericPDFs.createDoc(SessionUtil.getUserlogado().getNif(), "Cobrança", "Cobrança", new RelatorioConverter(relList).getList(), GenericPDFs.OrientacaoPagina.HORIZONTAL, -1);
            }
            else
            {
                GenericExcel.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.ValorReembolso,
                    RelatorioConverter.MapParam.NUMDocReal,
                    RelatorioConverter.MapParam.NUMDocPrevisto,
                    RelatorioConverter.MapParam.DataReal,
                    RelatorioConverter.MapParam.DataPrevista
                };
                GenericExcel.createDoc(SessionUtil.getUserlogado().getNif(), "Cobrança", "Cobrança", new RelatorioConverter(relList).getList(), -1);

            }
        }

    }

    public static class CapitalTAEG implements Relatorio {

        @Override
        public void export(List<modelo.Relatorio> relList, Type t) {
            if (t == Type.PDF) {
                GenericPDFs.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.ValorCredito,
                    RelatorioConverter.MapParam.MontanteEmDivida,
                    RelatorioConverter.MapParam.NUMDossier,
                    RelatorioConverter.MapParam.TAEG,
                    RelatorioConverter.MapParam.Estado}
                        ;
                GenericPDFs.createDoc(SessionUtil.getUserlogado().getNif(), "Divida por Produto", "Divida por Produto", new RelatorioConverter(relList).getList(), GenericPDFs.OrientacaoPagina.HORIZONTAL, -1);
            } else {
                GenericExcel.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.ValorCredito,
                    RelatorioConverter.MapParam.MontanteEmDivida,
                    RelatorioConverter.MapParam.NUMDossier,
                    RelatorioConverter.MapParam.TAEG,
                    RelatorioConverter.MapParam.Estado
                };
                GenericExcel.createDoc(SessionUtil.getUserlogado().getNif(), "Credito Concebito", "Credito Concebito", new RelatorioConverter(relList).getList(), -1);

            }
        }
    }

    public static class DividaPorProduto implements Relatorio {

        @Override
        public void export(List<modelo.Relatorio> relList, Type t) {

            //Divida por Produto
            if (Type.PDF == t) {
                GenericPDFs.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.CreditoSolicitado,
                    RelatorioConverter.MapParam.MontanteTotalCredito,
                    RelatorioConverter.MapParam.ValorPago,};
                GenericPDFs.createDoc(SessionUtil.getUserlogado().getNif(), "Divida por Produto", "Divida por Produto", new RelatorioConverter(relList).getList(), GenericPDFs.OrientacaoPagina.HORIZONTAL, -1);

            } else {
                GenericExcel.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.CreditoSolicitado,
                    RelatorioConverter.MapParam.MontanteTotalCredito,
                    RelatorioConverter.MapParam.ValorPago,};
                GenericExcel.createDoc(SessionUtil.getUserlogado().getNif(), "Divida por Produto", "Divida por Produto", new RelatorioConverter(relList).getList(), -1);

            }
        }

    }

    public static class Cheque implements Relatorio {

        @Override
        public void export(List<modelo.Relatorio> relList, Type t) {
            //Creque
            if (Type.PDF == t) {
                GenericPDFs.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.ValorCheque,
                    RelatorioConverter.MapParam.DataDeEndose,};
                GenericPDFs.createDoc(SessionUtil.getUserlogado().getNif(), "Creque", "Creque", new RelatorioConverter(relList).getList(), GenericPDFs.OrientacaoPagina.HORIZONTAL, -1);
            } else {
                GenericExcel.showItem = new RelatorioConverter.MapParam[]{
                    RelatorioConverter.MapParam.NIF,
                    RelatorioConverter.MapParam.NomeCompleto,
                    RelatorioConverter.MapParam.ValorCheque,
                    RelatorioConverter.MapParam.DataDeEndose,};
                GenericExcel.createDoc(SessionUtil.getUserlogado().getNif(), "Creque", "Creque", new RelatorioConverter(relList).getList(), -1);
            }
        }

    }

    public enum Type {
        EXCEL, PDF
    }
    /**
     * 
     * @param r
     * @param relList 
     * @param t 
     */
    public static void xports(Relatorio r, List<modelo.Relatorio> relList, Type t){
        if(relList.size() > 0)
            r.export(relList, t);
        else{
            RequestContext.getCurrentInstance().execute("$('.modalImage').hide();");
        }
    }
}
