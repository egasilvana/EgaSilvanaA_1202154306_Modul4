package com.example.android.egasilvanaa_1202154306_studycase4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListNama extends AppCompatActivity {

    //membuat variabel
    private String [] names = {"Ronald Lois","Ferdinan Ginting", "Firza Venanda", "Akbar Anggit", "Anthonius Ongko", "Anggi Priatna", "Wahyu Aji", "Muhamad Nurichsan", "Sayyid Pratama",
            "Faizal Sudarajat", "Aprianil Sesti", "Farhan Putra", "Nur Fitriyani", "Hana Nur", "Dwi Ratna", "Suci Wulanda", "Firda Nurul", "Khalida Zia", "Michael Adiansyah", "Halim Afif",
            "Muhammad Rinadhi", "Ahmad Taufiq", "Farah Urfani", "Labbaika Putri", "Aldy Lorenzo", "Mochammad Riza", "Mohamad Ikhsan", "Arkan Tara", "Fahmi Ananda", "Vian Rasyid", "Sandy Setiawan",
            "Dimas Komara", "Poltak Hasudungan", "Ayu Saraswati", "Ranti Dwita", "Ega Silvana", "Pratiwi Ika", "Hir Nanda", "Hylda Rizqa", "Nyimas Marissa", "Anggit Nur","Poltak Hasudungan",
            "Ayu Saraswati", "Ranti Dwita", "Ega Silvana", "Pratiwi Ika", "Hir Nanda", "Hylda Rizqa", "Nyimas Marissa", "Anggit Nur"};
    private ListView listView;
    private Button list;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama);

        //mereferensikan setiap button dan listview
        list = (Button) findViewById(R.id.buttoncari);
        listView = (ListView) findViewById(R.id.list_view);

        //membuat adapter listview
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //aksi ketika tombol ditekan
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask myTask = new MyTask(ListNama.this,listView,list);
                myTask.execute();
                list.setEnabled(false);
            }
        });
    }
    class MyTask extends AsyncTask<Void,String,String> {

        Context context;
        Button button;
        ListView listView;
        ProgressDialog progressDialog;
        ArrayAdapter<String> adapter;
        int count;

        public MyTask (Context context, ListView listView, Button list){
            this.context = context;
            this.button = list;
            this.listView = listView;
        }


        //proses preExecute berisi progressdialog
        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) listView.getAdapter();
            count = 0;
            //membuat progressdialog
            progressDialog = new ProgressDialog(context);
            //set pesan dari progressdialog
            progressDialog.setMessage("Loading Data");
            //set maksimal perhitungan
            progressDialog.setMax(50);
            progressDialog.setProgress(0);
            //set style progressdialog
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //menampilkan progress dialog
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (String Name : names){
                publishProgress(Name); //menampilkan Name (berisi array dari names )
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            progressDialog.setProgress(count);
        }

        @Override
        protected void onPostExecute(String result) {
            list.setEnabled(true);
            progressDialog.hide(); //menyembunyikan progressdialog
            Toast.makeText(ListNama.this, "Data Complete", Toast.LENGTH_SHORT).show();
        }
    }
}
