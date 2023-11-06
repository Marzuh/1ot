SET search_path = weather, public;

TRUNCATE shedlock, forecast, place, place_forecast;

-- Places
INSERT INTO place(id, name)
VALUES (1, 'Harku'),
       (2, 'Jõhvi'),
       (3, 'Tartu'),
       (4, 'Pärnu'),
       (5, 'Kuressaare'),
       (6, 'Türi');

-- Forecast
INSERT INTO forecast(id, date, time_of_day, phenomenon, temp_min, temp_max, text_description, peipsi)
VALUES (1, '2023-11-06', 'DAY', 'Moderate rain', 6, 10,
        'Mostly cloudy. Rain in many places, in Central and Eastern Estonia more dense rain. In the evening precipitation reducing eastwards. Variable wind 1-7 m/s. Air temperature 6..10°C.',
        'Southeast, east wind 3-7 m/s, towards evening variable 1-5 m/s. Wave height up to 0.6 m. Rain at times. Visibility moderate or poor. Air temperature 5..7°C.'),
       (2, '2023-11-06', 'NIGHT', 'Moderate rain', 3, 7,
        'Cloudy with some clear spells. Before midnight locally rain , after midnight more dense rain is spreading from Southern Estonia to north , in western part less rain. Locally fog possible. Maibly east , southeast wind 2-8 , before midnight on coast of western islands in gusts 11 m/s. Air temperature 3..7°C.',
        'Southeast , east wind 3-7 m/s. Wave height up to 0.6 m. Before midnight locally rain and fog possible , after midnight dense rain is spreading from south. Visibility mainly moderate or poor. Air temperature 4..7°C.'),
       (3, '2023-11-07', 'DAY', 'Moderate shower', 6, 10,
        'Cloudy with some clear spells. Rain in many places. Southwest , west wind 3-9 , in gusts up to 12 m/s. Air temperature 6..10°C.',
        null),
       (4, '2023-11-07', 'NIGHT', 'Moderate rain', 4, 9, 'Cloudy with some clear spells. Rain in many places. Variable wind 1-7 , after midnight becoming westerly 3-9 , on coast in gusts up to 12 m/s.
Air temperature 4..9°C.', null),
       (5, '2023-11-08', 'DAY', 'Light shower', 6, 9, 'Cloudy with clear spells. Rain at times. Westerly wind 3-8 , in gusts to 11 m/s , in the afternoon slightly decreasing.
Air temperature 6..9°C.', null),
       (6, '2023-11-08', 'NIGHT', 'Moderate rain', 3, 8,
        'Cloudy with some clear spells. Rain at times. Southwest , west wind 3-9 , on coast in gusts up to 12 m/s. Air temperature 3..8°C.',
        null),
       (7, '2023-11-09', 'DAY', 'Moderate shower', 5, 9,
        'Cloudy with some clear spells. Rain in many places. Variable wind 1-7 m/s , in southern part southerly wind 3-9 , in the evening increasing in the Gulf of Riga in gusts up to 12 m/s. Air temperature 5..9°C.',
        null),
       (8, '2023-11-09', 'NIGHT', 'Moderate rain', 1, 7,
        'Cloudy with clear spells. Rain in many places. Westerly wind 3-9 m/s , after midnight variable wind 1-7 m/s. Air temperature 1..7°C.',
        null),
       (9, '2023-11-10', 'DAY', 'Moderate rain', 5, 8,
        'Cloudy. At times rain. South , southeast wind 3-9 m/s. Air temperature 5..8°C.', null),
       (10, '2023-11-10', 'NIGHT', 'Light rain', 1, 7,
        'Cloudy. Rain in many places , by morning possibility for rain is decreasing. Wind around south 2-8 m/s. Air temperature 1..7°C.',
        null);

-- Place forecast
INSERT INTO place_forecast(id, place_id, forecast_id, phenomenon, temp_min, temp_max)
VALUES
(4, 5, 1, 'Light rain', null, 10),
(5, 3, 1, 'Moderate rain', null, 8),
(6, 6, 1, 'Moderate rain', null, 8),
(7, 6, 2, 'Moderate rain', 6, null),
(8, 3, 2, 'Moderate rain', 7, null),
(9, 5, 2, 'Light rain', 7, null),
(10, 4, 2, 'Light rain', 6, null),
(11, 2, 2, 'Moderate rain', 4, null),
(12, 1, 2, 'Light rain', 4, null);