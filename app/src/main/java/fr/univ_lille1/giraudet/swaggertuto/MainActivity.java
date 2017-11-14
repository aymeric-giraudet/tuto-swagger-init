package fr.univ_lille1.giraudet.swaggertuto;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import io.swagger.client.ApiException;
import io.swagger.client.api.EtudiantsApi;
import io.swagger.client.model.Etudiant;

public class MainActivity extends AppCompatActivity {

    private final EtudiantsApi api = new EtudiantsApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        PostEtudiantsJob post = new PostEtudiantsJob();
        post.execute();
        */
        GetEtudiantsJob get = new GetEtudiantsJob();
        get.execute();
    }

    public void onClick(View view) {
        GetEtudiantsJob get = new GetEtudiantsJob();
        get.execute();
    }

    private class GetEtudiantsJob extends AsyncTask<Void, Void, List<Etudiant>> {
        @Override
        protected List<Etudiant> doInBackground(Void... params) {
            try {
                return api.getEtudiants();
            } catch (ApiException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(List<Etudiant> result) {
            ListView listView = (ListView) findViewById(R.id.listView);
            final ArrayAdapter<Etudiant> adapter = new ArrayAdapter<Etudiant>(MainActivity.this,
                    android.R.layout.simple_list_item_1, result);
            listView.setAdapter(adapter);
        }
    }

    private class PostEtudiantsJob extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }
}
