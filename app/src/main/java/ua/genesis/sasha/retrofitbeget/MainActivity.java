package ua.genesis.sasha.retrofitbeget;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Message> product;
    List<Message> brand;
    ArrayList<String> typeCar;
    ArrayList<String> brandCar;
    Spinner spType;
    Spinner spBrand;


    class MyOnTypeSelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (spType.getSelectedItem().equals("Легкові")){

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spType=(Spinner) findViewById(R.id.spinner);
        spBrand=(Spinner) findViewById(R.id.spinnerBrand);


        //В билдере мы указываем базовый Url и добавляем GsonConverter
        //чтобы Retrofit сам смог сконвертировать json данные в обекты ua.genesis.sasha.retrofitbeget.Message
        //
        product=new ArrayList<>();
        brand=new ArrayList<>();



        typeCar=new ArrayList<>();
        brandCar=new ArrayList<>();

        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://developers.ria.com/auto/categories/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        //В итоге унас есть обект Retrofit который содержит базовый Url
        //и способность преобразовывать json данные с помощью Gson
        //мы передаем ему в метод create класс интерфейса
        //в котором описывали методы

        MessagesApi messagesApi=retrofit.create(MessagesApi.class);

        //И получаем реализацию MessagesApi от Retrofit
        //В этой реализации соеденены настройки билдера (базовый URL и Gson конвертер)
        //И описание методов из интерфейса (метод messages для получения файла messages1.json).


        final Call<List<Message>> productList=messagesApi.productList("bodystyles","HzQp7pByHXSWnDEDu75mYsKDYt4vKbQcALzKMrMS");

        productList.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                if(response.isSuccessful()){
                    Log.i("Script","response " + response.body());
                    product.addAll(response.body());
                    for(int i=0;i<product.size();i++)
                        typeCar.add(product.get(i).getName());
                    Log.i("Script","product " + product.get(1).getName());
                   onResume();

                }else{
                    Log.i("Script","response code" + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.i("Script","failure " + t);
            }
        });
        final Call<List<Message>> brandList=messagesApi.brandList("1","HzQp7pByHXSWnDEDu75mYsKDYt4vKbQcALzKMrMS");////////////////////////////////

        brandList.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(response.isSuccessful()){
                    Log.i("Script","response " + response.body());
                    brand.addAll(response.body());
                    for(int i=0;i<brand.size();i++)
                        brandCar.add(brand.get(i).getName());
                    Log.i("Script","brand " + brand.get(1).getName());

                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }
        public void onResume() {
            super.onResume();
            ArrayAdapter spTypeAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, typeCar);
                spType.setAdapter(spTypeAdapter);
                spType.setOnItemSelectedListener(new MyOnTypeSelectedListener());


        }


}
//Account Email: sasha.mizernuy@gmail.com
//Account ID: 57b2b93f-5204-4d90-b341-5c42840dfc87