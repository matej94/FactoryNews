package com.example.factorynews.database;

import com.example.factorynews.model.News;

import io.realm.Realm;
import io.realm.RealmResults;

public class DatabaseManager implements IDatabaseManager {

    private Realm realm;
    private static DatabaseManager dbManager;

    public DatabaseManager(){
        // Get realm instance
        realm = Realm.getDefaultInstance();
    }

    // Implementing Singleton pattern for database
    public static DatabaseManager getDatabaseInstance() {
        // Create new instance if there is no instance available
        if (dbManager == null) {
            dbManager = new DatabaseManager();
        }

        return dbManager;
    }

    // Get all data from database
    @Override
    public RealmResults<News> getAllNews() {
        return realm.where(News.class).findAll();    }

   // Add data to database
    @Override
    public void addNews(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        News news = new News(author, title, description, url,urlToImage,publishedAt);
        realm.beginTransaction();
        realm.copyToRealm(news);
        realm.commitTransaction();
    }

    // Delete data from database
    @Override
    public void deleteNews(RealmResults<News> realmList) {
        realmList = realm.where(News.class).findAll();
        realm.beginTransaction();
        realmList.deleteAllFromRealm();
        realm.commitTransaction();
    }
}
