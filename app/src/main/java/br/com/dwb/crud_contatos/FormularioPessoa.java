package br.com.dwb.crud_contatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;

import br.com.dwb.crud_contatos.bdhelper.PessoaDAO;
import br.com.dwb.crud_contatos.model.Pessoa;
import br.com.dwb.crud_contatos.model.PessoaEndereco;

public class FormularioPessoa extends AppCompatActivity {
    EditText editText_Nome, editText_Cpf, editText_Endereço, editText_Telefone;
    Button btn_Poliform;
    Pessoa editarPessoa, pessoa;
    PessoaDAO bdHelper;
    //add componetes
    PessoaEndereco pessoaEndereco;
    ListView listaEndereco;
    ArrayList<PessoaEndereco> listview_Endereco;
    ArrayAdapter adapter;
    ArrayList<Pessoa> listview_Pessoas;
    ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pessoa);

        //ativando activity
        Button btnAtualizarEndereco = (Button) findViewById(R.id.btn_Endereco);
        btnAtualizarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEndereco = new Intent(FormularioPessoa.this, MostrarEndereco.class);
                startActivity(intentEndereco);
            }
        });

        pessoa = new Pessoa();
        bdHelper = new PessoaDAO(FormularioPessoa.this);

        Intent intent = getIntent();
        editarPessoa = (Pessoa) intent.getSerializableExtra("pessoa_escolhida");

        editText_Nome = (EditText) findViewById(R.id.editText_Logradouro);
        editText_Cpf = (EditText) findViewById(R.id.editText_Numero);
        editText_Endereço = (EditText) findViewById(R.id.editText_Cep);
        editText_Telefone = (EditText) findViewById(R.id.editText_Telefone);

        btn_Poliform = (Button) findViewById(R.id.btn_AtualizarCadastro);//duvida

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

