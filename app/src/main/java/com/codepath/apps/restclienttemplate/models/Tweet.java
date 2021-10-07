package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"))
public class Tweet {

    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String body;

    @ColumnInfo
    public String createdAt;

   // public String replyCount;
    @ColumnInfo
    public String retweetCount;
//    @ColumnInfo
//    public String favoriteCount;

    @ColumnInfo
    public Long userId;

    @Ignore
    public User user;

    // empty constructor needed by the Parceler library
    public Tweet(){}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        try{
            tweet.body = jsonObject.getString("text");
            tweet.createdAt = jsonObject.getString("created_at");
            User user = User.fromJson(jsonObject.getJSONObject("user"));
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
            tweet.user = user;
            assert user != null;
            tweet.userId = user.id;

           // tweet.replyCount = (jsonObject.getString("reply_count"));
            tweet.retweetCount = (jsonObject.getString("retweet_count"));
           // tweet.favoriteCount = (jsonObject.getString("like_count"));
            tweet.id = jsonObject.getLong("id");

        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {

        List<Tweet> tweets = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){

            tweets.add(fromJson(jsonArray.getJSONObject(i)));

        }
        return tweets;
    }
}
