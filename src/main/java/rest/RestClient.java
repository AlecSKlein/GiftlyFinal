package rest;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import models.FriendDAO;
import models.FriendListDAO;
import models.GiftDAO;
import models.InterestDAO;
import models.UserDAO;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Alec Klein on 11/16/2015.
 */
public class RestClient {

    private static GiftlyApiInterface giftlyApiInterface;
    private static String baseUrl = "http://192.168.1.249:5000";

    public static GiftlyApiInterface getClient(){
        if(giftlyApiInterface == null) {
            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            giftlyApiInterface = client.create(GiftlyApiInterface.class);
        }
        return giftlyApiInterface ;
    }

    public interface GiftlyApiInterface {
        @POST("/api/user/registeruser")
        Call<UserDAO> registerUser(@Body UserDAO user);

        @POST("/api/user/loginuser")
        Call<UserDAO> loginUser(@Body UserDAO user);

        @POST("/api/user/getallfriends")
        Call<FriendListDAO> getAllFriends(@Body UserDAO user);

        @POST("/api/user/getfriendinfo")
        Call<FriendDAO> getFriendInfo(@Body UserDAO user, @Body FriendDAO friendid);

        @POST("/api/user/friend/addinterest")
        Call<InterestDAO> addFriendInterest(@Body FriendDAO friendid, @Body InterestDAO interest);

        @POST("/api/user/friend/addgift")
        Call<GiftDAO> addFriendInterest(@Body FriendDAO friendid, @Body GiftDAO gift);
    }
}
