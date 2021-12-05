package course.springadvanced.cookeasy.config;

import course.springadvanced.cookeasy.web.interceptor.StatisticsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class ApplicationWebConfiguration implements WebMvcConfigurer {
    private final StatisticsInterceptor statisticsInterceptor;
    private final LocaleChangeInterceptor localeChangeInterceptor;

    @Autowired
    public ApplicationWebConfiguration(StatisticsInterceptor statisticsInterceptor, LocaleChangeInterceptor localeChangeInterceptor) {
        this.statisticsInterceptor = statisticsInterceptor;
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.statisticsInterceptor);
        registry.addInterceptor(this.localeChangeInterceptor);
    }
}