package bookshopstore;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import javax.xml.crypto.dsig.keyinfo.PGPData;

public class 프로젝트제목확인 {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    public void setUp(){
        playwright=Playwright.create();
        browser=playwright.chromium().launch();
        page=browser.newPage();
    }
    @AfterEach
    public void tearDown(){
        browser.close();
        playwright.close();
    }

    @Test
    void showPageTitle(){
        page.navigate("http://bookshopstore.cafe24.com");
        String title= page.title();

        Assertions.assertTrue(title.contains("Insert title here"));

    }

}
