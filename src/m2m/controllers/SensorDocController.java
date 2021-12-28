package m2m.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import m2m.repo.entities.SensorStatistics;
import m2m.service.IBackOffice;
import static m2m.api.ApiConstants.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
public class SensorDocController {

    @Autowired
    IBackOffice service;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @GetMapping(GET_ID_BIG_VALUES)
    List<Integer> getIdBigValues(String from, String to, int sensorValue) {
        return service.getIdBigValues(LocalDateTime.parse(from), LocalDateTime.parse(to), sensorValue);
    }

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @GetMapping(GET_ID_SMALL_VALUES)
    List<Integer> getIdSmallValues(String from, String to, int sensorValue) {
        return service.getIdSmallValues(LocalDateTime.parse(from), LocalDateTime.parse(to), sensorValue);
    }

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @GetMapping(GET_DATES_BIG_VALUES)
    List<LocalDateTime> getDatesBigValues(int sensorId, String from, String to, int sensorValue) {
        return service.getDatesBigValues(sensorId, LocalDateTime.parse(from), LocalDateTime.parse(to), sensorValue);
    };

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @GetMapping(GET_DATES_SMALL_VALUES)
    List<LocalDateTime> getDatesSmallValues(int sensorId, String from, String to, int sensorValue) {
        return service.getDatesSmallValues(sensorId, LocalDateTime.parse(from), LocalDateTime.parse(to), sensorValue);
    };

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @GetMapping(GET_STATISTICS)
    SensorStatistics getSensorStatistics(int sensorId, String from, String to) {
        return service.getSensorStatistics(sensorId, LocalDateTime.parse(from), LocalDateTime.parse(to));
    }
}
