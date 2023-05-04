package com.cookeasy.service;

import com.cookeasy.model.view.StatisticsViewModel;

public interface StatisticsService {
    void onRequest();
    StatisticsViewModel getStatistics();
}