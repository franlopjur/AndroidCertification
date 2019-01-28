package com.franlopez.androidcertification.commons;

public class Constants {
    public static final String BASE_URL = "https://api.github.com/";
    public static final String DB_NAME = "androidCertificationDatabase";
    public static final int ITEMS_PER_PAGE = 15;

    public static class DB {
        public static class GithubRepo {
            public static final String TABLE_NAME = "GithubRepos";
            public static final String FULL_NAME = "full_name";
        }
    }

    public static class DTO {
        public static class GithubRepo {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String FULL_NAME = "full_name";
            public static final String DESCRIPTION = "description";
            public static final String HTML_URL = "html_url";
            public static final String STARGAZERS_COUNT = "stargazers_count";
            public static final String FORKS_COUNT = "forks_count";
            public static final String LANGUAGE = "language";
        }

        public static class GithubRepoSearch {
            public static final String TOTAL_COUNT = "total_count";
            public static final String ITEMS = "items";
        }
    }
}
