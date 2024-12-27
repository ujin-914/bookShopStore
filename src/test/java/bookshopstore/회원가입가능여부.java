package bookshopstore;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@UsePlaywright
public class 회원가입가능여부 {

    @DisplayName("회원가입 가능여부")
    @Test
    void createData(Page page) {
        page.navigate("http://bookshopstore.cafe24.com/member03_create.jsp?err=0");
        page.waitForSelector("input[name='newid']");
        page.locator("input[name='newid']").fill("user20");
        page.locator("input[name='newpw']").fill("Pass200");
        page.locator("input[name='newpwtwo']").fill("Pass200");
        page.locator("input[name='newbirth']").fill("1990-05-05");
        page.locator("select[name='phoneA']").selectOption("010");
        page.locator("input[name='phoneB']").fill("2222");
        page.locator("input[name='phoneC']").fill("3333");
        page.locator("input[type='submit']").click();
        System.out.println("회원가입성공");

    }
}