package ae3;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.TimeZone;

import javax.swing.JOptionPane;
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
		Bson filter = Filters.eq("user", userString);
		Document result = collecioUsuaris.find(filter).first();
        if (result != null) {
        	JOptionPane.showMessageDialog(null, "Usuari" + userString + "ya creat");
        } else {
            user.append("user", userString).append("pass", hashPassword(pass));
            collecioUsuaris.insertOne(user);
            JOptionPane.showMessageDialog(null, "Usuari fet" + userString);
        }
	}
	
	public static void iniciUsuari(String userString, String pass) {
		Document user = new Document();
		Bson filter = Filters.eq("user", userString);
		Document result = collecioUsuaris.find(filter).first();
		Bson filterContra = Filters.eq("pass", pass);
		Document resultContra = collecioUsuaris.find(filterContra).first();
        if (result != null && resultContra != null) {
        	JOptionPane.showMessageDialog(null, "Usuari i contrasenya correctes");
        } else {
            JOptionPane.showMessageDialog(null, "Usuari o contrasenya incorrectes");
        }
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
	
	public void extraureImatge() {
		byte[] btDataFile = Base64.decodeBase64(string64);
		BufferedImage imatge = ImageIO.read(new ByteArrayInputStream(btDataFile));
		Image imatge = imatge.getScaledInstance(-1, 400, java.awt.Image.SCALE_SMOOTH);
		ImageIO.write(imatge, "jpg", new File ("imatge.jpg"));
	}

	private static void selectImatgeB64() {
		Document imatges = new Document();
		record.append("usuario", usuari).append("dificultad", dificultat).append("timestamp", generateTimestamp())
				.append("duracion", duracioTotal);

		collecioRecords.insertOne(record);
	}
	private static String generateTimestamp() {
		String formato = "yyyyMMdd_HHmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(formato);

		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		timestamp = sdf.format(Date.from(iniciPartida));

		return timestamp;
	}
	
	private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            // Convertir el hash a una representación hexadecimal
            StringBuilder hexHash = new StringBuilder();
            for (byte b : hash) {
                hexHash.append(String.format("%02x", b));
            }

            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Manejar la excepción apropiadamente en tu aplicación
            return null;
        }
    }

}
