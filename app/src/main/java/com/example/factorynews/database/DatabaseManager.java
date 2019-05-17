package com.example.factorynews.database;

import com.example.factorynews.model.News;

import io.realm.Realm;
import io.realm.RealmResults;

public class DatabaseManager implements IDatabaseManager {

    private Realm realm;
    private static DatabaseManager dbManager;

    public DatabaseManager(){
        realm = Realm.getDefaultInstance();
    }
    public static DatabaseManager getDatabaseInstance() {
        if (dbManager == null) {
            dbManager = new DatabaseManager();
        }

        return dbManager;
    }

    @Override
    public RealmResults<News> getAllNews() {
        return realm.where(News.class).findAll();    }

    @Override
    public void addNews(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        News news = new News(author, title, description, url,urlToImage,publishedAt);
        realm.beginTransaction();
        realm.copyToRealm(news);
        realm.commitTransaction();
    }

    @Override
    public void deleteNews(RealmResults<News> realmList) {
        realmList = realm.where(News.class).findAll();
        realm.beginTransaction();
        realmList.deleteAllFromRealm();
        realm.commitTransaction();
    }
}
