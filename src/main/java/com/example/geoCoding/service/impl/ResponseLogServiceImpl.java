package com.example.geoCoding.service.impl;

import com.example.geoCoding.DTO.LogDto;
import com.example.geoCoding.DTO.ResponseLogDto;
import com.example.geoCoding.model.ResponseLog;
import com.example.geoCoding.repository.ResponseLogRepository;
import com.example.geoCoding.repository.Service.ResponseLogRepoService;
import com.example.geoCoding.service.ResponseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ResponseLogServiceImpl implements ResponseLogService {

    @Autowired
    private ResponseLogRepository responseLogRepository;

    @Autowired
    private ResponseLogRepoService responseLogRepoService;

    @Override
    public ResponseLog add(LogDto logDto, String subscriptionId) {

        System.out.println(logDto);

        Optional<ResponseLog> responseLog = responseLogRepoService.findBySubscriptionId(subscriptionId);
        System.out.println(responseLog);
        ResponseLog newResponseLog = new ResponseLog();
        if (responseLog.isEmpty()) {


            ArrayList<LogDto> logDtoList = new ArrayList<>();
            logDtoList.add(logDto);

            ArrayList<ResponseLogDto> responseLogDtoList = new ArrayList<>();
            responseLogDtoList.add(new ResponseLogDto(new Date(), logDtoList));

            newResponseLog.setSubscriptionId(subscriptionId);
            newResponseLog.setResponseLogDtoList(responseLogDtoList);

            responseLogRepository.save(newResponseLog);
            return new ResponseLog();


        } else {

            AtomicReference<Boolean> isDatePresent = new AtomicReference<>(false);
            responseLog.get().getResponseLogDtoList().forEach(i ->
            {
                System.out.println(new Date()+"  date1");
                System.out.println(i.getDate()+"  date2");

                if ((new Date().toString().substring(0,10)).
                        equals(i.getDate().toString().substring(0,10))) {
                    i.getLogDtoList().add(logDto);
                    isDatePresent.set(true);
                }
            });

            if (isDatePresent.get().toString().equals("false")) {
                ArrayList<LogDto> logDtoList = new ArrayList<>();
                logDtoList.add(logDto);
                ArrayList<ResponseLogDto> responseLogDtoList = new ArrayList<>();
                responseLogDtoList.add(new ResponseLogDto(new Date(), logDtoList));
                responseLog.get().setResponseLogDtoList(responseLogDtoList);
            }

            responseLogRepository.save(responseLog.get());
            return responseLog.get();
        }
    }

}
