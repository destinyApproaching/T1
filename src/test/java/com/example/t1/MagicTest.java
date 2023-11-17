package com.example.t1;

import com.example.t1.exception.BadRequestException;
import com.example.t1.service.MagicService;
import com.example.t1.service.MagicServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MagicTest {
    private final MagicService magicService;

    @Test
    void commonStringTest() {
        assertEquals("\"a\": 5, \"b\": 3, \"c\": 2", magicService.getString("bbbccaaaaa"),
                "Тест для строки (bbbccaaaaa) прошёл.");
    }

    @Test
    void emptyStringTest() {
        assertThrows(BadRequestException.class, () -> magicService.getString(""));
    }

    @Test
    void whitespaceTest() {
        assertThrows(BadRequestException.class, () -> magicService.getString("    "));
    }

    @Test
    void unCommonStringTest() {
        assertEquals("\"a\": 5, \"b\": 3, \"c\": 2", magicService.getString("   bbb   cc      aaaaa    "),
                "Тест для строки (   bbb   cc      aaaaa    ) прошёл.");
    }

    @Test
    void symbolsTest() {
        assertEquals("\"a\": 5, \"#\": 3, \"&\": 2", magicService.getString("###&&aaaaa"),
                "Тест для строки (###&&aaaaa) прошёл.");
    }

    @Test
    void whitespaceWithSymbols() {
        assertEquals("\",\": 4, \"#\": 3, \"&\": 2", magicService.getString("   ##  #&  &,,,  ,   "),
                "Тест для строки (   ##  #&  &,,,  ,   ) прошёл.");
    }
}
