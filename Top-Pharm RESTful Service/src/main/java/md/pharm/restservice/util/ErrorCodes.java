package md.pharm.restservice.util;

/**
 * Created by Andrei on 9/26/2015.
 */
public class ErrorCodes {

    public static class ErrorCode{
        public String name;
        public int httpCode;
        public String httpDescription;
        public String userMessage;

        public ErrorCode(String name, String httpDescription, int httpCode, String userMessage) {
            this.name = name;
            this.httpCode = httpCode;
            this.httpDescription = httpDescription;
            this.userMessage = userMessage;
        }
    }
    public static ErrorCode ValidAuthenticationInfo = new ErrorCode("ValidAuthenticationInfo", "OK", 200, "The authentication information was provided in the correct format");

    public static ErrorCode Continue = new ErrorCode("Continue", "Continue", 100, "The client SHOULD continue with its request");
    public static ErrorCode SwitchingProtocols = new ErrorCode("Switching Protocols", "Switching Protocols", 101, "The server understands and is willing to comply with the client's request");

    public static ErrorCode OK = new ErrorCode("OK", "OK", 200, "The request has succeeded");
    public static ErrorCode Updated = new ErrorCode("Updated", "OK", 200, "The request has succeeded");
    public static ErrorCode Created = new ErrorCode("Created", "Created", 201, "The request has been fulfilled and resulted in a new resource being created.");
    public static ErrorCode Accepted = new ErrorCode("Accepted", "Accepted", 202, "The request has been accepted for processing, but the processing has not been completed.");
    public static ErrorCode NonAuthoritativeInformation = new ErrorCode("Non-Authoritative Information", "Non-Authoritative Information", 203, "The returned metainformation in the entity-header is not the definitive set as available from the origin server, but is gathered from a local or a third-party copy");
    public static ErrorCode NoContent = new ErrorCode("No Content", "No Content", 204, "The server has fulfilled the request but does not need to return an entity-body, and might want to return updated metainformation");
    public static ErrorCode ResetContent = new ErrorCode("Reset Content", "Reset Content", 205, "The server has fulfilled the request and the user agent SHOULD reset the document view which caused the request to be sent");
    public static ErrorCode PartialContent = new ErrorCode("Partial Content", "Partial Content", 206, "The server has fulfilled the partial GET request for the resource");
    public static ErrorCode MultiStatus = new ErrorCode("Multi-Status", "Multi-Status", 207, "The 207 (Multi-Status) status code provides status for multiple independent operations (see Section 13 for more information)");

    public static ErrorCode MultipleChoices = new ErrorCode("MultipleChoices", "MultipleChoices", 300, "The requested resource corresponds to any one of a set of representations");
    public static ErrorCode MovedPermanently = new ErrorCode("Moved Permanently", "Moved Permanently", 301, "The requested resource has been assigned a new permanent URI");
    public static ErrorCode Found = new ErrorCode("Found", "Found", 302, "The requested resource resides temporarily under a different URI");
    public static ErrorCode SeeOther = new ErrorCode("See Other", "See Other", 303, "The response to the request can be found under a different URI");
    public static ErrorCode NotModified = new ErrorCode("Not Modified", "Not Modified", 304, "If the client has performed a conditional GET request and access is allowed, but the document has not been modified, the server SHOULD respond with this status code.");
    public static ErrorCode UseProxy = new ErrorCode("Use Proxy", "Use Proxy", 305, "The requested resource MUST be accessed through the proxy given by the Location field");
    public static ErrorCode Reserved = new ErrorCode("Reserved", "Reserved", 306, "The 306 status code was used in a previous version of the specification, is no longer used, and the code is reserved");
    public static ErrorCode TemporaryRedirect = new ErrorCode("Temporary Redirect", "Temporary Redirect", 307, "The requested resource resides temporarily under a different URI");

    public static ErrorCode ReadConditionNotMet = new ErrorCode("ReadConditionNotMet", "Not Modified", 304, "The condition specified in the conditional header(s) was not met for a read operation");
    public static ErrorCode MissingRequiredHeader = new ErrorCode("MissingRequiredHeader", "Bad Request", 400, "A required HTTP header was not specified");
    public static ErrorCode MissingRequiredXmlNode = new ErrorCode("MissingRequiredXmlNode", "Bad Request", 400, "A required XML node was not specified in the request body");
    public static ErrorCode UnsupportedHeader = new ErrorCode("UnsupportedHeader", "Bad Request", 400, "One of the HTTP headers specified in the request is not supported");
    public static ErrorCode UnsupportedXmlNode = new ErrorCode("UnsupportedXmlNode", "Bad Request", 400, "One of the XML nodes specified in the request body is not supported");
    public static ErrorCode InvalidHeaderValue = new ErrorCode("InvalidHeaderValue", "Bad Request", 400, "The value provided for one of the HTTP headers was not in the correct format");
    public static ErrorCode InvalidXmlNodeValue = new ErrorCode("InvalidXmlNodeValue", "Bad Request", 400, "The value provided for one of the XML nodes in the request body was not in the correct format");
    public static ErrorCode MissingRequiredQueryParameter = new ErrorCode("MissingRequiredQueryParameter", "Bad Request", 400, "A required query parameter was not specified for this request");
    public static ErrorCode UnsupportedQueryParameter = new ErrorCode("UnsupportedQueryParameter", "Bad Request", 400, "One of the query parameters specified in the request URI is not supported");
    public static ErrorCode InvalidQueryParameterValue = new ErrorCode("InvalidQueryParameterValue", "Bad Request", 400, "An invalid value was specified for one of the query parameters in the request URI");
    public static ErrorCode OutOfRangeQueryParameterValue = new ErrorCode("OutOfRangeQueryParameterValue", "Bad Request", 400, "A query parameter specified in the request URI is outside the permissible range");
    public static ErrorCode RequestUrlFailedToParse = new ErrorCode("RequestUrlFailedToParse", "Bad Request", 400, "The url in the request could not be parsed");
    public static ErrorCode InvalidUri = new ErrorCode("InvalidUri", "Bad Request", 400, "The requested URI does not represent any resource on the server");
    public static ErrorCode InvalidHttpVerb = new ErrorCode("InvalidHttpVerb", "Bad Request", 400, "The HTTP verb specified was not recognized by the server");
    public static ErrorCode EmptyMetadataKey = new ErrorCode("EmptyMetadataKey", "Bad Request", 400, "The key for one of the metadata key-value pairs is empty");
    public static ErrorCode InvalidXmlDocument = new ErrorCode("InvalidXmlDocument", "Bad Request", 400, "The specified XML is not syntactically valid");
    public static ErrorCode Md5Mismatch = new ErrorCode("Md5Mismatch", "Bad Request", 400, "The MD5 value specified in the request did not match the MD5 value calculated by the server");
    public static ErrorCode InvalidMd5= new ErrorCode("InvalidMd5", "Bad Request", 400, "The MD5 value specified in the request is invalid. The MD5 value must be 128 bits and Base64-encoded");
    public static ErrorCode OutOfRangeInput = new ErrorCode("OutOfRangeInput", "Bad Request", 400, "One of the request inputs is out of range");
    public static ErrorCode InvalidAuthenticationInfo = new ErrorCode("InvalidAuthenticationInfo", "Bad Request", 400, "The authentication information was not provided in the correct format. Verify the values of Username and Password");
    public static ErrorCode InvalidInput = new ErrorCode("InvalidInput", "Bad Request", 400, "One of the request inputs is not valid");
    public static ErrorCode InvalidMetadata = new ErrorCode("InvalidMetadata", "Bad Request", 400, "The specified metadata is invalid. It includes characters that are not permitted");
    public static ErrorCode InvalidResourceName = new ErrorCode("InvalidResourceName", "Bad Request", 400, "The specifed resource name contains invalid characters");
    public static ErrorCode MetadataTooLarge = new ErrorCode("MetadataTooLarge", "Bad Request", 400, "The size of the specified metadata exceeds the maximum size permitted");
    public static ErrorCode ConditionHeadersNotSupported = new ErrorCode("ConditionHeadersNotSupported", "BadRequest", 400, "Condition headers are not supported");
    public static ErrorCode MultipleConditionHeadersNotSupported = new ErrorCode("MultipleConditionHeadersNotSupported", "Bad Request", 400, "Multiple condition headers are not supported");
    public static ErrorCode AuthenticationFailed = new ErrorCode("AuthenticationFailed", "Forbidden", 403, "Server failed to authenticate the request. Make sure the value of the Authorization header is formed correctly including the signature");
    public static ErrorCode InsufficientAccountReadPermissions = new ErrorCode("InsufficientAccountReadPermissions", "Forbidden", 403, "Read operations are currently disabled");
    public static ErrorCode InsufficientAccountWritePermissions = new ErrorCode("InsufficientAccountWritePermissions", "Forbidden", 403, "Write operations are not allowed");
    public static ErrorCode ResourceNotFound = new ErrorCode("ResourceNotFound", "Not Found", 404, "The specified resource does not exist");
    public static ErrorCode AccountIsDisabled = new ErrorCode("AccountIsDisabled", "Forbidden", 403, "The specified account is disabled");
    public static ErrorCode InsufficientAccountPermissions = new ErrorCode("InsufficientAccountPermissions", "Forbidden", 403, "The account being accessed does not have sufficient permissions to execute this operation");
    public static ErrorCode UnsupportedHttpVerb = new ErrorCode("UnsupportedHttpVerb", "Method Not Allowed", 405, "The resource doesn't support the specified HTTP verb");
    public static ErrorCode AccountAlreadyExists = new ErrorCode("AccountAlreadyExists", "Conflict", 409, "The specified username already exists");
    public static ErrorCode AccountBeingCreated = new ErrorCode("AccountBeingCreated", "Conflict", 409, "The specified account is in the process of being created");
    public static ErrorCode ResourceAlreadyExists = new ErrorCode("ResourceAlreadyExists", "Conflict", 409, "The specified resource already exists");
    public static ErrorCode ResourceNotExists = new ErrorCode("ResourceNotExists", "Conflict", 409, "The specified resource not exists");
    public static ErrorCode ResourceTypeMismatch = new ErrorCode("ResourceTypeMismatch", "Conflict", 409, "The specified resource type does not match the type of the existing resource");
    public static ErrorCode MissingContentLengthHeader = new ErrorCode("MissingContentLengthHeader", "Length Required", 411, "The Content-Length header was not specified");
    public static ErrorCode WriteConditionNotMet = new ErrorCode("WriteConditionNotMet", "Precondition Failed", 412, "The condition specified in the conditional header(s) was not met for a write operation");
    public static ErrorCode RequestBodyTooLarge = new ErrorCode("RequestBodyTooLarge", "Request Entity Too Large", 413, "The size of the request body exceeds the maximum size permitted");
    public static ErrorCode InvalidRange = new ErrorCode("InvalidRange", "Requested Range Not Satisfiable", 416, "The range specified is invalid for the current size of the resource");
    public static ErrorCode InternalError = new ErrorCode("InternalError", "Internal Server Error", 500, "The server encountered an internal error. Please retry the request");
    public static ErrorCode OperationTimedOut = new ErrorCode("OperationTimedOut", "Internal Server Error", 500, "The operation could not be completed within the permitted time");
    public static ErrorCode ServerBusy = new ErrorCode("ServerBusy", "Service Unavailable", 503, "The server is currently unable to receive requests. Please retry your request");

}
