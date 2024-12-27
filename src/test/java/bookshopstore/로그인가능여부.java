package bookshopstore;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public  class 로그인가능여부{
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeEach
    void setUp(){
        playwright=Playwright.create();
        browser=playwright.chromium().launch();
        context=browser.newContext();
        page= context.newPage();
    }
    @AfterEach
    void tearDown(){
        browser.close();
        playwright.close();
    }
    @Test
    @DisplayName("로그인후 메인 페이지에서 로그아웃 테스트")
    void loginAndLogoutTest(){
        page.navigate("http://bookshopstore.cafe24.com/member01_login.jsp?err=0");
        page.waitForSelector("input[name='checkid']");

        //아이디와 비밀번호 입력
        page.locator("input[name='checkid']").fill("user1");
        page.locator("input[name='checkpw']").fill("Pass11");
        System.out.println("아이디와 비밀번호 확인");
        //로그인버튼 클릭
        page.locator("input[type='submit']").click();
        System.out.println("버튼확인");
        page.waitForURL("http://bookshopstore.cafe24.com/main.jsp");
        page.locator("a:has-text('로그아웃')").click();
        page.waitForSelector("button:has-text('예')");
        //로그아웃 시키기
        page.locator("button:has-text('예')").click();
        page.waitForURL("http://bookshopstore.cafe24.com/main.jsp");
        assertThat(page.url()).contains("main.jsp");

    }

}