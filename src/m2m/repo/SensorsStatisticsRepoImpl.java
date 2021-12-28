package m2m.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import m2m.repo.entities.SensorDoc;
import m2m.repo.entities.SensorStatistics;

public class SensorsStatisticsRepoImpl implements SensorsStatisticsRepo
{

        @Autowired
        MongoTemplate template;

        @Override
        public SensorStatistics getSensorStatistics(int sensorId, long from, long to)
        {
                MatchOperation matchOp = Aggregation
                                .match(Criteria.where("id").is(sensorId).and("_id").gt(from).lt(to));
                GroupOperation groupOp = Aggregation.group("id").first("id").as("sensorId").avg("avgValue")
                                .as("avgValue").min("avgValue").as("minValue").max("avgValue").as("maxValue");
                Aggregation pipe = Aggregation.newAggregation(matchOp, groupOp);
                AggregationResults <SensorStatistics> result = template.aggregate(pipe, SensorDoc.class,
                                SensorStatistics.class);
                return result.getUniqueMappedResult();

                // Criteria cr = Criteria.where("id").is(sensorId).and("_id").gt(from).lt(to);
                // Query query = new Query();
                // query.addCriteria(cr);
                // List<SensorDoc> list = template.find(query, SensorDoc.class);
                // if (list.size()==0)
//              return null;
                // IntSummaryStatistics statistic = list.stream().mapToInt(e ->
                // e.getAvgValue()).summaryStatistics();
                // return new SensorStatistic(sensorId, statistic.getMin(), statistic.getMax(),
                // (int) statistic.getAverage());
        }

}
