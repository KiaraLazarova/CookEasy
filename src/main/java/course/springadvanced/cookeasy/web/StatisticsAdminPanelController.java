package course.springadvanced.cookeasy.web;

import course.springadvanced.cookeasy.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsAdminPanelController {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsAdminPanelController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/statistics")
    public String retrieveStatisticsPage(Model model) {
        model.addAttribute("viewModel", this.statisticsService.getStatistics());

        return "statistics-admin";
    }
}