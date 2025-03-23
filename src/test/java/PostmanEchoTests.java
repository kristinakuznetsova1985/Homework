import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Полные тесты API Postman Echo
 * с контролируемым логированием и форматированным выводом
 */
public class PostmanEchoTests {

    @BeforeAll
    public static void setup() {
        // Настройка RestAssured
        RestAssured.baseURI = "https://postman-echo.com";

        // Конфигурация логирования (логи только при ошибках)
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig()
                        .enablePrettyPrinting(true)
                        .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL));

        printBanner("НАЧАЛО ТЕСТИРОВАНИЯ API");
    }

    @AfterAll
    public static void tearDown() {
        printBanner("ТЕСТИРОВАНИЕ ЗАВЕРШЕНО");
    }

    // ==================== ТЕСТОВЫЕ МЕТОДЫ ====================

    @Test
    @DisplayName("GET запрос с параметрами")
    void testGetRequest(TestInfo testInfo) {
        printTestHeader(testInfo);

        // Настраиваемые параметры
        String paramName = "testParam";
        String paramValue = "123";

        Response response = RestAssured.given()
                .when()
                .get("/get?{name}={value}", paramName, paramValue);

        assertEquals(200, response.getStatusCode());
        assertEquals(paramValue, response.jsonPath().getString("args." + paramName));

        printSuccess("GET параметры корректно обработаны");
    }

    @Test
    @DisplayName("POST с текстовым телом")
    void testPostRawText(TestInfo testInfo) {
        printTestHeader(testInfo);

        String requestBody = "Тестовое сообщение: кириллица ✓ ♥";

        Response response = RestAssured.given()
                .contentType("text/plain; charset=UTF-8")
                .body(requestBody)
                .when()
                .post("/post");

        assertEquals(200, response.getStatusCode());
        assertTrue(response.body().asString().contains(requestBody));

        printSuccess("Текст успешно возвращен сервером");
    }

    @Test
    @DisplayName("POST с form-data")
    void testPostFormData(TestInfo testInfo) {
        printTestHeader(testInfo);

        Response response = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("username", "test_user")
                .formParam("password", "qwerty123")
                .when()
                .post("/post");

        assertEquals(200, response.getStatusCode());
        assertEquals("test_user", response.jsonPath().getString("form.username"));
        assertEquals("qwerty123", response.jsonPath().getString("form.password"));

        printSuccess("Form-data успешно обработана");
    }

    @Test
    @DisplayName("PUT запрос")
    void testPutRequest(TestInfo testInfo) {
        printTestHeader(testInfo);

        String jsonBody = "{\"id\":1,\"active\":true}";

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .put("/put");

        assertEquals(200, response.getStatusCode());
        assertEquals(1, response.jsonPath().getInt("json.id"));
        assertTrue(response.jsonPath().getBoolean("json.active"));

        printSuccess("Данные успешно обновлены");
    }

    @Test
    @DisplayName("DELETE запрос")
    void testDeleteRequest(TestInfo testInfo) {
        printTestHeader(testInfo);

        String resourceId = "item-789";

        Response response = RestAssured.given()
                .when()
                .delete("/delete?id={id}", resourceId);

        assertEquals(200, response.getStatusCode());
        assertEquals(resourceId, response.jsonPath().getString("args.id"));

        printSuccess("Ресурс успешно удален");
    }

    // ==================== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ====================

    private static void printBanner(String text) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║  " + String.format("%-34s", text) + "║");
        System.out.println("╚════════════════════════════════════╝");
    }

    private static void printTestHeader(TestInfo testInfo) {
        System.out.println("\n──────────────────────────────────");
        System.out.println("🚀 " + testInfo.getDisplayName());
        System.out.println("──────────────────────────────────");
    }

    private static void printSuccess(String message) {
        System.out.println("[✅] " + message);
    }

    private static void printError(String message) {
        System.out.println("[❌] " + message);
    }
}