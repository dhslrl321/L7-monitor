package com.example.l7monitor.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AbnormalEntityTest {

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
        MalCode malCode = new MalCode(1, "XSS");


        Abnormal abnormal = Abnormal.builder()
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
                .malCode(malCode)
                .build();

        assertAll(
                () -> assertEquals(id, abnormal.getId()),
                () -> assertEquals(ip, abnormal.getIp()),
                () -> assertEquals(userid, abnormal.getUserid()),
                () -> assertEquals(resCode, abnormal.getResCode()),
                () -> assertEquals(uri, abnormal.getUri()),
                () -> assertEquals(protocol, abnormal.getProtocol()),
                () -> assertEquals(referer, abnormal.getReferer()),
                () -> assertEquals(userAgent, abnormal.getUserAgent()),
                () -> assertEquals(resDataSize, abnormal.getResDataSize()),
                () -> assertEquals(method, abnormal.getMethod()),
                () -> assertEquals(time, abnormal.getTimestamp()),
                () -> assertEquals(malCode, abnormal.getMalCode())
        );
    }

}