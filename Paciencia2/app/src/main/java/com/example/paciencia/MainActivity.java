package com.example.paciencia;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Mumu" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Regras.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Regras planetas = retrofit.create(Regras.class);
        Call<StarPlanets> requestPlanets = planetas.listPlanets();

        requestPlanets.enqueue(new Callback<StarPlanets>() {
            @Override
            public void onResponse(Call<StarPlanets> call, Response<StarPlanets> response) {
                if (!response.isSuccessful()){
                    Log.i("TAG", "Erro" + response.code());
                }

                    else{
                        //sucesso
                        StarPlanets stars = response.body();

                        for (Planet p : stars.planets){
                            Log.i(TAG, String.format("%s:  %s", p.name, p.climate ));

                                Log.i(TAG, "----------" );
                        }
                }
            }

            @Override
            public void onFailure(Call<StarPlanets> call, Throwable t) {
            Log.e(TAG, "Erro: " + t.getMessage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



