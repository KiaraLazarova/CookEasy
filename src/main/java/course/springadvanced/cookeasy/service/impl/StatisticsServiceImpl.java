package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.model.view.StatisticsViewModel;
import course.springadvanced.cookeasy.service.StatisticsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private int anonymousRequests;
    private int authenticatedRequests;

    @Override
    public void onRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && (authentication.getPrincipal() instanceof UserDetails)) ++this.authenticatedRequests;
        else ++this.anonymousRequests;
    }

    @Override
    public StatisticsViewModel getStatistics() {
        StatisticsViewModel statisticsViewModel = new StatisticsViewModel();

        statisticsViewModel.setAnonymousRequests(this.anonymousRequests);

        statisticsViewModel.setAuthenticatedRequests(this.authenticatedRequests);

        return statisticsViewModel;
    }
}