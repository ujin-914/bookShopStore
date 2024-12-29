package bookshopstoreSimpleVersion;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class 내프로젝트2Test {
    static Playwright playwright;
    static Browser browser;
    Page page;

    @BeforeAll
    static void setUpAll(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    void setUp(){
        page = browser.newPage();
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


    @DisplayName("회원가입 페이지 테스트")
    @Test
    void createMemberDetail() {
        page.navigate("http://bookshopstore.cafe24.com/member03_create.jsp?err=0");
        assertTrue(page.url().contains("member03_create.jsp"), "회원가입 페이지로 이동하지 않았습니다.");
        System.out.println("회원가입페이지 이동확인");
        page.waitForSelector("input[name='newname']");

        // 회원가입 폼에 데이터 입력
        page.locator("input[name='newname']").fill("김회원");
        page.locator("input[name='newid']").fill("user40");
        page.locator("input[name='newpw']").fill("Pass400");
        page.locator("input[name='newpwtwo']").fill("Pass400");
        page.locator("input[name='newbirth']").fill("1990-05-05");
        page.locator("select[name='phoneA']").selectOption("010");
        page.locator("input[name='phoneB']").fill("2222");
        page.locator("input[name='phoneC']").fill("3333");

        // 회원가입 제출
        page.locator("input[type='submit']").click();

        // 회원가입이 성공하고 createPro.jsp로 리디렉션되는지 확인
        assertTrue(page.url().contains("createPro.jsp"), "회원가입 후 리디렉션되지 않았습니다.");
        System.out.println("회원가입성공");
    }

}
