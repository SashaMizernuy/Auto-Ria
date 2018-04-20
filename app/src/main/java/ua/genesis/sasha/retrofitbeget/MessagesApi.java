package ua.genesis.sasha.retrofitbeget;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ua.genesis.sasha.retrofitbeget.Message;

/**
 * Created by sasha on 24.03.18.
 */

public interface MessagesApi {
    //В аннотации GET указываем что к базовому URL "надо добавить" , чтобы получилась нужная нам ссылка
    @GET("{body}?")
    //Call это обертка нужна для работы Retrofit
    //В ней мы указываем какой тип данных ожидаем получить из "добавленного адреса"
    //- т.е. List<ua.genesis.sasha.retrofitbeget.Message>.
    Call<List<Message>> productList(@Path("body")String body,@Query("api_key") String key);
//
    //Путь полного запроса   https://developers.ria.com/auto/categories/1/bodystyles?&api_key=fw1lbFdNVSvHOM5IIGZGH0l6JcwVUKyo2bV7diGV

    //@Path("body")


    //@Query("api_key")- знак "=" добавляется автоматически так как использовалась анотация @Query
    //@Query выполняет запрос с параметрами
    //сам ключ добавляем в Call<List<Message>> productList в MainActivity
    @GET("{id}/marks?")
    Call<List<Message>> brandList(@Path("id")String id,@Query("api_key") String key);
    //Марки зависят от типов транспорта. Поэтому для того, чтобы получить список марок необходимо отправить GET запрос
    //https://developers.ria.com/auto/categories/:categoryId/marks?api_key=YOUR_API_KEY
    //где categoryId - идентификатор типа транспорта, api_key- Ваш ключ.
    //Например, для легковых автомобилей (https://developers.ria.com/auto/categories/1/marks?api_key=YOUR_API_KEY)
}
