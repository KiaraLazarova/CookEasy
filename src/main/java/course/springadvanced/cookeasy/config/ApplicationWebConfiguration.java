package course.springadvanced.cookeasy.config;

import course.springadvanced.cookeasy.web.interceptor.StatisticsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebConfiguration implements WebMvcConfigurer {
    private final StatisticsInterceptor statisticsInterceptor;

    @Autowired
    public ApplicationWebConfiguration(StatisticsInterceptor statisticsInterceptor) {
        this.statisticsInterceptor = statisticsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.statisticsInterceptor);
    }
}