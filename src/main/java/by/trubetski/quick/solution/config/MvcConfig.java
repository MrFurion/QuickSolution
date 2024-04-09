package by.trubetski.quick.solution.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * This method is used to define resource handlers in the application.
     * It is used to determine the location of static resources (such as CSS files, JavaScript, images, etc.)
     * and the corresponding URLs through which they are accessible.
     * In this case, it is necessary for the correct operation with the Thymeleaf templating engine.
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}