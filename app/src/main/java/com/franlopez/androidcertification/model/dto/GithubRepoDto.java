package com.franlopez.androidcertification.model.dto;

import com.franlopez.androidcertification.commons.Constants;
import com.google.gson.annotations.SerializedName;

public class GithubRepoDto {
    @SerializedName(Constants.DTO.GithubRepo.ID)
    long id;
    @SerializedName(Constants.DTO.GithubRepo.NAME)
    String name;
    @SerializedName(Constants.DTO.GithubRepo.FULL_NAME)
    String fullName;
    @SerializedName(Constants.DTO.GithubRepo.DESCRIPTION)
    String description;
    @SerializedName(Constants.DTO.GithubRepo.HTML_URL)
    String url;
    @SerializedName(Constants.DTO.GithubRepo.STARGAZERS_COUNT)
    int stars;
    @SerializedName(Constants.DTO.GithubRepo.FORKS_COUNT)
    int forks;
    @SerializedName(Constants.DTO.GithubRepo.LANGUAGE)
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
