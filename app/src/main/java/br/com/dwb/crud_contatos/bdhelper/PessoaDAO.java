package br.com.dwb.crud_contatos.bdhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.com.dwb.crud_contatos.model.Pessoa;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String pessoas = "DROP TABLE IF EXISTS pessoa";
        db.execSQL(pessoas);
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
}
