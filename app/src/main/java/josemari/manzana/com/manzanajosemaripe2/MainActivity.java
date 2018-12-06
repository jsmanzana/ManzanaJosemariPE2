package josemari.manzana.com.manzanajosemaripe2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText fname,age,gender;
    DBHelper helper = new DBHelper(this);
    Cursor table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = helper.selectRecord();
        fname = findViewById(R.id.etFname);
        age = findViewById(R.id.etAge);
        gender = findViewById(R.id.etGender);
    }

    public void addRecord(View v){
        String f = fname.getText().toString();
        String l = gender.getText().toString();
        int s = Integer.parseInt(age.getText().toString());
        boolean inserted = helper.insert(f,l,s);
        if (inserted == true){
            Toast.makeText(this, "Record inserted", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record not inserted", Toast.LENGTH_LONG).show();
    }

    public void first (View v){
        table.moveToFirst();
        data();
    }

    public void previous (View v){
        table.moveToPrevious();
        if (table.isBeforeFirst()){
            Toast.makeText(this, "Record is at first position", Toast.LENGTH_LONG).show();
            //table.moveToFirst();
        } else data();
    }

    public void next (View v){
        table.moveToNext();
        if (table.isAfterLast()) {
            Toast.makeText(this, "Record is at last position", Toast.LENGTH_LONG).show();
            //table.moveToLast();
        } else data();
    }
    public void last (View v){
        table.moveToLast();
        data();
    }

    public void data(){
        fname.setText(table.getString(1));
        age.setText(table.getString(2));
        gender.setText(table.getString(3));
    }

    public void editRecord(View v){
        String id = table.getString(0);
        String f = fname.getText().toString();
        String l = gender.getText().toString();
        int s = Integer.parseInt(age.getText().toString());

        boolean updated = helper.update(id,f,l,s);
        if (updated == true){
            Toast.makeText(this, "Record updated", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record not updated", Toast.LENGTH_LONG).show();
    }

    public void deleteRecord(View v){
        String id = table.getString(0);
        boolean deleted = helper.delete(id);
        if (deleted == true){
            Toast.makeText(this, "Record deleted", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record deleted", Toast.LENGTH_LONG).show();
    }

}
