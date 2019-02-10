package com.franlopez.androidcertification.commons;

import android.util.Log;

import com.franlopez.androidcertification.CustomApplication;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    //region Public Methods
    public static <T> T readJsonFile(String pathJsonFile, Class<T> clazz) {
        String json = null;
        InputStream inputStreamFromFile = null;
        try {
            inputStreamFromFile = CustomApplication.getInstance().getAssets().open(pathJsonFile);
            int size = inputStreamFromFile.available();
            byte[] buffer = new byte[size];
            inputStreamFromFile.read(buffer);
            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (Exception ex) {
            Log.e(TAG, "Error parseando fichero json");

        } finally {
            closeInputStream(inputStreamFromFile);
        }
        return new Gson().fromJson(json, clazz);
    }
    //endregion

    //region Private Methods
    private static void closeInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();

            } catch (IOException e) {
                Log.e(TAG, "Error parseando fichero json");
            }
        }
    }
    //endregion
}
