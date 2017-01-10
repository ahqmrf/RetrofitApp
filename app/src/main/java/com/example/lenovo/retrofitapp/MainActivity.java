package com.example.lenovo.retrofitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.github.com/";
    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<User.ItemsBean> users;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        OkHttpClient okClient = new OkHttpClient.Builder().addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().
                                newBuilder().
                                addHeader("Accept", "Application/JSON").build();
                        return chain.proceed(request);
                    }
                }
        ).build();

        Retrofit retrofitRef = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPIEndPointInterface service = retrofitRef.create(MyAPIEndPointInterface.class);
        Call<User> call = service.getUserName("a");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                Log.d(TAG, "onResponse: " + response.code());

                if(response.isSuccessful()) {
                    users = new ArrayList<>();
                    User result = response.body();
                    users = result.getItems();
                    adapter = new UserAdapter(users);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
