package bookshopstoreSimpleVersion;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class 내프로젝트3Test {
    static Playwright playwright;
    static Browser browser;
    Page page;
    @BeforeAll
    static void setUpAll(){
        playwright=Playwright.create();
        browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }
    @BeforeEach
    void setUp(){
        // 로그인 후 세션 저장
        BrowserContext context = browser.newContext();
        page = context.newPage();

        // 먼저 메인 페이지로 이동
        page.navigate("http://bookshopstore.cafe24.com/main.jsp");
    }
    @AfterEach
    void tearDown(){
        page.close();
    }
    @AfterAll
    static void tearDownAll(){
        browser.close();
        playwright.close();
    }
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @Nested
    @DisplayName("1.로그인")
    class LoginTest {
        @Test
        @DisplayName("1.1 로그인페이지로 이동")
        @Order(1)
        void goLoginPage() {
            page.navigate("http://bookshopstore.cafe24.com/member01_login.jsp?err=0");
            boolean LoginTextVisible = page.locator("h2:has-text('로그인')").isVisible();
            assertTrue(LoginTextVisible, "로그인화면으로 이동하지 않음");
            System.out.println("로그인페이지 이동확인");
        }

        @Test
        @DisplayName("1.2 로그인 가능여부 확인")
        @Order(2)
        void loginCheck() {
            page.navigate("http://bookshopstore.cafe24.com/member01_login.jsp?err=0");
            page.waitForLoadState();
            page.waitForSelector("td:has-text('아이디')");
            page.locator("input[name='checkid']").fill("user1");
            page.locator("input[name='checkpw']").fill("Pass11");
            page.locator("input[type='submit']").click();
            assertTrue(page.url().contains("main.jsp"), "로그인실패");
            System.out.println("로그인성공");
            //로그인세션 저장

        }


        @Test
        @DisplayName("1.3 로그아웃 가능여부 확인")
        @Order(3)
        void logout() {
            // 로그인 후 페이지로 이동
            page.navigate("http://bookshopstore.cafe24.com/member01_login.jsp?err=0");
            page.waitForSelector("input[name='checkid']").fill("user1");
            page.locator("input[name='checkpw']").fill("Pass11");
            page.locator("input[type='submit']").click();

            // 로그아웃 버튼이 보이는지 확인
            boolean logoutButtonVisible = page.locator("a:has-text('로그아웃')").isVisible();
            assertTrue(logoutButtonVisible, "로그인 후 로그아웃 버튼이 보이지 않습니다.");

            // 로그아웃 클릭
            page.locator("a:has-text('로그아웃')").click();
            page.waitForSelector("button:has-text('예')");
            page.locator("button:has-text('예')").click();

            // 로그아웃 후 로그인 버튼이 보이는지 확인
            boolean loginButtonVisible = page.locator("a:has-text('로그인')").isVisible();
            assertTrue(loginButtonVisible, "로그아웃 후 로그인 버튼이 보이지 않습니다.");
            System.out.println("로그아웃 성공");

        }
    }
}
