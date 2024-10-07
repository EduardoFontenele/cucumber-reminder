package com.cucumb.client;

import com.cucumb.dto.MakerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8082/my-endpoint", name = "mockClient")
@Service
public interface MockClient {
    @GetMapping
    MakerDTO getMockResponse();
}
