package myclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class ToDoBase implements BaseColumns {
    //Constantes
    public static final String DATABASE = "dbTodo.db";
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_DATE = "date";
    public static final int VERSION = 1;
    public static final String CREATE_TABLE_NOTES = "CREATE TABLE "+
            TABLE_NAME + " ("+
            COLUMN_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_TITLE+" TEXT NOT NULL, "+
            COLUMN_NAME_DESCRIPTION+" TEXT NOT NULL, "+
            COLUMN_NAME_DATE+" TEXT NOT NULL);";
    public static final String DELETE_TABLE_NOTES = "DROP TABLE IF EXIST "+ TABLE_NAME +";";

    //Varibles
    private static SQLiteDatabase objDB;
    private static ToDoSQLiteOpenHelper objHelper;

    public ToDoBase(Context context) {
        //inicializar el Helper
        objHelper = new ToDoSQLiteOpenHelper(context, DATABASE, null, VERSION);
    }

    public static Boolean OpenDatabase() throws SQLException
    {
        //Metodo para abrir la base de datos
        try
        {
            objDB = objHelper.getWritableDatabase();
        }
        catch (SQLException ex)
        {
            throw new SQLException("Error DB: "+ ex.getMessage() );
        }
        return true;
    }

    public static Boolean CloseDatabase() throws SQLException
    {
        //Metodo para cerrar la base de datos
        try
        {
            objDB.close();
        }
        catch (SQLException ex)
        {
            throw new SQLException("Error DB: "+ ex.getMessage() );
        }
        return true;
    }

    public static Boolean Insert(ToDo note) throws SQLException
    {
        try
        {
            ContentValues valInsert = new ContentValues();
            valInsert.put(COLUMN_NAME_TITLE, note.getTitulo());
            valInsert.put(COLUMN_NAME_DESCRIPTION, note.getDescripcion());
            valInsert.put(COLUMN_NAME_DATE, note.getFecha());

            //si se insertó el registro
            if(objDB.insert(TABLE_NAME, null, valInsert) > 0){
                return true;
            }else{
                return false;
            }
        }
        catch (SQLException ex)
        {
            throw new SQLException("Error DB: "+ ex.getMessage() );
        }
    }

    public static Cursor getNotesToDo() throws SQLException
    {
        try
        {
            return objDB.query(TABLE_NAME, new String[]{COLUMN_NAME_ID, COLUMN_NAME_TITLE, COLUMN_NAME_DESCRIPTION, COLUMN_NAME_DATE},
                    null, null, null, null, null);
        }
        catch (SQLException ex)
        {
            throw new SQLException("Error DB: "+ ex.getMessage() );
        }
    }
}
