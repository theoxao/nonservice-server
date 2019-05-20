package com.example.service;

import com.theoxao.annotations.EmbeddedService;
import org.springframework.stereotype.Service;

/**
 * @author theo
 * @date 2019/5/20
 */
@EmbeddedService
public class FooService {
    public String duplicate(String foo) {
        return foo + foo;
    }
}
