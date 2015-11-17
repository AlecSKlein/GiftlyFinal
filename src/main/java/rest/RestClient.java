package rest;

import models.FriendDAO;
import models.FriendListDAO;
import models.GiftDAO;
import models.InterestDAO;
import models.UserDAO;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Alec Klein on 11/16/2015.
 */
public class RestClient {

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
