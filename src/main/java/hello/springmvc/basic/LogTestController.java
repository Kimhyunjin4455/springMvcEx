package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j // 롬복이 자동으로 private final Logger log = LoggerFactory.getLogger(getClass()); 삽입
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        System.out.println("name = " + name);

        // 로그처리는 "trace log="+ name 식으로 하지 않음 -> 연산을 통해 쓸모없는 리소스 사용됨
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); // 디버그 할때(개발서버) 사용
        log.info(" info log={}", name); // " 현재 시간 - info 프로세서 id - 현재 실행한 스레드명 - 컨트롤러명 - 메세지 " 출력됨, 중요(비즈니스) 정보 필요시
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
        // 문자 리턴시 @Controller 이용하면 뷰 이름으로 반환 But @RestController 이용하면 Http 메세지 바디에 바로 입력
    }
}
