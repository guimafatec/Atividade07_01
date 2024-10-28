package br.edu.fateczl.atividade07_01.model;

public class ContaEspecial extends ContaBancaria {
    private float limite;

    public ContaEspecial(String cliente, int num_conta, float saldo, float limite) {
        super(cliente, num_conta, saldo);
        this.setLimite(limite);
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public float sacar(float valorSaque) {
        float saldo = this.getSaldo();
        float limite = this.getLimite();
        float total = saldo + limite;
        if (valorSaque > total) {
            throw new IllegalArgumentException("O saque ultrapassou o limite!");
        } else
        if (valorSaque <= 0) {
            throw new IllegalArgumentException("O saque deve ser maior do que zero!");
        }
        saldo -= valorSaque;
        System.out.println("Saldo: " + saldo + "\nLimite: " + limite);
        this.setLimite(limite);
        this.setSaldo(saldo);
        return this.getSaldo();
    }
}
