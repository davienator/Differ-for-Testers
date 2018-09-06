package org.waes.it;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddSideValueIT {

    public AddSideValueIT() {
        RestAssured.baseURI = "http://localhost:8081/diffassign/v1/diff";
    }

    /**
     * Scenario: AddSideValue - Adding a base64 encoded value to left side.
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * When I add the value to the left side
     * Then the status code is "200"
     * And the added value returns in the left side
     */

    @Test(
            testName = "AddSideValue_TC01",
            description = "AddSideValue - Adding a base64 encoded value to left side."
    )
    public void givenAEncoded64ValueInJonBody_WhenIAddItToLeftSide_ThenAddedValueReturnsInLeftSide() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/3/left")
                .then().statusCode(200).contentType("application/json")
                .body("left", equalTo("W43s"));
    }

    /**
     * Scenario: AddSideValue - Adding a base64 encoded value to right side.
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * When I add the value to the right side
     * Then the status code is "200"
     * And the added value returns in right left side
     */

    @Test(
            testName = "AddSideValue_TC02",
            description = "AddSideValue - Adding a base64 encoded value to right side."
    )
    public void givenAEncoded64ValueInJonBody_WhenIAddItToRightSide_ThenAddedValueReturnsInRightSide() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/3/right")
                .then().statusCode(200).contentType("application/json")
                .body("right", equalTo("W43s"));
    }

    /**
     * Scenario: AddSideValue - Adding a base64 encoded value to an invalid side.
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * When I add the value to an invalid side
     * Then status code is "501 Not Implemented"
     * And the error message is: "This side is not supported, please use either 'left' or 'right'."
     */

    @Test(
            testName = "AddSideValue_TC03",
            description = "AddSideValue - Adding a base64 encoded value to an invalid side."
    )
    public void givenAEncoded64ValueInJonBody_WhenIAddItToAInvalidSide_ThenTheStatusCodeIsNotImplemented() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/3/center")
                .then().statusCode(501).contentType("application/json")
                .body("errorMessage", equalTo("This side is not supported, please use either 'left' or 'right'."));
    }

    /**
     * Scenario: Calling AddSideValue without a side in the endpoint.
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * When I try to add a value without ID in the endpoint
     * Then the status code is "405 Method Not Allowed"
     */

    @Test(
            testName = "AddSideValue_TC04",
            description = "Calling AddSideValue without a side in the endpoint."
    )
    public void givenAEncoded64ValueInJonBody_WhenICallItWithoutASide_ThenTheStatusCodeIsMethodNotAllowed() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/3/")
                .then().statusCode(405);
    }

    /**
     * Scenario: AddSideValue - Checking if side is Case Sensitive.
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * When I call it using Left as side
     * Then the status code is "501 Not Implemented"
     */

    @Test(
            testName = "AddSideValue_TC05",
            description = "AddSideValue - Checking if side is Case Sensitive."
    )
    public void givenAEncoded64ValueInJonBody_WhenICallItUsingUsingAUppercaseLetter_ThenTheStatusCodeIsNotImplemented() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/3/Left")
                .then().statusCode(501).contentType("application/json");
    }

    /**
     * Scenario: Calling AddSideValue without ID in the endpoint
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * When I call it without ID in the endpoint
     * Then the status code is "404 Not Found"
     */

    @Test(
            testName = "AddSideValue_TC06",
            description = "Calling AddSideValueIT without ID in the endpoint"
    )
    public void givenAEncoded64ValueInJonBody_WhenICallItWithoutAId_ThenTheStatusCodeIsNotFound() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/\"\"/left")
                .then().statusCode(404);
    }

    /**
     * Scenario: AddSideValue - Using a CHAR as ID.
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * And the endpoint requires a LONG as ID
     * When I call it using a CHAR as ID
     * Then the status codes is "404 Not Found"
     */

    @Test(
            testName = "AddSideValue_TC07",
            description = "AddSideValue - Using a CHAR as ID."
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingCharAsId_ThenTheStatusCodeIsNotFound() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/A/left")
                .then().statusCode(404);
    }

    /**
     * Scenario: AddSideValue - Using a DOUBLE as ID
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * And the endpoint requires a LONG as ID
     * When I call it using a DOUBLE as ID
     * Then the status code is "404 Not Found"
     */

    @Test(
            testName = "AddSideValue_TC08",
            description = "AddSideValue - Using a DOUBLE as ID"
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingDoubleAsId_ThenTheStatusCodeIsNotFound() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/1.1/left")
                .then().statusCode(404);
    }

    /**
     * Scenario: AddSideValue - Using a negative number as ID
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * And the endpoint requires a LONG as ID
     * When I call it using a negative number as ID
     * Then the status code is "200"
     * And the added value returns in the left side
     */

    @Test(
            testName = "AddSideValue_TC09",
            description = "AddSideValue - Using a negative number as ID"
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingNegativeNumberAsId_ThenAddedValueReturnsInLeftSide() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/-1/left")
                .then().statusCode(200).contentType("application/json")
                .body("left", equalTo("W43s"));
    }

    /**
     * Scenario: AddSideValueIT - Using a ID that exceeds long maximum limit.
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * And the endpoint requires a LONG as ID
     * When I call it using an ID that exceeds long maximum limit.
     * Then status code is "404 Not Found"
     */

    @Test(
            testName = "AddSideValue_TC10",
            description = "AddSideValueIT - Using a ID that exceeds long maximum limit."
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingAIdThatExceedsLongLimit_ThenTheStatusCodeIsNotFound() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/9223372036854775887/left")
                .then().statusCode(404);
    }

    /**
     * Scenario: AddSideValueIT - Using a ID that exceeds long maximum negative limit.
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * And the endpoint requires a LONG as ID
     * When I call it using an ID that exceeds long maximum negative limit.
     * Then status code is "404 Not Found"
     */

    @Test(
            testName = "AddSideValue_TC11",
            description = "AddSideValueIT - Using a ID that exceeds long maximum limit."
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingAIdThatExceedsLongNegativeLimit_ThenTheStatusCodeIsNotFound() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/-9223372036854775887/left")
                .then().statusCode(404);
    }

    /**
     * Scenario: AddSideValue - Using a value that is not in string format
     * Given I am calling AddSideValue with a base64 encoded value in json body
     * When I call it using a value that is not in a string format
     * Then status code is "415 Unsupported Media Type"
     * And the errorMessage is: "Value in request body must be in JSON format."
     */

    @Test(
            testName = "AddSideValue_TC12",
            description = "AddSideValue - Using a value that is not in string format"
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingAValueThatIsNotInStringFormat_ThenTheStatusCodeIsUnsupportedMediaType() {

        given()
                .contentType("application/json")
                .body("W43s")
                .when().post("/3/left")
                .then().statusCode(415)
                .contentType("application/json")
                .body("errorMessage", equalTo("Value in request body must be in JSON format."));
    }

    /**
     * Scenario: AddSideValue - Using an empty string in the json body
     * Given I am calling AddSideValue using an empty string value in json body
     * When I call it to add in left side
     * Then the status code is "200"
     * And the left value is " "
     */

    @Test(
            testName = "AddSideValue_TC13",
            description = "AddSideValue - Using an empty string in the json body"
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingAnEmptyStringInJsonBody_ThenTheEmptyStringIsAdded() {

        given()
                .contentType("application/json")
                .body("\"\"")
                .when().post("/3/left")
                .then().statusCode(200)
                .contentType("application/json")
                .body("left", equalTo(""));
    }

    /**
     * Scenario: AddSideValue - Using using padding in the json body value
     * Given I am calling AddSideValue using padding in the json body value
     * When I call it to add the padded value to left side
     * Then the status code is "200"
     * And the left value is "W4s="
     */

    @Test(
            testName = "AddSideValue_TC14",
            description = "AddSideValue - Using a padded value in the json body."
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingPaddingInJsonBodyValue_ThenThePaddedValueIsAdded() {

        given()
                .contentType("application/json")
                .body("\"W4s=\"")
                .when().post("/3/left")
                .then().statusCode(200)
                .contentType("application/json")
                .body("left", equalTo("W4s="));
    }

    /**
     * Scenario: AddSideValue - Using a slash in the json body value
     * Given I am calling AddSideValue with a slash in json body string
     * When I call it to add to left side
     * Then status code is "200"
     * And the left value is "W4s\"
     */

    @Test(
            testName = "AddSideValue_TC15",
            description = "AddSideValue using a slash in the json body value."
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingAnValueWithSlashInJsonBody_ThenTheValueWithSlashIsAdded() {

        given()
                .contentType("application/json")
                .body("\"W43\\\"")
                .when().post("/3/left")
                .then().statusCode(200)
                .contentType("application/json")
                .body("left", equalTo("W43\\"));
    }

    /**
     * Scenario: AddSideValue - Using a plus in the json body value
     * Given I am calling AddSideValue with a plus in json body string
     * When I call it to add to left side
     * Then status code is "200"
     * And the left value is "W4s+"
     */

    @Test(
            testName = "AddSideValue_TC16",
            description = "AddSideValue - Using a plus in the json body value."
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingAnValueWithPlusInJsonBody_ThenTheValueWithPlusIsAdded() {

        given()
                .contentType("application/json")
                .body("\"W43+\"")
                .when().post("/3/left")
                .then().statusCode(200)
                .contentType("application/json")
                .body("left", equalTo("W43+"));
    }

    /**
     * Scenario: AddSideValue - Using a string that contains less than 4 chars
     * Given I am calling AddSideValue with a string that is less than 4 chars in json body
     * When I call it to add to left side
     * Then the status code is "415 Unsupported Media Type"
     * And the errorMessage is "Data in body not Base64 formatted."
     */

    @Test(
            testName = "AddSideValue_TC17",
            description = "AddSideValue - Using a string that contains less than 4 chars"
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingAnValueThatIsLessThan4CharsInJsonBody_ThenTheStatusCodeIsUnsupportedMediaType() {

        given()
                .contentType("application/json")
                .body("\"W43\"")
                .when().post("/3/left")
                .then().statusCode(415)
                .contentType("application/json")
                .body("errorMessage", equalTo("Data in body not Base64 formatted."));
    }

    /**
     * Scenario: AddSideValue - Using a string in json format
     * Given I am calling AddSideValue with a string that is json format
     * When I call it to add to left side
     * Then status code is "200"
     * And the left value is "W4s+"
     */

    @Test(
            testName = "AddSideValue_TC18",
            description = "AddSideValue - Using a string in json format"
    )

    public void givenAEncoded64ValueInJonBody_WhenICallItUsingAnValueThatIsInJsonBodyFormatInJsonBody_ThenTheValueIsAdded() {

        given()
                .contentType("application/json")
                .body("{\"String\": \"W43s\"}")
                .when().post("/3/left")
                .then().statusCode(200)
                .contentType("application/json")
                .body("left", equalTo("W43s"));
    }
}