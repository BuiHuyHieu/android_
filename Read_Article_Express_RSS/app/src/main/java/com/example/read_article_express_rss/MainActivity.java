package com.example.read_article_express_rss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.midi.MidiDeviceService;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListView);
        new ReadArrticle().execute();

    }
    private class ReadArrticle extends AsyncTask<Void,Void,String>{


        @Override
        protected String doInBackground(Void... voids) {
            return new Read_RSS().read("https://vnexpress.net/rss/khoa-hoc.rss");

        }

        @Override
        protected void onPostExecute(String s) {
            List<String>Title = new ArrayList<String>();
            final List<String>link = new ArrayList<String>();
            super.onPostExecute(s);
            XMLDOMParser xmldomParser = new XMLDOMParser();

            Document document = xmldomParser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");

            String title="";
            for(int i=0 ; i<nodeList.getLength();i++) {
                title = xmldomParser.getValue((Element) nodeList.item(i), "title");
                Title.add(title);
                link.add(xmldomParser.getValue((Element) nodeList.item(i), "link"));
            }
            listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,Title));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this,Process.class);
                    intent.putExtra("Link",link.get(position));
                    startActivity(intent);
                }
            });

        }
    }

}




