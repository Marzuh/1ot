import {formatResponse, serverHttp} from "./serverHttp";
import {Place} from "../types/Place.ts";

export const findAllPlaces = async () => {
    const resp = await serverHttp.get(`places`);
    return formatResponse<Place[]>(resp);
}
