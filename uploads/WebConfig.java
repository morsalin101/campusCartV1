@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/products/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/products/");
    }
}
