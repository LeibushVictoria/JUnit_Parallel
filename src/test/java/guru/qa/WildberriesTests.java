package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.page.WildberriesMainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@ExtendWith({SimpleCallback.class})
public class WildberriesTests {
    private WildberriesMainPage page = new WildberriesMainPage();

    @ValueSource(strings = {
            "куртка",
            "пижама",
            "шапка",
            "шарф"
    })

    @ResourceLock("1")
    @ParameterizedTest(name = "Search with ValueSource: {0}")
    void wildberriesSearchTest(String searchQuery) {
        Configuration.startMaximized = true;
        Selenide.open(WildberriesMainPage.URL);
        page.doSearch(searchQuery).checkResults(searchQuery);
    }

    @ResourceLock("1")
    @Test
    @DisplayName("Search with ValueSource: халат")
    void minimizedWindowTest() {
        Configuration.startMaximized = false;
        Selenide.open(WildberriesMainPage.URL);
        page.doSearch("халат").checkResults("халат");
    }
}
