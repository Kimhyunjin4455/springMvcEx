package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response)throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");

    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity)throws IOException {
        // 스프링이 보고 http 바디에 있는 것을 문자로 변환하여 대입
        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok"); // 첫번째 파라미터로 바디 메세지 넣을 수 있음

    }

    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(@RequestBody String messageBody)throws IOException {

        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok"); // 첫번째 파라미터로 바디 메세지 넣을 수 있음
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4R")
    public String requestBodyStringV4R(@RequestBody String messageBody)throws IOException {

        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
