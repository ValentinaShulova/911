package m2m.service;

import java.time.LocalDateTime;
import java.util.List;

import m2m.repo.entities.SensorStatistics;

public interface IBackOffice
{
        List <Integer> getIdBigValues(LocalDateTime from, LocalDateTime to, int sensorValue);
    //  ���� ������ id, ���������� ������� �� ��������� ���������� ������� ��� ���� ��� sensorValue
        
        List <Integer> getIdSmallValues(LocalDateTime from, LocalDateTime to, int sensorValue); 
        
        List <LocalDateTime> getDatesBigValues(int sensorId, LocalDateTime from, 
                LocalDateTime to, int sensorValue);
    //  ���� ���, ����� ��������� ���������� ���������� ������� � ���������� id
        
        List <LocalDateTime> getDatesSmallValues(int sensorId, LocalDateTime from, 
                LocalDateTime to, int sensorValue);
        
        SensorStatistics getSensorStatistics(int sensorId, LocalDateTime from, 
                LocalDateTime to);
}
