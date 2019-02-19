package com.franlopez.androidcertification;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.franlopez.androidcertification.commons.FileUtils;
import com.franlopez.androidcertification.commons.ValidationUtils;
import com.franlopez.androidcertification.data.github.GithubRepository;
import com.franlopez.androidcertification.db.MyDatabase;
import com.franlopez.androidcertification.db.dao.GithubRepoDao;
import com.franlopez.androidcertification.model.domain.GithubRepoDomain;
import com.franlopez.androidcertification.model.domain.GithubRepoSearchDomain;
import com.franlopez.androidcertification.ui.main.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(
            MainActivity.class);

    private GithubRepoDao githubRepoDao;
    private GithubRepository githubRepository;
    private MyDatabase db;

    @Before
    public void createDb() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(appContext, MyDatabase.class).build();
        githubRepoDao = db.reposDao();
        githubRepository = new GithubRepository();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void validateElementsFromService() {
        List<GithubRepoDomain> onlyAndroidElements = callAndroidGithubRepos();
        List<GithubRepoDomain> notOnlyAndroidElements = callRandomGithubRepos();

        assertTrue(allItemsContainsStringPassed(onlyAndroidElements, "Android"));
        assertFalse(allItemsContainsStringPassed(notOnlyAndroidElements, "Android"));
    }

    private boolean allItemsContainsStringPassed(List<GithubRepoDomain> elements, String text) {
        for (GithubRepoDomain current : elements) {
            if (current != null &&
            !ValidationUtils.containsText(current.getDescription(), text) &&
            !ValidationUtils.containsText(current.getName(), text)) {
                return false;
            }
        }
        return true;
    }

    private List<GithubRepoDomain> callAndroidGithubRepos() {
        GithubRepoSearchDomain response = new GithubRepoSearchDomain();
        response = FileUtils.readJsonFile("json/allItemsContainsAndroidText.json",
                                          response.getClass());
        return response != null ?
                response.getItems() : new ArrayList<GithubRepoDomain>();
    }

    private List<GithubRepoDomain> callRandomGithubRepos() {
        GithubRepoSearchDomain response = new GithubRepoSearchDomain();
        response = FileUtils.readJsonFile("json/notAllItemsContainsAndroidText.json",
                                          response.getClass());
        return response != null ?
                response.getItems() : new ArrayList<GithubRepoDomain>();
    }
}
