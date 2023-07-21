package android.demosqlite3.View;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.demosqlite3.Controller.KhoaHandler;
import android.demosqlite3.Model.Khoa;
import android.demosqlite3.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    ArrayList<Khoa> lsKhoa=new ArrayList<>();
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        //------------------------------
        khoaHandler=new KhoaHandler(getApplicationContext(),KhoaHandler.DB_NAME,null,1);
        //khoaHandler.onCreate(db);
        //khoaHandler.initData();

        lsKhoa= khoaHandler.loadData();
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
                loadDataListView();

            }
        });

        //Xử lý chọn item của LsView
        lvKhoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Khoa k =lsKhoa.get(position);
              edtMaKhoa.setText(String.valueOf(k.getMakhoa()));
              edtMaKhoa.setEnabled(false);
              edtTenKhoa.setText(k.getTenkhoa());
            }
        });
        //---------------
        //Xử lý cho nút update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtMaKhoa.getText().toString()==null)
                {
                    return;
                }
                else {
                    Khoa newKhoa =
                            new Khoa(Integer.parseInt(edtMaKhoa.getText().toString()),
                                    edtTenKhoa.getText().toString());
                    Khoa oldKhoa =
                            timKhoa(Integer.parseInt(edtMaKhoa.getText().toString()),lsKhoa);
                    khoaHandler.updateKhoa(oldKhoa,newKhoa);
                    loadDataListView();

                }
            }
        });










    }

    public Khoa timKhoa(int makhoa, ArrayList<Khoa>lsKhoa)
    {
        for (Khoa a:lsKhoa  ) {
            if(a.getMakhoa()==makhoa)
                return a;
        }
        return null;
    }
    public void loadDataListView()
    {
        valuesLView = convertKhoaToString(khoaHandler.loadData());
        //adapter.notifyDataSetChanged();
        adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,valuesLView);
        lvKhoa.setAdapter(adapter);
    }





}