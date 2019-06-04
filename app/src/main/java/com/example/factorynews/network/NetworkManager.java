package com.example.factorynews.network;

import com.example.factorynews.model.NewsResponse;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Callback;

public class NetworkManager implements INetworkManager {
    private static final String API_KEY ="6946d0c07a1c4555a4186bfcade76398";
    private static final String SOURCE ="bbc-news";
    private static final String SORT_BY ="top";

    private static NetworkManager networkManager;
    private final IApiClient apiClient;

    public NetworkManager() {
        // Get ApiClient instance
        apiClient = ApiClient.getApiServiceInstance();
    }

    // Implementing Singleton pattern
    public static NetworkManager getInstance() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }
        return networkManager;
    }

    @Override
    public void getNewsList(final Callback<NewsResponse> newsListCallback) {
        // Send network request every 5 minutes with corresponding parameters
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                apiClient.getNewsData(SOURCE, SORT_BY, API_KEY).enqueue(newsListCallback);
            }
        }, 0, 5, TimeUnit.MINUTES);

    }
}
