package com.example.t1.controller;

import com.example.t1.service.MagicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/T1")
@RequiredArgsConstructor
public class MagicController {
    private final MagicService magicService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/string={string}")
    public String getString(@PathVariable String string) {
        return magicService.getString(string);
    }
}
