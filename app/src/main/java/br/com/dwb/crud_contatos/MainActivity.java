package br.com.dwb.crud_contatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.dwb.crud_contatos.bdhelper.PessoaDAO;
import br.com.dwb.crud_contatos.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    PessoaDAO bdHelper;
    ArrayList<Pessoa> listview_Pessoas;
    Pessoa pessoas;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastrar = (Button) findViewById(R.id.btn_Cadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, FormularioPessoa.class);
                startActivity(intent);
            }
        });
        lista = (ListView) findViewById(R.id.listview_Pessoas);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Pessoa pessoaEscolhida = (Pessoa) parent.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, FormularioPessoa.class);
                i.putExtra("pessoaescolhida",pessoaEscolhida);
                startActivity(i);
            }
        });



        //outro metodo longclick

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                pessoas = (Pessoa) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Esta Pessoa");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                bdHelper = new PessoaDAO(MainActivity.this);
                bdHelper.deletarPessoa(pessoas);
                bdHelper.close();
                carregarPessoa();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarPessoa();
    }

    public void carregarPessoa(){
        bdHelper = new PessoaDAO(MainActivity.this);
        listview_Pessoas = bdHelper.getLista();
        bdHelper.close();

        if (listview_Pessoas != null){
            adapter = new ArrayAdapter<Pessoa>(MainActivity.this, android.R.layout.simple_list_item_1, listview_Pessoas);
            lista.setAdapter(adapter);
        }

    }

}