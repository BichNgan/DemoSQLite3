package android.demosqlite3.View;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.demosqlite3.Controller.KhoaHandler;
import android.demosqlite3.Model.Khoa;
import android.demosqlite3.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtMaKhoa, edtTenKhoa;
    Button btnInsert, btnUpdate;
    ListView lvKhoa;
    SQLiteDatabase db;
    KhoaHandler khoaHandler;
    ArrayList<String> valuesLView = new ArrayList<>();
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        //------------------------------
        khoaHandler=new KhoaHandler(getApplicationContext(),KhoaHandler.DB_NAME,null,1);
        khoaHandler.onCreate(db);
        khoaHandler.initData();

        ArrayList<Khoa> lsKhoa = khoaHandler.loadData();
        //Hiển thị dữ liệu lên listview
        valuesLView=convertKhoaToString(lsKhoa);
        adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,valuesLView);
        lvKhoa.setAdapter(adapter);
        //------------------------------
        addEvent();
    }
    ArrayList<String> convertKhoaToString(ArrayList<Khoa>lsKhoa)
    {
        ArrayList<String> lsKq=new ArrayList<>();
        for (Khoa k:lsKhoa   ) {
            String s=String.valueOf(k.getMakhoa()) +" " +k.getTenkhoa();
            lsKq.add(s);
        }
        return lsKq;
    }
    public void addControl()
    {
        edtMaKhoa=(EditText) findViewById(R.id.edtMaKhoa);
        edtTenKhoa=(EditText) findViewById(R.id.edtTenKhoa);
        btnInsert=(Button) findViewById(R.id.btnInsert);
        btnUpdate=(Button) findViewById(R.id.btnUpdate);
        lvKhoa=(ListView) findViewById(R.id.lvKhoa);
    }
    public void addEvent()
    {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int makhoa=Integer.parseInt(edtMaKhoa.getText().toString());
                String tenKhoa=edtTenKhoa.getText().toString();
                khoaHandler.insertANewRecord(makhoa,tenKhoa );

                valuesLView = convertKhoaToString(khoaHandler.loadData());
                adapter.notifyDataSetChanged();
            }
        });
    }
}