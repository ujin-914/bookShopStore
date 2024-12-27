package bookshopstore;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@UsePlaywright(HeadlessChromeOptions.class)
public class 상품구매가능여부확인 {

    @DisplayName("상품 구매 가능여부 확인")
    @Test
    void checkBookBuyAvailable(Page page) {
        // 페이지 열기
        page.navigate("http://bookshopstore.cafe24.com/allListPage.jsp");
        page.waitForLoadState();

        // 찾고자 하는 도서명
        String target = "JAVA의 정석";  // 정확한 텍스트를 사용하세요.

        // 도서가 존재하는지 확인 (텍스트로 도서 찾기)
        Locator bookLocator = page.locator("text=" + target);
        System.out.println(page.content());

        // 도서가 목록에 있는지 확인
        if (bookLocator.count() > 0) {
            System.out.println(target + " 도서가 목록에 있습니다.");
        } else {
            System.out.println(target + " 도서는 목록에 없습니다.");
        }
    }

}


