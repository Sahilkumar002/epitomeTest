package devil.epitomecodetest.webServices.api;

import java.util.List;

import devil.epitomecodetest.webServices.Pojos.Albums;
import devil.epitomecodetest.webServices.Pojos.Post;
import devil.epitomecodetest.webServices.Pojos.Users;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by devil on 3/20/18.
 */

public interface API {

    @GET("users")
    Call<List<Users>> getUsersList();

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer userId);

    @GET("albums")
    Call<List<Albums>> getAlbums(@Query("userId") Integer userId);
}