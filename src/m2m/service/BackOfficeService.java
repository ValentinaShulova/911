package m2m.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import m2m.repo.SensorRepo;
import m2m.repo.entities.SensorDoc;
import m2m.repo.entities.SensorStatistics;

@Service
public class BackOfficeService implements IBackOffice
{

        private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

        @Autowired
        SensorRepo repo;

        @Override
        public List<Integer> getIdBigValues(LocalDateTime from, LocalDateTime to, int sensorValue)
        {
                List<SensorDoc> list = repo.findByTimestampBetweenAndAvgValueGreaterThanOrderById(
                                getTimestampFromLocalDateTime(from), getTimestampFromLocalDateTime(to), sensorValue);
                return getListItems(list, e -> e.getId());
        }

        @Override
        public List<Integer> getIdSmallValues(LocalDateTime from, LocalDateTime to, int sensorValue)
        {
                List<SensorDoc> list = repo.findByTimestampBetweenAndAvgValueLessThanOrderById(
                                getTimestampFromLocalDateTime(from), getTimestampFromLocalDateTime(to), sensorValue);
                return getListItems(list, e -> e.getId());
        }

        @Override
        public List<LocalDateTime> getDatesBigValues(int sensorId, LocalDateTime from, LocalDateTime to,
                        int sensorValue)
        {
                List<SensorDoc> list = repo.findByTimestampBetweenAndAvgValueGreaterThanAndId(
                                getTimestampFromLocalDateTime(from), getTimestampFromLocalDateTime(to), sensorValue,
                                sensorId);
                return getListItems(list, e -> getLocalDateTimeFromTimestamp(e.getTimestamp()));
        }

        @Override
        public List<LocalDateTime> getDatesSmallValues(int sensorId, LocalDateTime from, LocalDateTime to,
                        int sensorValue)
        {
                List<SensorDoc> list = repo.findByTimestampBetweenAndAvgValueLessThanAndId(
                                getTimestampFromLocalDateTime(from), getTimestampFromLocalDateTime(to), sensorValue,
                                sensorId);
                return getListItems(list, e -> getLocalDateTimeFromTimestamp(e.getTimestamp()));
        }

        @Override
        public SensorStatistics getSensorStatistics(int sensorId, LocalDateTime from, LocalDateTime to)
        {
                return repo.getSensorStatistics(sensorId, getTimestampFromLocalDateTime(from),
                                getTimestampFromLocalDateTime(from));

        }

        private long getTimestampFromLocalDateTime(LocalDateTime ldt)
        {
                return ldt.toInstant(ZONE_OFFSET).toEpochMilli();
        }

        private LocalDateTime getLocalDateTimeFromTimestamp(long timestamp)
        {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZONE_OFFSET);
        }

        private <T, R> List<R> getListItems(List<T> list, Function<T, R> mapper)
        {
                return list.stream().map(mapper::apply).distinct().collect(Collectors.toList());
        }

}
