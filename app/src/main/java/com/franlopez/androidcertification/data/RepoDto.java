package com.franlopez.androidcertification.data;

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
}
