package br.com.dwb.crud_contatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.dwb.crud_contatos.bdhelper.PessoaDAO;
import br.com.dwb.crud_contatos.model.Pessoa;
import br.com.dwb.crud_contatos.model.PessoaEndereco;

public class PrintEndereco extends AppCompatActivity {

    ListView listaEndereco;
    PessoaDAO bdHelper;
    ArrayList<PessoaEndereco> listview_Endereco;
    PessoaEndereco pessoaEndereco;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_endereco);

        Button btnVoltar = (Button) findViewById(R.id.btn_Voltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PrintEndereco.this, MainActivity.class);
                startActivity(intent);
            }
        });
        listaEndereco = (ListView) findViewById(R.id.listview_Endereco);
        registerForContextMenu(listaEndereco);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarEndereco();
    }

    public void carregarEndereco() {
        bdHelper = new PessoaDAO(PrintEndereco.this);
        listview_Endereco = bdHelper.getListaEndereco();
        bdHelper.close();

        if (listview_Endereco != null) {
            adapter = new ArrayAdapter<PessoaEndereco>(PrintEndereco.this, android.R.layout.simple_list_item_1, listview_Endereco);
            listaEndereco.setAdapter(adapter);
        }

    }
}