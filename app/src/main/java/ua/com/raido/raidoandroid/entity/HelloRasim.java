package ua.com.raido.raidoandroid.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HelloRasim {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content")
    @Expose
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
