package myclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoSQLiteOpenHelper extends SQLiteOpenHelper {
    public ToDoSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //sentencia para crear la tabla
        db.execSQL(ToDoBase.CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //sentencia para borrar la tabla y re-crear
        db.execSQL(ToDoBase.DELETE_TABLE_NOTES);
        onCreate(db);
    }
}
