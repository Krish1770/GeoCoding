//package com.example.geoCoding.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.lang.NonNull;
//import org.springframework.lang.Nullable;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//@Configuration
//public class InterceptorHandler implements HandlerInterceptor {
//
//    @Override
//    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//
////        String requestData = request.getReader().lines().collect(Collectors.joining());
//
//  String ans=request.getCharacterEncoding();
//
//        System.out.println(ans);
//
//    }
//}
