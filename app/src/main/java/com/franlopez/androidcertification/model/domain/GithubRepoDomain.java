package com.franlopez.androidcertification.model.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.franlopez.androidcertification.commons.Constants;

@Entity(tableName = Constants.DB.GithubRepo.TABLE_NAME)
public class GithubRepoDomain {
    @PrimaryKey
    private long id;
    private String name;
    @ColumnInfo(name = Constants.DB.GithubRepo.FULL_NAME)
    private String fullName;
    private String description;
    private String url;
    private int stars;
    private int forks;
    private String language;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
