package com.rezkyatinnov.jeniuscontact.restapi

/**
 * Created by rezkyatinnov on 10/08/2017.
 */

enum class HttpStatus private constructor(
    private val value: Int,
    /**
     * Return the reason phrase of this status code.
     */
    val reasonPhrase: String
) {

    // 1xx Informational

    /**
     * `100 Continue`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.2.1](http://tools.ietf.org/html/rfc7231.section-6.2.1)
     */
    CONTINUE(100, "Continue"),
    /**
     * `101 Switching Protocols`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.2.2](http://tools.ietf.org/html/rfc7231.section-6.2.2)
     */
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    /**
     * `102 Processing`.
     *
     * @see [WebDAV](http://tools.ietf.org/html/rfc2518.section-10.1)
     */
    PROCESSING(102, "Processing"),
    /**
     * `103 Checkpoint`.
     *
     * @see [A proposal for supporting
     * resumable POST/PUT HTTP requests in HTTP/1.0](http://code.google.com/p/gears/wiki/ResumableHttpRequestsProposal)
     */
    CHECKPOINT(103, "Checkpoint"),

    // 2xx Success

    /**
     * `200 OK`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.3.1](http://tools.ietf.org/html/rfc7231.section-6.3.1)
     */
    OK(200, "OK"),
    /**
     * `201 Created`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.3.2](http://tools.ietf.org/html/rfc7231.section-6.3.2)
     */
    CREATED(201, "Created"),
    /**
     * `202 Accepted`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.3.3](http://tools.ietf.org/html/rfc7231.section-6.3.3)
     */
    ACCEPTED(202, "Accepted"),
    /**
     * `203 Non-Authoritative Information`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.3.4](http://tools.ietf.org/html/rfc7231.section-6.3.4)
     */
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    /**
     * `204 No Content`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.3.5](http://tools.ietf.org/html/rfc7231.section-6.3.5)
     */
    NO_CONTENT(204, "No Content"),
    /**
     * `205 Reset Content`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.3.6](http://tools.ietf.org/html/rfc7231.section-6.3.6)
     */
    RESET_CONTENT(205, "Reset Content"),
    /**
     * `206 Partial Content`.
     *
     * @see [HTTP/1.1: Range Requests, section 4.1](http://tools.ietf.org/html/rfc7233.section-4.1)
     */
    PARTIAL_CONTENT(206, "Partial Content"),
    /**
     * `207 Multi-Status`.
     *
     * @see [WebDAV](http://tools.ietf.org/html/rfc4918.section-13)
     */
    MULTI_STATUS(207, "Multi-Status"),
    /**
     * `208 Already Reported`.
     *
     * @see [WebDAV Binding Extensions](http://tools.ietf.org/html/rfc5842.section-7.1)
     */
    ALREADY_REPORTED(208, "Already Reported"),
    /**
     * `226 IM Used`.
     *
     * @see [Delta encoding in HTTP](http://tools.ietf.org/html/rfc3229.section-10.4.1)
     */
    IM_USED(226, "IM Used"),

    // 3xx Redirection

    /**
     * `300 Multiple Choices`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.4.1](http://tools.ietf.org/html/rfc7231.section-6.4.1)
     */
    MULTIPLE_CHOICES(300, "Multiple Choices"),
    /**
     * `301 Moved Permanently`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.4.2](http://tools.ietf.org/html/rfc7231.section-6.4.2)
     */
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    /**
     * `302 Found`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.4.3](http://tools.ietf.org/html/rfc7231.section-6.4.3)
     */
    FOUND(302, "Found"),
    /**
     * `302 Moved Temporarily`.
     *
     * @see [HTTP/1.0, section 9.3](http://tools.ietf.org/html/rfc1945.section-9.3)
     *
     */
    @Deprecated("in favor of {@link #FOUND} which will be returned from {@code HttpStatus.valueOf(302)}")
    MOVED_TEMPORARILY(302, "Moved Temporarily"),
    /**
     * `303 See Other`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.4.4](http://tools.ietf.org/html/rfc7231.section-6.4.4)
     */
    SEE_OTHER(303, "See Other"),
    /**
     * `304 Not Modified`.
     *
     * @see [HTTP/1.1: Conditional Requests, section 4.1](http://tools.ietf.org/html/rfc7232.section-4.1)
     */
    NOT_MODIFIED(304, "Not Modified"),
    /**
     * `305 Use Proxy`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.4.5](http://tools.ietf.org/html/rfc7231.section-6.4.5)
     *
     */
    @Deprecated("due to security concerns regarding in-band configuration of a proxy")
    USE_PROXY(305, "Use Proxy"),
    /**
     * `307 Temporary Redirect`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.4.7](http://tools.ietf.org/html/rfc7231.section-6.4.7)
     */
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    /**
     * `308 Permanent Redirect`.
     *
     * @see [RFC 7238](http://tools.ietf.org/html/rfc7238)
     */
    PERMANENT_REDIRECT(308, "Permanent Redirect"),

    // --- 4xx Client Error ---

    /**
     * `400 Bad Request`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.1](http://tools.ietf.org/html/rfc7231.section-6.5.1)
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * `401 Unauthorized`.
     *
     * @see [HTTP/1.1: Authentication, section 3.1](http://tools.ietf.org/html/rfc7235.section-3.1)
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * `402 Payment Required`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.2](http://tools.ietf.org/html/rfc7231.section-6.5.2)
     */
    PAYMENT_REQUIRED(402, "Payment Required"),
    /**
     * `403 Forbidden`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.3](http://tools.ietf.org/html/rfc7231.section-6.5.3)
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * `404 Not Found`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.4](http://tools.ietf.org/html/rfc7231.section-6.5.4)
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * `405 Method Not Allowed`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.5](http://tools.ietf.org/html/rfc7231.section-6.5.5)
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    /**
     * `406 Not Acceptable`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.6](http://tools.ietf.org/html/rfc7231.section-6.5.6)
     */
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    /**
     * `407 Proxy Authentication Required`.
     *
     * @see [HTTP/1.1: Authentication, section 3.2](http://tools.ietf.org/html/rfc7235.section-3.2)
     */
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    /**
     * `408 Request Timeout`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.7](http://tools.ietf.org/html/rfc7231.section-6.5.7)
     */
    REQUEST_TIMEOUT(408, "Request Timeout"),
    /**
     * `409 Conflict`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.8](http://tools.ietf.org/html/rfc7231.section-6.5.8)
     */
    CONFLICT(409, "Conflict"),
    /**
     * `410 Gone`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.9](http://tools.ietf.org/html/rfc7231.section-6.5.9)
     */
    GONE(410, "Gone"),
    /**
     * `411 Length Required`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.10](http://tools.ietf.org/html/rfc7231.section-6.5.10)
     */
    LENGTH_REQUIRED(411, "Length Required"),
    /**
     * `412 Precondition failed`.
     *
     * @see [HTTP/1.1: Conditional Requests, section 4.2](http://tools.ietf.org/html/rfc7232.section-4.2)
     */
    PRECONDITION_FAILED(412, "Precondition Failed"),
    /**
     * `413 Payload Too Large`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.11](http://tools.ietf.org/html/rfc7231.section-6.5.11)
     *
     * @since 4.1
     */
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    /**
     * `413 Request Entity Too Large`.
     *
     * @see [HTTP/1.1, section 10.4.14](http://tools.ietf.org/html/rfc2616.section-10.4.14)
     *
     */
    @Deprecated("in favor of {@link #PAYLOAD_TOO_LARGE} which will be returned from {@code HttpStatus.valueOf(413)}")
    REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
    /**
     * `414 URI Too Long`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.12](http://tools.ietf.org/html/rfc7231.section-6.5.12)
     *
     * @since 4.1
     */
    URI_TOO_LONG(414, "URI Too Long"),
    /**
     * `414 Request-URI Too Long`.
     *
     * @see [HTTP/1.1, section 10.4.15](http://tools.ietf.org/html/rfc2616.section-10.4.15)
     *
     */
    @Deprecated("in favor of {@link #URI_TOO_LONG} which will be returned from {@code HttpStatus.valueOf(414)}")
    REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),
    /**
     * `415 Unsupported Media Type`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.13](http://tools.ietf.org/html/rfc7231.section-6.5.13)
     */
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    /**
     * `416 Requested Range Not Satisfiable`.
     *
     * @see [HTTP/1.1: Range Requests, section 4.4](http://tools.ietf.org/html/rfc7233.section-4.4)
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
    /**
     * `417 Expectation Failed`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.5.14](http://tools.ietf.org/html/rfc7231.section-6.5.14)
     */
    EXPECTATION_FAILED(417, "Expectation Failed"),
    /**
     * `418 I'm a teapot`.
     *
     * @see [HTCPCP/1.0](http://tools.ietf.org/html/rfc2324.section-2.3.2)
     */
    I_AM_A_TEAPOT(418, "I'm a teapot"),

    @Deprecated("See <a href=\"http://tools.ietf.org/rfcdiff?difftype=--hwdiff&url2=draft-ietf-webdav-protocol-06.txt\">WebDAV Draft Changes</a>")
    INSUFFICIENT_SPACE_ON_RESOURCE(419, "Insufficient Space On Resource"),

    @Deprecated("See <a href=\"http://tools.ietf.org/rfcdiff?difftype=--hwdiff&url2=draft-ietf-webdav-protocol-06.txt\">WebDAV Draft Changes</a>")
    METHOD_FAILURE(420, "Method Failure"),

    @Deprecated("See <a href=\"http://tools.ietf.org/rfcdiff?difftype=--hwdiff&url2=draft-ietf-webdav-protocol-06.txt\">WebDAV Draft Changes</a>")
    DESTINATION_LOCKED(421, "Destination Locked"),
    /**
     * `422 Unprocessable Entity`.
     *
     * @see [WebDAV](http://tools.ietf.org/html/rfc4918.section-11.2)
     */
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    /**
     * `423 Locked`.
     *
     * @see [WebDAV](http://tools.ietf.org/html/rfc4918.section-11.3)
     */
    LOCKED(423, "Locked"),
    /**
     * `424 Failed Dependency`.
     *
     * @see [WebDAV](http://tools.ietf.org/html/rfc4918.section-11.4)
     */
    FAILED_DEPENDENCY(424, "Failed Dependency"),
    /**
     * `426 Upgrade Required`.
     *
     * @see [Upgrading to TLS Within HTTP/1.1](http://tools.ietf.org/html/rfc2817.section-6)
     */
    UPGRADE_REQUIRED(426, "Upgrade Required"),
    /**
     * `428 Precondition Required`.
     *
     * @see [Additional HTTP Status Codes](http://tools.ietf.org/html/rfc6585.section-3)
     */
    PRECONDITION_REQUIRED(428, "Precondition Required"),
    /**
     * `429 Too Many Requests`.
     *
     * @see [Additional HTTP Status Codes](http://tools.ietf.org/html/rfc6585.section-4)
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    /**
     * `431 Request Header Fields Too Large`.
     *
     * @see [Additional HTTP Status Codes](http://tools.ietf.org/html/rfc6585.section-5)
     */
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    /**
     * `451 Unavailable For Legal Reasons`.
     *
     * @see [
     * An HTTP Status Code to Report Legal Obstacles](https://tools.ietf.org/html/draft-ietf-httpbis-legally-restricted-status-04)
     *
     * @since 4.3
     */
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

    // --- 5xx Server Error ---

    /**
     * `500 Internal Server Error`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.6.1](http://tools.ietf.org/html/rfc7231.section-6.6.1)
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /**
     * `501 Not Implemented`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.6.2](http://tools.ietf.org/html/rfc7231.section-6.6.2)
     */
    NOT_IMPLEMENTED(501, "Not Implemented"),
    /**
     * `502 Bad Gateway`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.6.3](http://tools.ietf.org/html/rfc7231.section-6.6.3)
     */
    BAD_GATEWAY(502, "Bad Gateway"),
    /**
     * `503 Service Unavailable`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.6.4](http://tools.ietf.org/html/rfc7231.section-6.6.4)
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    /**
     * `504 Gateway Timeout`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.6.5](http://tools.ietf.org/html/rfc7231.section-6.6.5)
     */
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    /**
     * `505 HTTP Version Not Supported`.
     *
     * @see [HTTP/1.1: Semantics and Content, section 6.6.6](http://tools.ietf.org/html/rfc7231.section-6.6.6)
     */
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),
    /**
     * `506 Variant Also Negotiates`
     *
     * @see [Transparent Content Negotiation](http://tools.ietf.org/html/rfc2295.section-8.1)
     */
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
    /**
     * `507 Insufficient Storage`
     *
     * @see [WebDAV](http://tools.ietf.org/html/rfc4918.section-11.5)
     */
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
    /**
     * `508 Loop Detected`
     *
     * @see [WebDAV Binding Extensions](http://tools.ietf.org/html/rfc5842.section-7.2)
     */
    LOOP_DETECTED(508, "Loop Detected"),
    /**
     * `509 Bandwidth Limit Exceeded`
     */
    BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),
    /**
     * `510 Not Extended`
     *
     * @see [HTTP Extension Framework](http://tools.ietf.org/html/rfc2774.section-7)
     */
    NOT_EXTENDED(510, "Not Extended"),
    /**
     * `511 Network Authentication Required`.
     *
     * @see [Additional HTTP Status Codes](http://tools.ietf.org/html/rfc6585.section-6)
     */
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

    /**
     * Whether this status code is in the HTTP series
     * This is a shortcut for checking the value of [.series].
     */
    val is1xxInformational: Boolean
        get() = Series.INFORMATIONAL == series()

    /**
     * Whether this status code is in the HTTP series
     * This is a shortcut for checking the value of [.series].
     */
    val is2xxSuccessful: Boolean
        get() = Series.SUCCESSFUL == series()

    /**
     * Whether this status code is in the HTTP series
     * This is a shortcut for checking the value of [.series].
     */
    val is3xxRedirection: Boolean
        get() = Series.REDIRECTION == series()


    /**
     * Whether this status code is in the HTTP series
     * This is a shortcut for checking the value of [.series].
     */
    val is4xxClientError: Boolean
        get() = Series.CLIENT_ERROR == series()

    /**
     * Whether this status code is in the HTTP series
     * This is a shortcut for checking the value of [.series].
     */
    val is5xxServerError: Boolean
        get() = Series.SERVER_ERROR == series()

    /**
     * Whether this status code is in the HTTP series
     * This is a shortcut for checking the value of [.series].
     */
    val isError: Boolean
        get() = is4xxClientError || is5xxServerError


    /**
     * Return the integer value of this status code.
     */
    fun value(): Int {
        return this.value
    }

    /**
     * Returns the HTTP status series of this status code.
     *
     * @see HttpStatus.Series
     */
    fun series(): Series {
        return Series.valueOf(this)
    }

    /**
     * Return a string representation of this status code.
     */
    override fun toString(): String {
        return Integer.toString(this.value)
    }


    /**
     * Enumeration of HTTP status series.
     *
     * Retrievable via [HttpStatus.series].
     */
    enum class Series private constructor(private val value: Int) {

        INFORMATIONAL(1),
        SUCCESSFUL(2),
        REDIRECTION(3),
        CLIENT_ERROR(4),
        SERVER_ERROR(5);

        /**
         * Return the integer value of this status series. Ranges from 1 to 5.
         */
        fun value(): Int {
            return this.value
        }

        companion object {

            fun valueOf(status: Int): Series {
                val seriesCode = status / 100
                for (series in values()) {
                    if (series.value == seriesCode) {
                        return series
                    }
                }
                throw IllegalArgumentException("No matching constant for [$status]")
            }

            fun valueOf(status: HttpStatus): Series {
                return valueOf(status.value)
            }
        }
    }

    companion object {


        /**
         * Return the enum constant of this type with the specified numeric value.
         *
         * @param statusCode the numeric value of the enum to be returned
         * @return the enum constant with the specified numeric value
         * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
         */
        fun valueOf(statusCode: Int): HttpStatus {
            for (status in values()) {
                if (status.value == statusCode) {
                    return status
                }
            }
            throw IllegalArgumentException("No matching constant for [$statusCode]")
        }
    }
}
