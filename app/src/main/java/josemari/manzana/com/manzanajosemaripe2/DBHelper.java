package josemari.manzana.com.manzanajosemaripe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {

    final static String DBName = "person.db";
    final static int ver = 1;
    Cursor rs;
    final static String table = "person";

    public DBHelper(Context context) {
        super(context, DBName, null, ver);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cTable = "CREATE TABLE grade (ID INTEGER PRIMARY KEY AUTOINCREMENT, Fname TEXT, Age INTEGER, Gender TEXT)";
        db.execSQL(cTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dTable = "DROP TABLE IF EXISTS person";
        db.execSQL(dTable);
        onCreate(db);
    }

    public Cursor selectRecord(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM person", null);
    }

    public boolean insert(String fname, String gender, int age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Fname", fname);
        cv.put("Age", age);
        cv.put("Gender", gender);
        long inserted = db.insert(table, null, cv);
        if (inserted == -1){
            return false;
        } else return true;
    }

}
