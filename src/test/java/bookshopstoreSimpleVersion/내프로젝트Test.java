package bookshopstoreSimpleVersion;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class 내프로젝트Test {
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
        page= browser.newPage();
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
    //공통메서드
   /* void login(String checkid, String checkpw){
        page.navigate("http://bookshopstore.cafe24.com/member01_login.jsp?err=0");
        page.fill("input[type='text']",checkid);
        page.fill("input[type='password']",checkpw);
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("로그인")).click();
    }*/
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @Nested
    @DisplayName("1. 메인페이지 테스트")
    class HomePageTest{
        @Test
        @DisplayName("1.1 메인 페이지 로딩 확인")
        @Order(1)
        void testHomePageLoad(){
            assertEquals("Insert title here",page.title(),"페이지 제목이 일치하지 않음");
        }
        @Test
        @DisplayName("1.2 메인페이지 베너 확인")
        @Order(2)
        void testHomePageImage() {
            assertTrue(page.locator("img[alt='중앙 메인 이미지']").isVisible(), "베너 이미지 보이지않음");
        }
    }
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @Nested
    @DisplayName("2. 도서 전체 페이지 테스트")
    class AllbookTest{
        @BeforeEach
        void bookPage(){
            page.navigate("http://bookshopstore.cafe24.com/allListPage.jsp");
        }
        @Test
        @DisplayName("2.1 전체 도서 사진,정보 로딩 확인")
        @Order(1)
        void testBookPage(){
            assertTrue(page.locator("a").count() > 0, "도서 사진 보이지 않음");
            assertTrue(page.locator("p").count()>0,"도서 정보 비어있음");

        }
        @Test
        @DisplayName("2.2 도서 품절여부 확인")
        @Order(2)
        void testSoldOut(){
            assertEquals(3, page.locator("p font:has-text('품절')").count(), "품절된 제품 없음");
        }
        @Test
        @DisplayName("2.3 도서 상세페이지로 이동")
        @Order(3)
        void testDetailBook() {
            page.locator("a[href*='bookNumber']").first().click();
            page.waitForSelector("td font:has-text('총알배송/택배배송')");

            String nextUrl = page.url();
            assertTrue(nextUrl.contains("bookNumber"), "도서 상세페이지로 이동하지 않았습니다. 현재페이지:" + nextUrl);
            System.out.println("도서상세정보 보임");
            if (nextUrl.contains("bookNumber")) {
                System.out.println("넘어감");
            }
        }
    }
}
