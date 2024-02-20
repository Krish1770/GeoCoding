package com.example.geoCoding.service.impl;

import com.example.geoCoding.DTO.LogDto;
import com.example.geoCoding.TemplateClass.RedisCounter;
import com.example.geoCoding.model.ResponseLog;
import com.example.geoCoding.repository.ResponseLogRepository;
import com.example.geoCoding.repository.Service.ResponseLogRepoService;
import com.example.geoCoding.repository.Service.SubscriptionViewRepoService;
import com.example.geoCoding.service.ResponseLogService;
import com.example.geoCoding.view.SubscriptionView;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class ResponseLogServiceImpl implements ResponseLogService {

    @Autowired
    RedisCounter redisCounter;


    @Autowired
    private ResponseLogRepository responseLogRepository;

    @Autowired
    private ResponseLogRepoService responseLogRepoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubscriptionViewRepoService subscriptionViewRepoService;

    @Override
    public ResponseLog add(LogDto logDto, String subscriptionId)
//    {
//
//        System.out.println(logDto);
//
//        Optional<ResponseLog> responseLog = responseLogRepoService.findBySubscriptionId(subscriptionId);
//        System.out.println(responseLog);
//        ResponseLog newResponseLog = new ResponseLog();
//        if (responseLog.isEmpty()) {
//
//
//            ArrayList<LogDto> logDtoList = new ArrayList<>();
//            logDtoList.add(logDto);
//
//            ArrayList<ResponseLogDto> responseLogDtoList = new ArrayList<>();
//            responseLogDtoList.add(new ResponseLogDto(new Date(), logDtoList));
//
//            newResponseLog.setSubscriptionId(subscriptionId);
//            newResponseLog.setResponseLogDtoList(responseLogDtoList);
//
//            responseLogRepository.save(newResponseLog);
//            return new ResponseLog();
//
//
//        } else {
//
//            AtomicReference<Boolean> isDatePresent = new AtomicReference<>(false);
//            responseLog.get().getResponseLogDtoList().forEach(i ->
//            {
//                System.out.println(new Date()+"  date1");
//                System.out.println(i.getDate()+"  date2");
//
//                if ((new Date().toString().substring(0,10)).
//                        equals(i.getDate().toString().substring(0,10))) {
//                    i.getLogDtoList().add(logDto);
//                    isDatePresent.set(true);
//                }
//            });
//
//            if (isDatePresent.get().toString().equals("false")) {
//                ArrayList<LogDto> logDtoList = new ArrayList<>();
//                logDtoList.add(logDto);
//                ArrayList<ResponseLogDto> responseLogDtoList = new ArrayList<>();
//                responseLogDtoList.add(new ResponseLogDto(new Date(), logDtoList));
//                responseLog.get().setResponseLogDtoList(responseLogDtoList);
//            }
//
//            responseLogRepository.save(responseLog.get());
//            return responseLog.get();
//        }
//    }

    {

        ResponseLog responseLog = new ResponseLog();
        responseLog = modelMapper.map(logDto, ResponseLog.class);
        responseLog.setDate(new Date());
        responseLog.setSubscriptionId(subscriptionId);

        responseLogRepository.save(responseLog);
//        updatingCache(subscriptionId);
        return responseLog;
    }

    private void updatingCache(String subscriptionId,String type) {
        String date = LocalDate.now().toString();
        log.info(redisCounter.toString());
        String key = subscriptionId;

        if(type.equals("requestBasis"))
            key+=date;
        if (Boolean.TRUE.equals(redisCounter.hasKey(key))) {
//                 System.out.println(getValue(key)+" "+key);
//                 redisTemplate.opsForValue().increment(key);
            redisCounter.incrementTheValue(key);
        } else {
//                 redisTemplate.opsForValue().set(key, Long.valueOf("1"));
            redisCounter.setValue(key, 1);
        }
    }

    private Long getValue(String key) {
        log.info(redisCounter.toString());
//        return redisTemplate.opsForValue().get(key);
        if (redisCounter.getValue(key) != null) {
            return Long.parseLong(redisCounter.getValue(key).toString());
        }
        return 0L;
    }

    @Override
    public Boolean isSubscriptionValid(String subscription) {


            String key = subscription +"";


        Optional<SubscriptionView> subscriptions = subscriptionViewRepoService.findBySubscriptionId(subscription);

        if(subscriptions.get().getPlanType().equals("requestBasis"))
        {
            key+= "_" + new Date().toString().substring(0, 10);
        }
        System.out.println(new Date().toString().substring(0, 10));
        Long count = getValue(key);

        System.out.println("yes i am");
        System.out.println(subscriptions.get().toString());
        if ( subscriptions.get().getPlanType().equals("requestBasis")) {
            if (count >= subscriptions.get().getRequestCount())
                return false;

            return true;
        }
        else if( subscriptions.get().getPlanType().equals("dayBasis"))
        {
    if(count>=subscriptions.get().getRequestCount()) {
        return false;
    }
    return  false;
        }

        updatingCache(key,subscriptions.get().getPlanType());
        return true;
    }
}
