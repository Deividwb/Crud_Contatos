package br.com.dwb.crud_contatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.dwb.crud_contatos.bdhelper.PessoaDAO;
import br.com.dwb.crud_contatos.model.Pessoa;
import br.com.dwb.crud_contatos.model.PessoaEndereco;

public class MostrarEndereco extends AppCompatActivity {

    EditText editText_Logradouro, editText_Numero, editText_Cep, editText_Bairro, editText_Cidade, editText_Estado;
    Button btn_Endereco;
    PessoaEndereco editarEndereco, pessoaEndereco;
    PessoaDAO bdHelper;
    ArrayList<PessoaEndereco> listview_Endereco;
    ArrayAdapter adapter;
    ListView listaEndereco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_endereco);

        Button btnVisualizar = (Button) findViewById(R.id.btn_Visualizar);
        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MostrarEndereco.this, PrintEndereco.class);//PrintEndereco
                startActivity(intent);
            }
        });
        pessoaEndereco = new PessoaEndereco();
        bdHelper = new PessoaDAO(MostrarEndereco.this);

        editText_Logradouro = (EditText) findViewById(R.id.editText_Logradouro);
        editText_Numero = (EditText) findViewById(R.id.editText_Numero);
        editText_Cep = (EditText) findViewById(R.id.editText_Cep);
        editText_Bairro = (EditText) findViewById(R.id.editText_Bairro);
        editText_Cidade = (EditText) findViewById(R.id.editText_Cidade);
        editText_Estado = (EditText) findViewById(R.id.editText_Estado);

        btn_Endereco = (Button) findViewById(R.id.btn_AtualizarEndereco);//duvida

        if (editarEndereco != null) {
            btn_Endereco.setText("Modificar Endereço");
            editText_Logradouro.setText(editarEndereco.getLogradouro());
            editText_Numero.setText(Integer.parseInt(editarEndereco.getNumero().toString()));
            editText_Cep.setText(editarEndereco.getCep());
            editText_Bairro.setText(editarEndereco.getBairro());
            editText_Cidade.setText(editarEndereco.getCidade());
            editText_Estado.setText(editarEndereco.getEstado());

            pessoaEndereco.setId_pessoa(editarEndereco.getId_pessoa());

        } else {
            btn_Endereco.setText("Cadastrar Novo Endereço");
        }

        btn_Endereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoaEndereco.setLogradouro(editText_Logradouro.getText().toString());
                pessoaEndereco.setNumero(Integer.parseInt(editText_Numero.getText().toString()));
                pessoaEndereco.setCep(editText_Cep.getText().toString());
                pessoaEndereco.setBairro(editText_Bairro.getText().toString());
                pessoaEndereco.setCidade(editText_Cidade.getText().toString());
                pessoaEndereco.setEstado(editText_Estado.getText().toString());

                if (btn_Endereco.getText().toString().equals("Cadastrar Novo Endereço")) {
                    bdHelper.salvarEndereco(pessoaEndereco);
                    bdHelper.close();
                } else {
                    bdHelper.alterarEndereco(pessoaEndereco);
                    bdHelper.close();
                }
            }
        });
    }

}


