package com.example.fatemeh.gallery.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fatemeh on 01/04/15.
 */

public class Image implements Parcelable{

    public static final String ID_KEY = "id";
    public static final String LINK_KEY = "link";
    public static final String TITLE_KEY = "title";
    public static final String UP_VOTES_KEY = "ups";
    public static final String DOWN_VOTES_KEY = "downs";
    public static final String DESCRIPTION_KEY = "description";

    private String id;
    private String link;
    private String title;
    private String description;

    private int upVotes;
    private int downVotes;

    public Image() {}

    public Image(JSONObject imageObject) {
        try {
            id = imageObject.getString(ID_KEY);
            link = imageObject.getString(LINK_KEY);
            title = imageObject.getString(TITLE_KEY);
            description = imageObject.getString(DESCRIPTION_KEY);

            upVotes = imageObject.getInt(UP_VOTES_KEY);
            downVotes = imageObject.getInt(DOWN_VOTES_KEY);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return description;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public static List<Image> getImagesFromJasonArray(JSONArray imagesJsonArray) {
        List<Image> imageList = new ArrayList<>();

        int listSize = Math.min(40, imagesJsonArray.length());

        for(int i = 0; i < listSize; i++) {
            try {
                JSONObject imageJsonObject = imagesJsonArray.getJSONObject(i);

                Image image = new Image();
                image.id = imageJsonObject.getString(ID_KEY);
                image.link = imageJsonObject.getString(LINK_KEY);
                image.title = imageJsonObject.getString(TITLE_KEY);
                image.description = imageJsonObject.getString(DESCRIPTION_KEY);

                image.upVotes = imageJsonObject.getInt(UP_VOTES_KEY);
                image.downVotes = imageJsonObject.getInt(DOWN_VOTES_KEY);

                imageList.add(image);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return imageList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(link);
        dest.writeString(title);
        dest.writeString(description);

        dest.writeInt(upVotes);
        dest.writeInt(downVotes);
    }

    public static final Parcelable.Creator<Image> CREATOR
            = new Parcelable.Creator<Image>() {
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    protected Image(Parcel source) {
        this.id = source.readString();
        this.link = source.readString();
        this.title = source.readString();
        this.description = source.readString();

        this.upVotes = source.readInt();
        this.downVotes = source.readInt();
    }
}
