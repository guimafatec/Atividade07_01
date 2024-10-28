package br.edu.fateczl.atividade07_01.model;

public abstract class ContaBancaria {
    /*
     *@author: Gustavo Guimarães de Oliveira
     */
    private String cliente;
    private int num_conta;
    private float saldo;

    public ContaBancaria(String cliente, int num_conta, float saldo) {
        super();
        this.setCliente(cliente);
        this.setNum_conta(num_conta);
        this.setSaldo(saldo);
    }
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(int num_conta) {
        this.num_conta = num_conta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float sacar(float valorSaque) {
        float saldoAtual = this.getSaldo();
        if (saldoAtual < valorSaque) {
            throw new IllegalArgumentException("Valor do saque está maior do que o saldo!");
        }
        saldoAtual -= valorSaque;
        this.setSaldo(saldoAtual);
        return this.getSaldo();
    }

    public void depositar(float valorDeposito) {
        float saldoAtual = this.getSaldo();
        if (valorDeposito <= 0) {
            throw new IllegalArgumentException("Valor do depósito deve ser maior que zero");
        }
        saldoAtual += valorDeposito;
        this.setSaldo(saldoAtual);
    }
}