package org.grida;

import lombok.extern.slf4j.Slf4j;
import org.grida.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class TestController {

    @GetMapping("/test/1")
    public ApiResponse test1() {
        return ApiResponse.created(1);
    }

    @GetMapping("/test/2")
    public ApiResponse test2() {
        throw new IllegalArgumentException("asd");
//        return ApiResponse.created(1);
    }
}
