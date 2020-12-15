package id.suryaproject.a25nabi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import id.suryaproject.a25nabi.adapter.AdapterMain;
import id.suryaproject.a25nabi.model.ModelMain;

public class MainActivity extends AppCompatActivity implements AdapterMain.OnselectedItem {

    RecyclerView rvName;
    AdapterMain adapterMain;
    ArrayList<ModelMain> modelMains = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadJson();
    }

    private void init() {
        rvName= findViewById(R.id.recycleview);
        rvName.setHasFixedSize(true);
        rvName.setLayoutManager(new LinearLayoutManager(this));
    }
    private void  loadJson(){
        try {
            InputStream stream = getAssets().open("kisahnabi.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String tContents = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONArray obj = new JSONArray( tContents);
                for (int i=0;i<obj.length();i++){
                    JSONObject temp = obj.getJSONObject(i);
                    ModelMain dataApi = new ModelMain();
                    dataApi.setName(temp.getString("name"));
                    dataApi.setName_arab(temp.getString("name_arab"));
                    dataApi.setThn_kelahiran(temp.getString("thn_kelahiran"));
                    dataApi.setImage_url(temp.getString("image_url"));
                    dataApi.setUsia(temp.getString("usia"));
                    dataApi.setDescription(temp.getString("description"));
                    modelMains.add(dataApi);
                    adapterMain = new AdapterMain(getApplicationContext(),modelMains,this);
                    rvName.setAdapter(adapterMain);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void itemSelected(ModelMain modelMain) {
        Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
        intent.putExtra("data",modelMain);
        startActivity(intent);

    }
}