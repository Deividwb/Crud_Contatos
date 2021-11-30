package br.com.dwb.crud_contatos.bdhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.com.dwb.crud_contatos.model.Pessoa;
import br.com.dwb.crud_contatos.model.PessoaEndereco;

public class PessoaDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "bdpessoa";
    private static final  int VERSION = 1;

    public PessoaDAO(Context context){
        super(context,DATABASE,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String pessoas = "CREATE TABlE pessoa(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome TEXT NOT NULL, cpf TEXT NOT NULL, enderecos TEXT NOT NULL, telefones TEXT NOT NULL);";
        db.execSQL(pessoas);
        //new table
        String pessoaEndereco = "CREATE TABlE pessoaEndereco(id_pessoa INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, logradouro TEXT NOT NULL, numero INTEGER NOT NULL, cep TEXT NOT NULL, bairro TEXT NOT NULL, cidade TEXT NOT NULL, estado TEXT NOT NULL);";
        db.execSQL(pessoaEndereco);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String pessoas = "DROP TABLE IF EXISTS pessoa";
        db.execSQL(pessoas);
        //new table
        String pessoaEndereco = "DROP TABLE IF EXISTS pessoaEndereco";
        db.execSQL(pessoaEndereco);
    }


    public void salvarPessoa(Pessoa pessoa){
        ContentValues values = new ContentValues();

        values.put("nome",pessoa.getNome());
        values.put("cpf",pessoa.getCpf());
        values.put("enderecos",pessoa.getEnderecos());
        values.put("telefones",pessoa.getTelefones());

        getWritableDatabase().insert("pessoa",null,values);
    }

    public void alterarPessoa(Pessoa pessoa){
        ContentValues values = new ContentValues();

        values.put("nome",pessoa.getNome());
        values.put("cpf",pessoa.getCpf());
        values.put("enderecos",pessoa.getEnderecos());
        values.put("telefones",pessoa.getTelefones());

        String[] args = {pessoa.getId().toString()};
        getWritableDatabase().update("pessoa",values,"id=?",args);
    }

    public void deletarPessoa(Pessoa pessoa){
        String[] args = {pessoa.getId().toString()};
        getWritableDatabase().delete("pessoa","id=?",args);

    }

    public ArrayList<Pessoa> getLista(){
        String[] columns = {"id", "nome", "cpf", "enderecos", "telefones"};
        Cursor cursor = getWritableDatabase().query("pessoa",columns,null,null,null,null,null,null);
        ArrayList<Pessoa> pessoa = new ArrayList<Pessoa>();

        while (cursor.moveToNext()){
            Pessoa pessoas = new Pessoa();
            pessoas.setId(cursor.getLong(0));
            pessoas.setNome(cursor.getString(1));
            pessoas.setCpf(cursor.getString(2));
            pessoas.setEnderecos(cursor.getString(3));
            pessoas.setTelefones(cursor.getString(4));

            pessoa.add(pessoas);
        }
        return pessoa;
    }

    public ArrayList<PessoaEndereco> getListaEndereco(){
        String[] columns = {"id_pessoa", "logradouro", "numero", "cep", "bairro","cidade","estado"};
        Cursor cursor = getWritableDatabase().query("pessoaEndereco",columns,null,null,null,null,null,null);
        ArrayList<PessoaEndereco> pessoaEndereco = new ArrayList<PessoaEndereco>();

        while (cursor.moveToNext()){
            PessoaEndereco pessoaEnderecos = new PessoaEndereco();
            pessoaEnderecos.setId_pessoa(cursor.getLong(0));
            pessoaEnderecos.setLogradouro(cursor.getString(1));
            pessoaEnderecos.setNumero(Integer.parseInt(cursor.getString(2)));
            pessoaEnderecos.setCep(cursor.getString(3));
            pessoaEnderecos.setBairro(cursor.getString(4));
            pessoaEnderecos.setCidade(cursor.getString(5));
            pessoaEnderecos.setEstado(cursor.getString(6));

            pessoaEndereco.add(pessoaEnderecos);
        }
        return pessoaEndereco;
    }

    public void alterarEndereco(PessoaEndereco pessoaEndereco){
        ContentValues values = new ContentValues();

        values.put("logradouro",pessoaEndereco.getLogradouro());
        values.put("numero",pessoaEndereco.getNumero());
        values.put("cep",pessoaEndereco.getCep());
        values.put("bairro",pessoaEndereco.getBairro());
        values.put("cidade",pessoaEndereco.getCidade());
        values.put("estado",pessoaEndereco.getEstado());

        String[] args = {pessoaEndereco.getId_pessoa().toString()};
        getWritableDatabase().update("pessoaEndereco",values,"id_pessoa=?",args);
    }
    public void salvarEndereco(PessoaEndereco pessoaEndereco){
        ContentValues values = new ContentValues();

        values.put("logradouro",pessoaEndereco.getLogradouro());
        values.put("numero",pessoaEndereco.getNumero());
        values.put("cep",pessoaEndereco.getCep());
        values.put("bairro",pessoaEndereco.getBairro());
        values.put("cidade",pessoaEndereco.getCidade());
        values.put("estado",pessoaEndereco.getEstado());

        getWritableDatabase().insert("pessoaEndereco",null,values);
    }
}
