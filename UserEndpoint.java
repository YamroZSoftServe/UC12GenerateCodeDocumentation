package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.user.UserDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
 * This class represents the endpoint for managing users using REST API.
 * It provides methods to perform CRUD operations on user resources.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-09-05
 */
public class UserEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USERS_END = "/users";
    private static final String USERS_RESOURCE_END = "/users/{userID}";

    /**
     * Constructs a UserEndpoint with the given RequestSpecification.
     *
     * @param specification The RequestSpecification to use for API requests.
     */
    public UserEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Creates a new user.
     *
     * @param userDto The UserDto object containing user details.
     * @return The created UserDto object.
     * @see UserDto
     */
    public UserDto create(UserDto userDto) {
        return create(userDto, HttpStatus.CREATED)
            .extract().as(UserDto.class);
    }

    /**
     * Creates a new user with a specific HTTP status.
     *
     * @param userDto The UserDto object containing user details.
     * @param status The HTTP status to set in the response.
     * @return The ValidatableResponse object representing the API response.
     * @see UserDto
     * @see HttpStatus
     */
    public ValidatableResponse create(UserDto userDto, HttpStatus status) {
        LOGGER.info("Create new User");
        return post(
            this.specification,
            USERS_END,
            userDto)
            .statusCode(status.getCode());
    }

    /**
     * Updates an existing user by ID.
     *
     * @param id The ID of the user to update.
     * @param userDto The UserDto object containing updated user details.
     * @return The updated UserDto object.
     * @see UserDto
     */
    public UserDto update(String id, UserDto userDto) {
        return update(userDto, id, HttpStatus.OK)
            .extract().as(UserDto.class);
    }

    /**
     * Updates an existing user by ID with a specific HTTP status.
     *
     * @param userDto The UserDto object containing updated user details.
     * @param id The ID of the user to update.
     * @param status The HTTP status to set in the response.
     * @return The ValidatableResponse object representing the API response.
     * @see UserDto
     * @see HttpStatus
     */
    public ValidatableResponse update(UserDto userDto, String id, HttpStatus status) {
        LOGGER.info("Update User by id [{}]", id);
        return put(
            this.specification,
            USERS_RESOURCE_END,
            userDto,
            id)
            .statusCode(status.getCode());
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The retrieved UserDto object.
     * @see UserDto
     */
    public UserDto getById(String id) {
        return getById(id, HttpStatus.OK)
            .extract().as(UserDto.class);
    }

    /**
     * Retrieves a user by ID with a specific HTTP status.
     *
     * @param id The ID of the user to retrieve.
     * @param status The HTTP status to set in the response.
     * @return The ValidatableResponse object representing the API response.
     * @see HttpStatus
     */
    public ValidatableResponse getById(String id, HttpStatus status) {
        LOGGER.info("Get User by id [{}]", id);
        return get(
            this.specification,
            USERS_RESOURCE_END,
            id)
            .statusCode(status.getCode());
    }

    /**
     * Retrieves all users.
     *
     * @return A List of all UserDto objects.
     * @see UserDto
     */
    public List<UserDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(UserDto[].class));
    }

    /**
     * Retrieves all users with a specific HTTP status.
     *
     * @param status The HTTP status to set in the response.
     * @return The ValidatableResponse object representing the API response.
     * @see HttpStatus
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Users");
        ValidatableResponse response = get(this.specification, USERS_END);
        response.statusCode(status.getCode());
        return response;
    }
}
