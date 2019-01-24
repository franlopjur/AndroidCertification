package com.franlopez.androidcertification.data.dto;

import com.google.gson.annotations.SerializedName;

public class RepoDto {
    @SerializedName("id")
    long id;
    @SerializedName("name")
    String name;
    @SerializedName("full_name")
    String fullName;
    @SerializedName("description")
    String description;
    @SerializedName("html_url")
    String url;
    @SerializedName("stargazers_count")
    int stars;
    @SerializedName("forks_count")
    int forks;
    @SerializedName("language")
    String language;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public int getStars() {
        return stars;
    }

    public int getForks() {
        return forks;
    }

    public String getLanguage() {
        return language;
    }
}
