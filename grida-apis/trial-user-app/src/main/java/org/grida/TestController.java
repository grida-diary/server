package org.grida;

import lombok.extern.slf4j.Slf4j;
import org.grida.response.ApiResponse;
import org.grida.util.RequestUserId;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/test/3")
    public ApiResponse test3(@RequestUserId Long userId,
                             @RequestBody String name) {
        System.out.println(name);
        return ApiResponse.created(userId);
    }

    @GetMapping("/test/4")
    public ApiResponse test4(@RequestUserId Long userId) {
        return ApiResponse.created(userId);
    }

    @GetMapping("/test/5")
    public ApiResponse test5(@RequestUserId Long userId) {
        return ApiResponse.created(userId);
    }

    @GetMapping("/test/6")
    public ApiResponse test6(@RequestUserId Long userId) {
        return ApiResponse.created(userId);
    }
}
