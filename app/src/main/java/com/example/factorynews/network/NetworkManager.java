package com.example.factorynews.network;

import com.example.factorynews.model.NewsResponse;

import retrofit2.Callback;

public class NetworkManager implements INetworkManager {
    private static final String API_KEY ="6946d0c07a1c4555a4186bfcade76398";
    private static final String SOURCE ="bbc-news";
    private static final String SORT_BY ="top";

    private static NetworkManager networkManager;
    private final IApiClient apiClient;

    public NetworkManager() {
        apiClient = ApiClient.getApiServiceInstance();
    }

    public static NetworkManager getInstance() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }
        return networkManager;
    }
    @Override
    public void getNewsList(Callback<NewsResponse> newsListCallback) {
        apiClient.getNewsData(SOURCE, SORT_BY, API_KEY).enqueue(newsListCallback);

    }
}
