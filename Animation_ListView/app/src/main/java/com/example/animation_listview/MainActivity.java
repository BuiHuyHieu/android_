package com.example.animation_listview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Model> list;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        addModel();
        MyAdapter adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
        setAction();
    }

    private void addModel() {

        list = new ArrayList<Model>();
        list.add(new Model(R.drawable.facebook, "Facebook"));
        list.add(new Model(R.drawable.flickr, "Flickr"));
        list.add(new Model(R.drawable.google_plus, "Google Plus"));
        list.add(new Model(R.drawable.instagram, "Instagram"));
        list.add(new Model(R.drawable.pinterest, "Pinterest"));
        list.add(new Model(R.drawable.soundcloud, "SoundCloud"));
        list.add(new Model(R.drawable.tumblr, "Tumblr"));
        list.add(new Model(R.drawable.twitter, "Twitter"));
        list.add(new Model(R.drawable.linkedin, "Linkedin"));
        list.add(new Model(R.drawable.vk, "VK"));

    }

    private void setAction() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        connectURL("https://www.facebook.com/");
                        break;
                    case 1:
                        connectURL("https://www.flickr.com/");
                        break;
                    case 2:
                        connectURL("https://plus.google.com/");
                        break;
                    case 3:
                        connectURL("https://www.instagram.com");
                        break;
                    case 4:
                        connectURL("https://www.pinterest.com");
                        break;
                    case 5:
                        connectURL("https://soundcloud.com");
                        break;
                    case 6:
                        connectURL("https://www.tumblr.com");
                        break;
                    case 7:
                        connectURL("https://twitter.com/Twitter");
                        break;
                    case 8:
                        connectURL("https://www.linkedin.com/");
                        break;
                    case 9:
                        connectURL("https://vk.com/");
                        break;
                }
            }
        });
    }

    private void connectURL(String uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
}
