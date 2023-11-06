import {PlaceForecast} from "./PlaceForecast.ts";

export interface Forecast {

    id: number;
    date: string;
    timeOfDay: TimeOfDay;
    phenomenon: string;
    tempMin: number;
    tempMax: number;
    placeForecasts: PlaceForecast[];
    textDescription: string;
    peipsi: string;

}

export enum TimeOfDay {
    DAY = "DAY",
    NIGHT = "NIGHT"
}