package by.trubetski.quick.solution.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Defines the resource handler in the application.
     * Determines the locations of static resources (such as CSS files, JavaScript, images, etc.)
     * and the corresponding URLs where they are available.
     * In this case, this is necessary to work correctly with the Thymeleaf template creation mechanism.
     *
     * @param registry ResourceHandlerRegistry.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
