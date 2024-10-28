package br.edu.fateczl.atividade07_01;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.atividade07_01.model.ContaBancaria;
import br.edu.fateczl.atividade07_01.model.ContaEspecial;
import br.edu.fateczl.atividade07_01.model.ContaPoupanca;

public class MainActivity extends AppCompatActivity {
    /*
     *@author: Gustavo Guimarães de Oliveira
     */
    private RadioGroup rgTipoConta;
    private RadioButton rbPoupanca;
    private RadioButton rbEspecial;
    private EditText etCliente;
    private EditText etNumConta;
    private EditText etSaldo;
    private EditText etLimite;
    private EditText etDiaRendimento;
    private EditText etTaxa;
    private TextView tvInfoConta;
    private EditText etValorSaque;
    private EditText etValorDeposito;
    private Button btnCriarConta;
    private Button btnSacar;
    private Button btnDepositar;
    private TextView tvResultado;
    private ContaBancaria conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgTipoConta = findViewById(R.id.rg_tipoConta);
        rbPoupanca = findViewById(R.id.rb_Poupanca);
        rbPoupanca.setChecked(true);
        rbEspecial = findViewById(R.id.rb_Especial);
        rbEspecial.setChecked(false);
        etCliente = findViewById(R.id.et_Cliente);
        etNumConta = findViewById(R.id.et_NumConta);
        etSaldo = findViewById(R.id.et_Saldo);
        etLimite = findViewById(R.id.et_Limite);
        etDiaRendimento = findViewById(R.id.et_DiaRendimento);
        etTaxa = findViewById(R.id.et_Taxa);
        tvInfoConta = findViewById(R.id.tv_InfoConta);
        etValorSaque = findViewById(R.id.et_ValorSaque);
        etValorDeposito = findViewById(R.id.et_ValorDeposito);
        btnCriarConta = findViewById(R.id.btn_CriarConta);
        btnSacar = findViewById(R.id.btn_Sacar);
        btnDepositar = findViewById(R.id.btn_Depositar);
        tvResultado = findViewById(R.id.tv_Res);
        checarTipoSelecionado();

        rgTipoConta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checarTipoSelecionado();
            }
        });

        btnCriarConta.setOnClickListener(op -> criarConta());
        btnSacar.setOnClickListener(op -> sacar());
        btnDepositar.setOnClickListener(op -> depositar());
    }

    private void depositar() {
        tvResultado.setText("");
        try {
            if (conta == null) {
                throw new IllegalStateException("Crie uma conta primeiro!");
            }
            float valorDeposito = Float.parseFloat(etValorDeposito.getText().toString());
            conta.depositar(valorDeposito);
            tvResultado.setText("Deposito de " + valorDeposito + " realizado!");
            tvInfoConta.setText("Saldo: " + conta.getSaldo());
        } catch (NumberFormatException e) {
            tvResultado.setText("Preencha o valor do depósito!");
        } catch (IllegalStateException | IllegalArgumentException exc) {
            tvResultado.setText(exc.getMessage());
        }
    }

    private void sacar() {
        tvResultado.setText("");
        try {
            if (conta == null) {
                throw new IllegalStateException("Crie uma conta primeiro!");
            }
            float valorSaque = Float.parseFloat(etValorSaque.getText().toString());
            float saldoAtual = conta.sacar(valorSaque);
            tvResultado.setText("Saque de " + valorSaque + " realizado!");
            tvInfoConta.setText("Saldo: " + conta.getSaldo());
        } catch (NumberFormatException e) {
            tvResultado.setText("Preencha o valor do saque!");
        } catch (IllegalStateException | IllegalArgumentException exc) {
            tvResultado.setText(exc.getMessage());
        }
    }

    private void criarConta() {
        try {
            String infoConta = "";
            tvInfoConta.setText(infoConta);
            String nomeCliente = etCliente.getText().toString();
            int numConta = Integer.parseInt(etNumConta.getText().toString());
            float saldo = Float.parseFloat(etSaldo.getText().toString());
            if (rbPoupanca.isChecked()) {
                int diaRendimento = Integer.parseInt(etDiaRendimento.getText().toString());
                float taxa = Float.parseFloat(etTaxa.getText().toString());
                ContaPoupanca contaP = new ContaPoupanca(nomeCliente, numConta, saldo, diaRendimento);
                float novoSaldo = contaP.calcularNovoSaldo(taxa);
                infoConta = "Saldo: " + contaP.getSaldo() + "\nSaldo (Rendimento): " + novoSaldo;
                tvInfoConta.setText(infoConta);
                conta = (ContaBancaria) contaP;
            } else if (rbEspecial.isChecked()) {
                float limite = Float.parseFloat(etLimite.getText().toString());
                ContaEspecial contaE = new ContaEspecial(nomeCliente, numConta, saldo, limite);
                infoConta = "Saldo: " + saldo + "\nLimite: " + limite;
                tvInfoConta.setText(infoConta);
                conta = (ContaBancaria) contaE;
            }
            System.out.println("Saldo: " + conta.getSaldo() + "\nNome: " + conta.getCliente());
        } catch (NumberFormatException e) {
            tvInfoConta.setText("Preencha todos os campos");
        }
    }

    private void checarTipoSelecionado() {
        if (rbPoupanca.isChecked()) {
            etLimite.setEnabled(false);
            etLimite.setText("");
            etDiaRendimento.setEnabled(true);
            etTaxa.setEnabled(true);
        } else if (rbEspecial.isChecked()) {
            etLimite.setEnabled(true);
            etDiaRendimento.setEnabled(false);
            etDiaRendimento.setText("");
            etTaxa.setEnabled(false);
            etTaxa.setText("");
        }
    }
}