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

    private static final String ID_KEY = "id";
    private static final String LINK_KEY = "link";
    private static final String TYPE_KEY = "type";
    private static final String TITLE_KEY = "title";
    private static final String WIDTH_KEY = "width";
    private static final String SCORE_KEY = "score";
    private static final String UP_VOTES_KEY = "ups";
    private static final String HEIGHT_KEY = "height";
    private static final String DOWN_VOTES_KEY = "downs";
    private static final String DESCRIPTION_KEY = "description";

    private String id;
    private String link;
    private String title;
    private String description;

    private int score;
    private int upVotes;
    private int downVotes;

    public Image() {}

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

    public int getScore() {
        return score;
    }

    public static List<Image> getImagesFromJasonArray(JSONArray imagesJsonArray) {
        List<Image> imageList = new ArrayList<>();

        int listSize = Math.min(60, imagesJsonArray.length());

        for(int i = 0; i < listSize; i++) {
            try {
                JSONObject imageJsonObject = imagesJsonArray.getJSONObject(i);

                if(shouldAddToList(imageJsonObject)) {
                    Image image = imageFromJsonObject(imageJsonObject);

                    if(image != null) {
                        imageList.add(image);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return imageList;
    }

    private static boolean shouldAddToList(JSONObject imageJsonObject) {
        boolean hasCorrectFormat = false;

        try {
            if(imageJsonObject.has(TYPE_KEY)){
                String type = imageJsonObject.getString(TYPE_KEY);
                if (type.startsWith("image/")) {
                    hasCorrectFormat = true;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return hasCorrectFormat;
    }

    private static Image imageFromJsonObject(JSONObject imageJsonObject) {
        Image image = new Image();

        try {
            if(!imageJsonObject.isNull(ID_KEY)) {
                image.id = imageJsonObject.getString(ID_KEY);
            }

            if(!imageJsonObject.isNull(LINK_KEY)) {
                image.link = imageJsonObject.getString(LINK_KEY);
            }

            if(!imageJsonObject.isNull(TITLE_KEY)) {
                image.title = imageJsonObject.getString(TITLE_KEY);
            }

            if(!imageJsonObject.isNull(DESCRIPTION_KEY)) {
                image.description = imageJsonObject.getString(DESCRIPTION_KEY);
            }

            image.score = imageJsonObject.getInt(SCORE_KEY);
            image.upVotes = imageJsonObject.getInt(UP_VOTES_KEY);
            image.downVotes = imageJsonObject.getInt(DOWN_VOTES_KEY);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
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

        dest.writeInt(score);
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

        this.score = source.readInt();
        this.upVotes = source.readInt();
        this.downVotes = source.readInt();
    }
}
