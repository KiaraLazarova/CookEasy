package course.springadvanced.cookeasy.model.view;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticsViewModel {
    private int anonymousRequests;
    private int authenticatedRequests;
}