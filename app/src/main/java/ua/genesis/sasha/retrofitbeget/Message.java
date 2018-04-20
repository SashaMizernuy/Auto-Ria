package ua.genesis.sasha.retrofitbeget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sasha on 24.03.18.
 */

public class Message {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
