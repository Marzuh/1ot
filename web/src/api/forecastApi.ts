import {formatResponse, serverHttp} from "./serverHttp";
import {ApiResponse} from "../types/ForecastApiResponse.ts";
import {FilterForm} from "../types/FilterForm.ts";

export const searchForecasts = async (searchParams: FilterForm) => {
    const resp = await serverHttp.get(`forecast/search`, {params: searchParams});
    return formatResponse<ApiResponse>(resp);
}
