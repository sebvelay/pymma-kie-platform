package org.chtijbug.guvnor.uberfire.security;

import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Utils {
    public static Document getDocumentFromRef(DBRef dbRef, MongoDatabase database){
        if (dbRef!=null) {
            MongoCollection<Document> userRolesCollection = database.getCollection(dbRef.getCollectionName());
            Document document = userRolesCollection.find(eq("_id", dbRef.getId())).first();
            return document;
        }
        return null;
    }
}
