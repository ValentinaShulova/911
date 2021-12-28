package m2m.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import m2m.repo.entities.SensorDoc;

public interface SensorRepo extends MongoRepository<SensorDoc, Object>, SensorsStatisticsRepo
{
        List <SensorDoc> findByTimestampBetweenAndAvgValueGreaterThanOrderById(long from, long to, int sensorValue);

        List <SensorDoc> findByTimestampBetweenAndAvgValueLessThanOrderById(long from, long to, int sensorValue);

        List <SensorDoc> findByTimestampBetweenAndAvgValueGreaterThanAndId(long from, long to, int sensorValue,
                        int sensorId);

        List <SensorDoc> findByTimestampBetweenAndAvgValueLessThanAndId(long from, long to, int sensorValue,
                        int sensorId);
}
