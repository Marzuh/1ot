import axios, { AxiosResponse } from "axios";
import {ErrorMessage} from "../types/HttpErrors.ts";

export const serverHttp = axios.create({
    baseURL: '/api',
    timeout: 15000,
});

export const formatResponse = <T>(resp: AxiosResponse<any, any>) => {
    if (resp.status === 200) {
        return { data: resp.data as T, error: null, success: true };
    }
    return { error: resp.data as ErrorMessage, data: null,  success: false};
};