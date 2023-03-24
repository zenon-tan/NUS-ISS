package day28.workshop.utils;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

public class CustomAggregation implements AggregationOperation {

    private String jsonOperation;

    public CustomAggregation(String jsonOperation) {

        this.jsonOperation = jsonOperation;
    }

    @Override
    public Document toDocument(AggregationOperationContext context) {
        
        return context.getMappedObject(Document.parse(jsonOperation));
    }

    
}
