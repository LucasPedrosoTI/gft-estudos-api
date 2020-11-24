///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gft.estudosapi;
//
//import com.gft.estudosapi.config.AppSwaggerConfig;
//import com.gft.estudosapi.config.SpringFoxConfig;
//import java.io.IOException;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//import org.springframework.core.io.Resource;
//import static org.springframework.core.io.support.PropertiesLoaderUtils.loadProperties;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
///**
// *
// * @author lps10
// */
//public class WebAppInitializer implements WebApplicationInitializer {
//    @Override
//    public void onStartup(ServletContext container) throws ServletException {
//
//        // Create the 'root' Spring application context
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(SpringFoxConfig.class);
//
//        // Manage the lifecycle of the root application context
//        container.addListener(new ContextLoaderListener(rootContext));
//
//        try {
//            loadProperties((Resource) rootContext);
//        } catch (IOException e) {
//            throw new ServletException("Properties could not be loaded", e);
//        }
//
//        // Create the dispatcher servlet's Spring application context
//        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
//        dispatcherServlet.register(AppSwaggerConfig.class);
//
//        // Register and map the dispatcher servlet
//        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
//                new DispatcherServlet(dispatcherServlet));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//    }
//
//}
