package xyz.vimtools.share.global.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 配置拦截器
 *
 * @EnableWebMvc + extends WebMvcConfigurationAdapter，在扩展的类中重写父类的方法即可，这种方式会屏蔽springboot的@EnableAutoConfiguration中的设置
 * extends WebMvcConfigurationSupport，在扩展的类中重写父类的方法即可，这种方式会屏蔽springboot的@EnableAutoConfiguration中的设置
 * extends WebMvcConfigurationAdapter，在扩展的类中重写父类的方法即可，这种方式依旧使用springboot的@EnableAutoConfiguration中的设置
 *
 * @author zhangzheng
 * @version 1.0.0
 * @date 2017-4-28
 */
@Configuration
@EnableWebMvc
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/user",
                        "/user/login"
                );
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
}
