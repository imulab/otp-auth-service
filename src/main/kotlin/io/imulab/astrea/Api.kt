package io.imulab.astrea

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.reactive.function.server.*

class ValidationHandler {

    suspend fun validate(request: ServerRequest): ServerResponse {
        val validationRequest = request.awaitBody<ValidationRequest>()

        if (validationRequest.code == "123456")
            return ServerResponse.noContent().buildAndAwait()

        return ServerResponse.badRequest()
            .json()
            .bodyAndAwait(ValidationFailedResponse(
                subject = validationRequest.subject,
                error = "bad_code",
                attemptsLeft = 3
            ))
    }
}

data class ValidationRequest(
    @field:JsonProperty("code")
    val code: String,
    @field:JsonProperty("subject")
    val subject: String
)

data class ValidationFailedResponse(
    @field:JsonProperty("subject")
    val subject: String,
    @field:JsonProperty("error")
    val error: String,
    @field:JsonProperty("attempts-left")
    val attemptsLeft: Int
)