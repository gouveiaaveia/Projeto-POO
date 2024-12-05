import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class ProdutoAlimentar extends Produtos implements Serializable{

    private enum TipoTaxa {
        Reduzida,
        Intermedia,
        Normal
    }

    protected TipoTaxa tipoTaxa;
    protected String categotia;
    protected ArrayList<String> certificacoes;


    public ProdutoAlimentar(String nome, String codigo,String descricao, int quantidade, double precoUnitario, String categoria,ArrayList<String> certificacoes){
        super(nome,codigo,descricao,quantidade,precoUnitario);
        this.categotia=categoria;
        this.certificacoes=certificacoes;
        determinarTipoTaxaIVA();
    }


    @Override
    public double valorComIVA(String localizacao){
        if(getCertificacoes().size() == 4){
            return getPrecoUnitario() + (extraCertificacoes(localizacao) * getPrecoUnitario());
        }else if(getCategotia().equalsIgnoreCase("vinho")){
            return getPrecoUnitario() + (extraCategoriaVinho(localizacao) * getPrecoUnitario());
        }

        return getPrecoUnitario() + (obterIVA(localizacao) * getPrecoUnitario());
    }

    @Override
    public double valorTotalSemIVA(){
        return getQuantidade() * getPrecoUnitario();
    }

    @Override
    public double valorTotalComIVA(String localizacao){
        return getQuantidade() * valorComIVA(localizacao);
    }

    @Override
    public double obterIVA(String localizacao) {

        TabelaIVA tabelaBase = new TabelaIVA(0,0,0);
        TabelaIVA tabela = tabelaBase.getTabelaPorLocalizacao(localizacao.toLowerCase(), "alimentar");

        switch (tipoTaxa) {
            case Reduzida:
                return tabela.getTaxaReduzida();
            case Intermedia:
                return tabela.getTaxaIntermedia();
            default:
                return tabela.getTaxaNormal();
        }
    }

    public void determinarTipoTaxaIVA(){
        if(!certificacoes.isEmpty()){
            tipoTaxa = TipoTaxa.Reduzida;
        }else if(getCategotia().equals("congelados") || getCategotia().equals("enlatados") || getCategotia().equals("vinho")){
            tipoTaxa = TipoTaxa.Intermedia;
        }else{
            tipoTaxa = TipoTaxa.Normal;
        }
    }

    protected double extraCategoriaVinho(String localizacao){
        return obterIVA(localizacao) + (double) (1 /100);
    }

    protected double extraCertificacoes(String localizacao){
        return obterIVA(localizacao) - (double) (1/100);
    }

    public String toString(){
        return super.toString();
    }


    public TipoTaxa getTipoTaxa() {
        return tipoTaxa;
    }

    public void setTipoTaxa(TipoTaxa tipoTaxa) {
        this.tipoTaxa = tipoTaxa;
    }

    public String getCategotia() {
        return categotia;
    }

    public void setCategotia(String categotia) {
        this.categotia = categotia;
    }

    public ArrayList<String> getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(ArrayList<String> certificacoes) {
        this.certificacoes = certificacoes;
    }
}