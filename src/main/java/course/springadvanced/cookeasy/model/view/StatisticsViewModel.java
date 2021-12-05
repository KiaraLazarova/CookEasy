package course.springadvanced.cookeasy.model.view;

public class StatisticsViewModel {
    private int anonymousRequests;
    private int authenticatedRequests;

    public StatisticsViewModel() {
    }

    public int getAnonymousRequests() {
        return this.anonymousRequests;
    }

    public void setAnonymousRequests(int anonymousRequests) {
        this.anonymousRequests = anonymousRequests;
    }

    public int getAuthenticatedRequests() {
        return this.authenticatedRequests;
    }

    public void setAuthenticatedRequests(int authenticatedRequests) {
        this.authenticatedRequests = authenticatedRequests;
    }
}