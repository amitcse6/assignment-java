package com.am.assignment.config;

import com.am.assignment.converter.YamlJackson2HttpMessageConverter;
import com.am.assignment.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private StorageService storageService;

    private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/upload-image/**")
                .addResourceLocations(storageService.getDIR())
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        //WebMvcConfigurer.super.addResourceHandlers(registry);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//        exposeDirectory(storageService.getStorageDirImage(), registry);
//    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

        registry
                .addResourceHandler("/" + dirName + "/**")
                .addResourceLocations("file:/" + uploadPath + "/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorPathExtension(false)
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_YML);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("*")
                //.allowedOrigins("*")
//                .allowedOrigins(
//                        "**/hindu-religious-frontend/**",
//                        "file:///Users/amitmondol/Desktop/MyPc/**",
//                        "localhost:3001",
//                        "127.0.0.1:3001",
//                        "192.168.0.103:3001",
//                        "http://localhost:3001",
//                        "http://127.0.0.1:3001",
//                        "http://192.168.0.100:3001",
//                        "http://192.168.0.101:3001",
//                        "http://192.168.0.102:3001",
//                        "http://192.168.0.103:3001",
//                        "http://192.168.0.104:3001",
//                        "http://192.168.0.105:3001",
//                        "http://192.168.0.106:3001",
//                        "http://192.168.0.107:3001",
//                        "http://192.168.0.108:3001",
//                        "http://192.168.0.109:3001",
//                        "http://192.168.0.110:3001",
//                        "https://localhost:3001",
//                        "https://192.168.0.103:3001"
//                )
        ;
    }


//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) servletRequest;
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        System.out.println("WebConfig; " + request.getRequestURI());
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Expose-Headers", "Authorization");
//        response.addHeader("Access-Control-Expose-Headers", "responseType");
//        response.addHeader("Access-Control-Expose-Headers", "observe");
//        System.out.println("Request Method: " + request.getMethod());
//        if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
//            try {
//                filterChain.doFilter(servletRequest, servletResponse);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Pre-flight");
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers" + "Authorization, content-type," +
//                    "USERID" + "ROLE" +
//                    "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
//    }

    //    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*")
//                        .allowedOrigins(
//                                "localhost:3001",
//                                "192.168.0.103:3001",
//                                "http://localhost:3001",
//                                "http://192.168.0.103:3001",
//                                "https://localhost:3001",
//                                "https://192.168.0.103:3001"
//                        );
//            }
//        };
//    }
}
