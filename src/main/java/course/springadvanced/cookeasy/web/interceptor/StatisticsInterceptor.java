package course.springadvanced.cookeasy.web.interceptor;

import course.springadvanced.cookeasy.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StatisticsInterceptor implements HandlerInterceptor {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsInterceptor(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.statisticsService.onRequest();
        return true;
    }
}