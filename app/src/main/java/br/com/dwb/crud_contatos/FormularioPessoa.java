package br.com.dwb.crud_contatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import br.com.dwb.crud_contatos.bdhelper.PessoaDAO;
import br.com.dwb.crud_contatos.model.Pessoa;

public class FormularioPessoa extends AppCompatActivity {
    EditText editText_Nome, editText_Cpf, editText_Endereço, editText_Telefone;
    Button btn_Poliform;
    Pessoa editarPessoa, pessoa;
    PessoaDAO bdHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pessoa);

        pessoa = new Pessoa();
        bdHelper = new PessoaDAO(FormularioPessoa.this);

        Intent intent = getIntent();
        editarPessoa = (Pessoa) intent.getSerializableExtra("pessoa");

        editText_Nome = (EditText) findViewById(R.id.editText_Nome);
        editText_Cpf = (EditText) findViewById(R.id.editText_Cpf);
        editText_Endereço = (EditText) findViewById(R.id.editText_Endereço);
        editText_Telefone = (EditText) findViewById(R.id.editText_Telefone);

        btn_Poliform = (Button) findViewById(R.id.btn_Poliform);

        if (editarPessoa != null) {
            btn_Poliform.setText("Modificar Pessoa");

            editText_Nome.setText(editarPessoa.getNome());
            editText_Cpf.setText(editarPessoa.getCpf());
            editText_Endereço.setText(editarPessoa.getEnderecos());
            editText_Telefone.setText(editarPessoa.getTelefones());

            pessoa.setId(editarPessoa.getId());

        } else {
            btn_Poliform.setText("Cadastrar Nova Pessoa");
        }

        btn_Poliform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoa.setNome(editText_Nome.getText().toString());
                pessoa.setCpf(editText_Cpf.getText().toString());
                pessoa.setEnderecos(editText_Endereço.getText().toString());
                pessoa.setTelefones(editText_Telefone.getText().toString());

                if (btn_Poliform.getText().toString().equals("Cadastrar Nova Pessoa")) {
                    bdHelper.salvarPessoa(pessoa);
                    bdHelper.close();
                } else {
                    bdHelper.alterarPessoa(pessoa);
                    bdHelper.close();
                }
            }
        });
    }

}