package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Parcel
@Entity
public class User {

    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String screenName;

    @ColumnInfo
    public String profileImageUrl;

    //public boolean isVerified;

    // empty constructor needed by the Parceler library
    public User(){}

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        try{
            user.id = jsonObject.getLong("id");
            user.name = jsonObject.getString("name");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImageUrl = jsonObject.getString("profile_image_url_https");
//            user.isVerified = jsonObject.getBoolean("verified");
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public static List<User> fromJsonTweetArray(List<Tweet> tweetFromNetwork) {
        List<User> users = new ArrayList<>();

        for(int i = 0; i < tweetFromNetwork.size(); i++){
            users.add(tweetFromNetwork.get(i).user);
        }
        return users;
    }
}
