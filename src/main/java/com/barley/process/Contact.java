package com.barley.process;

import com.barley.dao.MongoDb;
import com.barley.model.Query;
import com.barley.util.Utilities;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contact {
    private MongoDb mongoDb = MongoDb.getInstance();

    public Object handleGet(Request request, Response response) {
        return renderContact();
    }

    public Object handlePost(Request request, Response response) {
        Query query = Query.builder()
                .ip(request.ip())
                .email(request.queryParams("email"))
                .name(request.queryParams("name"))
                .query(request.queryParams("query"))
                .state(Query.State.NEW)
                .dateCreated(Utilities.now())
                .build();
        mongoDb.insertQuery(query);
        return renderContact();
    }

    private Map getContactMap() {
        Map map = new HashMap();
        map.put("activeNav", "contact");
        map.put("title", "Query Us");
        map.put("text", "We are happy to address any concern you may have or will try to provide any help we can. Please submit your query below.");
        map.put("displayList", "none");
        return map;
    }

    private Object renderContact() {
        Map contact = getContactMap();
        return Utilities.render(contact, "velocity/contact.vm");
    }

    public Object handleRetreive(Request request, Response response) {
        String id = request.params(":id");
        System.out.println(id);
        List queries = mongoDb.getQueries(request.ip());
        int size = queries.size();
        Map contact = getContactMap();
        if (size > 0) {
            contact.put("displayList", "");
            contact.put("count", size);
            contact.put("queries", queries);
        }
        return Utilities.render(contact, "velocity/contact.vm");
    }
}
