package m2m.repo;

import m2m.repo.entities.SensorStatistics;

public interface SensorsStatisticsRepo
{
        SensorStatistics getSensorStatistics(int sensorId, long from, long to);
}
