package ae3;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Model {
	private MongoClient mongoClient;
	private static MongoDatabase database;

	public MongoDatabase ConexioDBMongo() {
		try {

			String jsonConfig = new String(Files.readAllBytes(Paths.get("conexio.json")));

			Document configDoc = Document.parse(jsonConfig);

			String ip = configDoc.getString("ip");
			int port = configDoc.getInteger("port");
			String databaseName = configDoc.getString("database");

			mongoClient = MongoClients.create(String.format("mongodb://%s:%d", ip, port));
			database = mongoClient.getDatabase(databaseName);

			return database;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static MongoCollection<Document> getCollection(String NomCollection) {
		return database.getCollection(NomCollection);
	}
}
