public class ProdutoAlimentarBiologico extends ProdutoAlimentar{

    private final boolean biologico;

    public ProdutoAlimentarBiologico(){
        super();
        this.biologico = true;
    }


    @Override
    public double valorComIVA(String localizacao){
        return getPrecoUnitario() + (aplicarDescontoBiologico(localizacao) * getPrecoUnitario());
    }

    private double aplicarDescontoBiologico(String localizacao){
        return obterIVA(localizacao) + (double) (10 /100);
    }

    public boolean isBiologico() {
        return biologico;
    }
}
