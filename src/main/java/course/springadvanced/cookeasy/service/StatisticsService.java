package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.view.StatisticsViewModel;

public interface StatisticsService {
    void onRequest();
    StatisticsViewModel getStatistics();
}