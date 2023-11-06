export interface ErrorMessage {
    errorCode: keyof ErrorCode;
    message: string;
    stackTrace?: string;
}

export interface ErrorCode {
    UNKNOWN: string;
    MISSING_ARGS: string;
    INVALID_ARGS: string;
    USER_NOT_FOUND: string;
    USER_ALREADY_REGISTERED: string;
    LOGIN_FAILURE: string;
}

export const ErrorCodes: ErrorCode = {
    UNKNOWN: "UNKNOWN",
    MISSING_ARGS: "MISSING_ARGS",
    INVALID_ARGS: "INVALID_ARGS",
    USER_NOT_FOUND: "USER_NOT_FOUND",
    USER_ALREADY_REGISTERED: "USER_ALREADY_REGISTERED",
    LOGIN_FAILURE: "LOGIN_FAILURE",
};
