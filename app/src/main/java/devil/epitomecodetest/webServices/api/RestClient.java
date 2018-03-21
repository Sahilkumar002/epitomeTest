package devil.epitomecodetest.webServices.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by devil on 3/20/18.
 */

public class RestClient {
    private static API MY_CLIENT;
    private static Retrofit retrofit;

    public static API getClient() {


        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MY_CLIENT = retrofit.create(API.class);
        return MY_CLIENT;

    }

    public static Retrofit getRetrofitInstance() {
        return retrofit;
    }
}
