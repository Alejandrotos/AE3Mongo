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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.TimeZone;

public class Model {
	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> collecioRecords;
	private static MongoCollection<Document> collecioImatges;
	private static MongoCollection<Document> collecioUsuaris;
	private static Instant iniciPartida;
	private static long duracioTotal;
	private static String timestamp;

	public static void conexioDBMongo() {
		try {

			String jsonConfig = new String(Files.readAllBytes(Paths.get("conexio.json")));

			Document configDoc = Document.parse(jsonConfig);

			String ip = configDoc.getString("ip");
			int port = configDoc.getInteger("port");
			String databaseName = configDoc.getString("database");

			mongoClient = MongoClients.create(String.format("mongodb://%s:%d", ip, port));
			database = mongoClient.getDatabase(databaseName);

			collecioRecords = database.getCollection("records");
			collecioImatges = database.getCollection("imatges");
			collecioUsuaris = database.getCollection("usuaris");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void insertRecord(String usuari, int dificultat) {
		Document record = new Document();
		record.append("usuario", usuari).append("dificultad", dificultat).append("timestamp", generateTimestamp())
				.append("duracion", duracioTotal);

		collecioRecords.insertOne(record);
	}

	public static void insertUsuari(String userString, String pass) {
		Document user = new Document();
		user.append("user", userString).append("pass", pass);

		collecioUsuaris.insertOne(user);
	}

	public static void iniciarPartida() {
		iniciPartida = Instant.now();
	}

	public static void calcularDuracioEnSegons() {
		duracioTotal = Duration.between(iniciPartida, Instant.now()).getSeconds();
	}

	public Instant getIniciPartida() {
		return iniciPartida;
	}

	private static String generateTimestamp() {
		String formato = "yyyyMMdd_HHmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(formato);

		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		timestamp = sdf.format(Date.from(iniciPartida));

		return timestamp;
	}

}
