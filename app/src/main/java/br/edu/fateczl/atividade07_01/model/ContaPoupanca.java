package br.edu.fateczl.atividade07_01.model;

public class ContaPoupanca extends ContaBancaria{
    /*
     *@author: Gustavo Guimarães de Oliveira
     */
    private int dia_rendimento;

    public ContaPoupanca(String cliente, int num_conta, float saldo, int dia_rendimento) {
        super(cliente, num_conta, saldo);
        this.setDia_rendimento(dia_rendimento);
    }

    public int getDia_rendimento() {
        return dia_rendimento;
    }
    public void setDia_rendimento(int dia_rendimento) {
        this.dia_rendimento = dia_rendimento;
    }
    /**
     * Exemplo taxa 1%:
     * <p> conta = new ContaPoupanca(15);
     * <p> conta.calcularNovoSaldo(1);
     */
    public float calcularNovoSaldo(float taxaRend) {
        float novoSaldo = this.getSaldo();
        float taxa = 1 + taxaRend/100;
        novoSaldo = novoSaldo * taxa;
        return novoSaldo;
    }
}
