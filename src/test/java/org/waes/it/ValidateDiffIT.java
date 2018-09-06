package org.waes.it;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ValidateDiffIT {

    public ValidateDiffIT() {
        RestAssured.baseURI = "http://localhost:8081/diffassign/v1/diff";
    }

    /**
     * Scenario: ValidateDiff - When the left side don't have data.
     * Given left side don't have data
     * When ValidateDiff is called
     * Then status code is "200"
     * And "Detail" message is "Left side contains no value."
     * And "Type" is "DIFFERENT_LENGTH"
     */
    @Test(
            testName = "ValidateDiff_TC01",
            description = "ValidateDiff - When the left side don't have data."
    )
    public void givenLeftSideDontHaveData_WhenValidateDiffIsCalled_ThenTypeIsDifferentLengthAndLeftSideContainsNoValue() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/8/right")
                .then().statusCode(200).contentType("application/json")
                .body("right", equalTo("W43s"));

        given()
                .contentType("application/json")
                .when().get("/8")
                .then().statusCode(200).contentType("application/json")
                .body("detail", equalTo("Left side contains no value."))
                .body("type", equalTo("DIFFERENT_LENGTH"));
    }

    /**
     * Scenario: ValidateDiff - When the right side don't have data.
     * Given right side don't have data
     * When ValidateDiff is called
     * Then status code is "200"
     * And "Detail" message is "Right side contains no value."
     * And "Type" is "DIFFERENT_LENGTH"
     */

    @Test(
            testName = "ValidateDiff_TC02",
            description = "ValidateDiff - When the right side don't have data."
    )
    public void givenRightSideDontHaveData_WhenValidateDiffIsCalled_ThenTypeIsDifferentLengthAndRightSideContainsNoValue() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/9/left")
                .then().statusCode(200).contentType("application/json")
                .body("left", equalTo("W43s"));

        given()
                .contentType("application/json")
                .when().get("/9")
                .then().statusCode(200).contentType("application/json")
                .body("detail", equalTo("Right side contains no value."))
                .body("type", equalTo("DIFFERENT_LENGTH"));
    }

    /**
     * Scenario: ValidateDiff - When both sides have same data.
     * Given both same have same data
     * When ValidateDiff is called
     * Then the status code is "200"
     * And the type is "EQUAL"
     */

    @Test(
            testName = "ValidateDiff_TC03",
            description = "ValidateDiff - When both sides have same data."
    )
    public void givenBothsideHavesameData_WhenValidateDiffIsCalled_ThenTheStatusCodeIs200AndTypeIsEqual() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/4/left")
                .then().statusCode(200).contentType("application/json")
                .body("left", equalTo("W43s"));
        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/4/right")
                .then().statusCode(200).contentType("application/json")
                .body("right", equalTo("W43s"));
        given()
                .contentType("application/json")
                .when().get("/4")
                .then().statusCode(200).contentType("application/json")
                .body("type", equalTo("EQUAL"));
    }

    /**
     * Scenario: ValidateDiff -  When the sides have different length values.
     * Given left and right side have values with different lengths
     * When ValidateDiff is called
     * Then the status code is "200"
     * And the type is "DIFFERENT_LENGTH"
     */

    @Test(
            testName = "ValidateDiff_TC04",
            description = "ValidateDiff -  When the sides have different length values."
    )
    public void givenBothSideHaveDiffLengthValues_WhenValidateDiffIsCalled_ThenStatusCodeIs200AndTypeIsDifferentLength() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/5/left")
                .then().statusCode(200)
                .body("left", equalTo("W43s"));
        given()
                .contentType("application/json")
                .body("\"W43sDiF7\"")
                .when().post("/5/right")
                .then().statusCode(200)
                .body("right", equalTo("W43sDiF7"));
        given()
                .contentType("application/json")
                .when().get("/5")
                .then().statusCode(200)
                .body("type", equalTo("DIFFERENT_LENGTH"));
    }

    /**
     * Scenario: ValidateDiff - Both sides with same length but the chars are different.
     * Given both side have same length but the chars are different
     * When ValidateDiff is called
     * Then status code is "200"
     * And the type is "DIFFERENT_CHARS"
     * And detail message is "Values are different on char(s)..."
     */

    @Test(
            testName = "ValidateDiff_TC05",
            description = "ValidateDiff - Both sides with same length but the chars are different."
    )
    public void givenBothSideHaveSameLengthAndDiffChars_WhenValidateDiffIsCalled_ThenTheTypeIsDifferentLengthAndDetailMessageIsDisplayed() {

        given()
                .contentType("application/json")
                .body("\"W43sn3th3RL4ND5Z\"")
                .when().post("/6/left")
                .then().statusCode(200)
                .body("left", equalTo("W43sn3th3RL4ND5Z"));
        given()
                .contentType("application/json")
                .body("\"w43sn3TH3rL4ND5z\"")
                .when().post("/6/right")
                .then().statusCode(200)
                .body("right", equalTo("w43sn3TH3rL4ND5z"));
        given()
                .contentType("application/json")
                .when().get("/6")
                .then().statusCode(200)
                .body("type", equalTo("DIFFERENT_CHARS"))
                .body("detail", equalTo("Values are different on char(s) [0] [6-7] [9] [15]."));

    }

    /**
     * Scenario: ValidateDiff - Sides using same chars but are in a different order.
     * Given both sides have same length and chars but
     * And they are in a different order
     * When ValidateDiff is called
     * Then status code is "200"
     * And the type is "DIFFERENT_CHARS"
     * And detail message is "Values are different on char(s)..."
     */

    @Test(
            testName = "ValidateDiff_TC06",
            description = "ValidateDiff - Sides using same chars but they are are in a different order."
    )
    public void givenBothSidesHaveSameCharsButInDiffOrder_WhenValidateDiffIsCalled_ThenTheTypeIsDifferentLengthAndDetailMessageIsDisplayed() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/6/left")
                .then().statusCode(200)
                .body("left", equalTo("W43s"));
        given()
                .contentType("application/json")
                .body("\"s34W\"")
                .when().post("/6/right")
                .then().statusCode(200)
                .body("right", equalTo("s34W"));
        given()
                .contentType("application/json")
                .when().get("/6")
                .then().statusCode(200)
                .body("type", equalTo("DIFFERENT_CHARS"))
                .body("detail", equalTo("Values are different on char(s) [0-3]."));
    }

    /**
     * Scenario: ValidateDiff - Sides with equal data in a negative ID
     * Given the values are in a negative ID
     * When ValidateDiff is called with the negative ID
     * Then status code is "200"
     * the response should contain "type": "EQUAL"
     * And values are in the respective sides.
     */

    @Test(
            testName = "ValidateDiff_TC07",
            description = "ValidateDiff - Sides with equal data but in a negative ID"
    )
    public void givenTheValueAreInANegId_WhenValidateDiffIsCalledWithTheNegId_ThenTheStatusCodeIs200AndTypeEqual() {

        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/-3/left")
                .then().statusCode(200).contentType("application/json")
                .body("left", equalTo("W43s"));
        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/-3/right")
                .then().statusCode(200).contentType("application/json")
                .body("right", equalTo("W43s"));
        given()
                .contentType("application/json")
                .when().get("/-3")
                .then().statusCode(200).contentType("application/json")
                .body("type", equalTo("EQUAL"));
    }

    /**
     * Scenario: ValidateDiff - Both sides with empty strings.
     * Given both sides have empty strings
     * When ValidateDiff is called
     * Then the status code is "200"
     * And the type is "EQUAL"
     */

    @Test(
            testName = "ValidateDiff_TC07",
            description = "ValidateDiff - Both sides with empty strings."
    )
    public void givenBothSidesHaveEmprtyStrings_WhenValidateDiffIsCalled_ThenTheStatusCodeIs200AndTypeIsEqual() {

        given()
                .contentType("application/json")
                .body("\"\"")
                .when().post("/3/left")
                .then().statusCode(200)
                .body("left", equalTo(""));
        given()
                .contentType("application/json")
                .body("\"\"")
                .when().post("/3/right")
                .then().statusCode(200)
                .body("right", equalTo(""));
        given()
                .contentType("application/json")
                .when().get("/3")
                .then().statusCode(200)
                .body("type", equalTo("EQUAL"));
    }

    /**
     * Scenario: ValidateDiff - When left side has a empty string and right side has a string.
     * Given left side has a empty string
     * And right side has a string
     * When ValidateDiff is called
     * Then status code is "200"
     * And the type is "DIFFERENT_LENGTH"
     */

    @Test(
            testName = "ValidateDiff_TC08",
            description = "ValidateDiff - When left side has a empty string and right side has a string."
    )
    public void givenLeftSideHasAEmptyString_WhenValidateDiffIsCalled_ThenStatusCodeIs200AndTypeIsDifferentLength() {

        given()
                .contentType("application/json")
                .body("\"\"")
                .when().post("/3/left")
                .then().statusCode(200)
                .body("left", equalTo(""));
        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/3/right")
                .then().statusCode(200)
                .body("right", equalTo("W43s"));
        given()
                .contentType("application/json")
                .when().get("/3")
                .then().statusCode(200)
                .body("type", equalTo("DIFFERENT_LENGTH"));
    }

    /**
     * Scenario: ValidateDiff - When right side has a empty string and left side has a string.
     * Given right side has a empty string
     * And left side has a string
     * When ValidateDiff is called
     * Then status code is "200"
     * And the type is "DIFFERENT_LENGTH"
     */

    @Test(
            testName = "ValidateDiff_TC09",
            description = "ValidateDiff - When right side has a empty string and right side has a string."
    )
    public void givenRightSideHasAEmptyString_WhenValidateDiffIsCalled_ThenStatusCodeIs200AndTypeIsDifferentLength() {

        given()
                .contentType("application/json")
                .body("\"\"")
                .when().post("/3/right")
                .then().statusCode(200)
                .body("right", equalTo(""));
        given()
                .contentType("application/json")
                .body("\"W43s\"")
                .when().post("/3/left")
                .then().statusCode(200)
                .body("left", equalTo("W43s"));
        given()
                .contentType("application/json")
                .when().get("/3")
                .then().statusCode(200)
                .body("type", equalTo("DIFFERENT_LENGTH"));
    }

    /**
     * Scenario: ValidateDiff - using a CHAR as id
     * Given the endpoint accepts long as ID
     * When ValidateDiff is called with a char as Id
     * Then the response Header should be: "404"
     * And body don't have any side
     */

    @Test(
            testName = "ValidateDiff_TC10",
            description = "Calling ValidateDiffIT using a CHAR as id"
    )
    public void givenTheEndpointAcceptsLongAsID_WhenValidateDiffIsCalledWithACharAsId_ThenTheStatusCodeIs404() {

        given()
                .contentType("")
                .when().get("/A")
                .then().statusCode(400);
    }

    /**
     * Scenario: ValidateDiff - Checking if validation is case sensitive
     * Given method comparison is case sensitive
     * When ValidateDiff is called with a char as Id
     * Then status code is "200"
     * And the type is "DIFFERENT_CHARS"
     * And detail message is "Values are different on char(s) [0] [15]."
     */
    @Test(
            testName = "ValidateDiff_TC11",
            description = "ValidateDiff - First and Last char are different."
    )
    public void givenFirstAndLastCharsAreDiff_WhenValidateDiffIsCalled_ThenTheTypeIsDifferentCharsAndDetailMessageIsDisplayed() {

        given()
                .contentType("application/json")
                .body("\"W0gAXBzAWG9Ab3Igc2l0IAAGFtZXQsIGNvbnNlYA\"")
                .when().post("/6/left")
                .then().statusCode(200).contentType("application/json")
                .body("left", equalTo("W0gAXBzAWG9Ab3Igc2l0IAAGFtZXQsIGNvbnNlYA"));
        given()
                .contentType("application/json")
                .body("\"bGFAb3JAcyBAaXNpIHV0IGFsaXF1aXAgAAAZXggA\"")
                .when().post("/6/right")
                .then().statusCode(200).contentType("application/json")
                .body("right", equalTo("bGFAb3JAcyBAaXNpIHV0IGFsaXF1aXAgAAAZXggA"));
        given()
                .contentType("application/json")
                .when().get("/6")
                .then().statusCode(200).contentType("application/json")
                .body("type", equalTo("DIFFERENT_CHARS"))
                .body("detail", equalTo("Values are different on char(s) [0-2] [4-6] [8-10] [12-18] [21-38]."));
    }

    /**
     * Scenario: GetAllSides - Calling getAll sides must return a Json
     * Given the getAllSides method returns a Json
     * And may be empty or not.
     * When getAllSides is called
     * Then status code is "200"
     * And the Json body may have values and it could be empty
     */

    @Test(
            testName = "GetAllSides_TC12",
            description = "GetAllSides - Calling getAll sides must return a Json"
    )
    public void givenGetAllSidesReturnsAJson_WhengetAllSidesIsCalled_ThenTheReturnsAJson() {

        given()
                .contentType("application/json")
                .when().get("")
                .then().statusCode(200).contentType("application/json");
    }
}
