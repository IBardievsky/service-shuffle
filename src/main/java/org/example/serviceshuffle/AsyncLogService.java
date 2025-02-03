package org.example.serviceshuffle;

import org.example.serviceshuffle.dto.ShuffleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class AsyncLogService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncLogService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-log.url}")
    private String serviceLogUrl;

    @Async
    public void logRequestAsync(ShuffleRequest shuffleRequest) {
        String endpoint = serviceLogUrl + "/log";
        try {
            restTemplate.postForObject(endpoint, shuffleRequest, Void.class);
        } catch (Exception e) {
            logger.error("Failed to log request: {}", e.getMessage());
        }
    }
}
