package com.example.calculadora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "calculadora";
    private static final int VERSAO_BANCO = 2;
    private static final String TABLE_NAME = "calculadora";
    /*
     * O enunciado do Hands on Work solicitava que fossem criados os campos valor1, valor2, operacao e resultado para
     * persistência dos dados, mas o layout a ser seguido sugeria que a calculadora deveria realizar não apenas cálculos
     * de dois valores, mas de expressões. Desta forma, esta calculadora calcula expressões, mas os campos salvos para
     * persistência de dados são apenas 'expressão' e 'resultado'.
     */
    private static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + //
            "expressao VARCHAR, resultado VARCHAR);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public long insert(String expressao, String resultado){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("expressao", expressao);
        cv.put("resultado", resultado);
        try {
            return db.insert(TABLE_NAME, null, cv);
        } finally {
            db.close();
        }
    }
    public void select(Context context, ListView lv){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id","expressao", "resultado" };
        int[] to = {R.id.textViewHistoryId, R.id.textViewHistoryExpression, R.id.textViewHistoryResult};
        Cursor data = db.query(TABLE_NAME, columns, null, null, null, null, "_id DESC" );
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.item_history, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();

    }
}
