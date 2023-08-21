package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
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
import java.nio.charset.StandardCharsets;
/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper(); // json이기 때문

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData data) throws IOException {
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }
    // @RequestBody에 직접 만든 객체를 저장할 수 있다.
    // HttpEntity , @RequestBody 를 사용하면 HTTP 메시지 컨버터가 HTTP 메시지 바디의 내용을 우리가 원하는 문자나 객체 등으로 변환
    // 1. @RequestBody는 생략 불가
    // 2. 스프링은 @ModelAttribute , @RequestParam 과 같은 해당 애노테이션을 생략시 다음과 같은 규칙을 적용
    // 3. String , int , Integer 같은 단순 타입 = @RequestParam / 나머지 = @ModelAttribute
    // 4. 따라서 이 경우 HelloData에 @RequestBody 를 생략하면 @ModelAttribute 가 적용

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException {
        HelloData helloData = httpEntity.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) throws IOException {
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data; // 응답이 보낸 그대로 출력됨
    }






}
