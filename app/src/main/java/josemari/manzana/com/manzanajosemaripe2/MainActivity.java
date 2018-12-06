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
}
