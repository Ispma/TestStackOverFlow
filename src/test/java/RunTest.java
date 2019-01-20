import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunTest extends StartTest
{

    @Before
    public void firstStep() throws InterruptedException
    {
        //1) Перейти на внешний ресурс: http://stackoverflow.com/
        one();
    }

    @Test

    public void firstTest() throws InterruptedException
    {
        System.out.println("First Test");
        //2) В строку поиска ввести значение «webdriver».
        send("//input[contains(@class, 's-input js-search-field')]", "webdriver");
        //Кликаем на поиск
        click("//button[contains(@class, 's-btn s-btn__primary s-btn__icon btn-topbar-primary js-search-submit')]");
        //3) Проверить, что в каждом результате представлено слово WebDriver.
        search("//div[contains(@class, 'question-summary search-result')]", ".//h3[contains(@title, '')]", "WebDriver");
        //4) Войти в каждое обсуждения из выборки и убедиться, что перешли именно в эту тему (проверить заголовок обсуждения).
        check();
    }

    @Test

    public void secondTest() throws InterruptedException
    {
        //5) Перейти в раздел Tags
        clickById( "nav-tags" );
        //6) В строку поиска ввести значение – webdriver.
        send("//input[contains(@id, 'tagfilter')]", "webdriver");
        // Убедиться, что в результате присутствуют элементы содержащее слово webdriver.
        search("//div[contains(@class, 'grid-layout--cell tag-cell')]", ".//a[contains(@class, 'post-tag')]", "webdriver");
        //7) Найти в результатах тэг по точному совпадению поискового запроса кликнуть по нему
        clickByLink("webdriver");
        // проверить, что после перехода отображаются обсуждения помеченные тэгом webdriver.
        System.out.println("last step");
        doubleSearch("//div[contains(@class, 'question-summary')]", ".//a[contains(@class, 'post-tag')]", "webdriver");
    }

    @After
    public void exit()
    {
        //Выход из браузера
        close();
    }
}
