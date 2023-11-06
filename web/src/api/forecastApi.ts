import {formatResponse, serverHttp} from "./serverHttp";
import {Forecast} from "../types/Forecast.ts";
import {FilterForm} from "../types/FilterForm.ts";

export const searchForecasts = async (searchParams: FilterForm) => {
    const resp = await serverHttp.get(`forecast/search`, { params: searchParams} );
    return formatResponse<Forecast[]>(resp);
}
