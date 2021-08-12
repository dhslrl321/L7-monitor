package com.example.l7monitor.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TotalEntityTest {

    @Test
    void create() {
        long id = 1004L;
        String ip = "192.168.0.1";
        int resCode = 200;

        String method = "GET";
        String uri = "/images/pharming_img.png";
        String protocol = "HTTP/1.1";
        int resDataSize = 53529;
        String referer = "https://google.com";
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko";
        LocalDateTime time = LocalDateTime.now();
        String userid = "-";

        Total total = Total.builder()
                .id(id)
                .ip(ip)
                .userid(userid)
                .timestamp(time)
                .method(method)
                .uri(uri)
                .protocol(protocol)
                .resCode(resCode)
                .resDataSize(resDataSize)
                .referer(referer)
                .userAgent(userAgent)
                .build();

        assertAll(
                () -> assertEquals(id, total.getId()),
                () -> assertEquals(ip, total.getIp()),
                () -> assertEquals(userid, total.getUserid()),
                () -> assertEquals(resCode, total.getResCode()),
                () -> assertEquals(uri, total.getUri()),
                () -> assertEquals(protocol, total.getProtocol()),
                () -> assertEquals(referer, total.getReferer()),
                () -> assertEquals(userAgent, total.getUserAgent()),
                () -> assertEquals(resDataSize, total.getResDataSize()),
                () -> assertEquals(method, total.getMethod()),
                () -> assertEquals(time, total.getTimestamp())
        );
    }
}