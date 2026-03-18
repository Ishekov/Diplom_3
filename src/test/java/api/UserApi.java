package api;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static utils.Constant.*;

public class UserApi {
    private String accessToken;
    private final Filter logRequestFilter = new RequestLoggingFilter();
    private final Filter logResponseFilter = new ResponseLoggingFilter();

    private RequestSpecification getReqSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(Authorization authorization){
        return given()
                .filters(logRequestFilter, logResponseFilter)
                .spec(getReqSpec())
                .body(authorization)
                .when()
                .post(LOGIN_PATH)
                .then();
    }
    @Step("Авторизоваться и сохранить токен")
    public void loginAndSaveToken(Authorization authorization) {
        ValidatableResponse resp = loginUser(authorization);
        String token = resp.extract().path("accessToken");
        if (token != null) {
            this.accessToken = token;
        }
    }
    @Step("Создать пользователя")
    public ValidatableResponse createUser(UserCreate data) {
        return given().log().all()
                .filters(logRequestFilter, logResponseFilter)
                .spec(getReqSpec())
                .body(data)
                .when()
                .post(CREATE_USER)
                .then();
    }

    @Step("Удалить пользователя")
    public ValidatableResponse deleteUser() {
        if (accessToken == null || accessToken.isEmpty()) {
            return null;
        }
        return given()
                .filters(logRequestFilter, logResponseFilter)
                .spec(getReqSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(USER_PATH)
                .then();
    }
}
