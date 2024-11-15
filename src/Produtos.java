import java.util.Random;
import java.util.Scanner;

public abstract class Produtos{
    protected int codigo;
    protected String nome;
    protected String descricao;
    protected int quantidade;
    protected double precoUnitario;

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public Produtos(){
       this.codigo = 0;
       this.nome = "";
       this.descricao = "";
       this.quantidade = 0;
       this.precoUnitario = 0;
    }

    public void criarProduto(){
        setCodigo(rand.nextInt(10000));
        System.out.print("Nome do produto: ");
        setNome(sc.nextLine());
        System.out.print("Descrição do produto: ");
        setDescricao(sc.nextLine());
        System.out.print("Quantidade do produto: ");
        setQuantidade(sc.nextInt());
        System.out.print("Preço unitário do produto: ");
        setPrecoUnitario(sc.nextDouble());
    }

    public abstract double valorTotalComIVA(String localizacao);
    public abstract double valorTotalSemIVA();
    public abstract double obterIVA(String localizacao);
    public abstract double valorComIVA(String localizacao);
    public abstract void determinarTipoTaxaIVA();

    public String toString(){
        return "";
    };


    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}