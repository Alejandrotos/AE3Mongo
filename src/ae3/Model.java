package ae3;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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
			collecioImatges = database.getCollection("img");
			collecioUsuaris = database.getCollection("usuarios");

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

	public static void iniciarPartida() {
		iniciPartida = Instant.now();
	}

	public static void calcularDuracioEnSegons() {
		duracioTotal = Duration.between(iniciPartida, Instant.now()).getSeconds();
	}

	public Instant getIniciPartida() {
		return iniciPartida;
	}

	public void extraureImatge(String string64) throws IOException {
		byte[] btDataFile = Base64.decodeBase64(string64);

		// Lee la imagen desde bytes
		BufferedImage imatge = ImageIO.read(new ByteArrayInputStream(btDataFile));

		// Escala la imagen
		Image scaledImage = imatge.getScaledInstance(-1, 400, Image.SCALE_SMOOTH);

		// Convierte la imagen a BufferedImage
		BufferedImage bufferedImage = new BufferedImage(scaledImage.getWidth(null), scaledImage.getHeight(null),
				BufferedImage.TYPE_INT_RGB);

		// Dibuja la imagen en el BufferedImage
		Graphics g = bufferedImage.createGraphics();
		g.drawImage(scaledImage, 0, 0, null);
		g.dispose();

		// Guarda la imagen en un archivo (opcional)
		ImageIO.write(bufferedImage, "jpg", new File("imatge.jpg"));
	}

	private void selectImatgeB64() throws IOException {
		MongoCursor<Document> cursor = collecioImatges.find().iterator();

		while (cursor.hasNext()) {
			Document document = cursor.next();
			String base64String = document.getString("base64");
			extraureImatge(base64String);
		}

		cursor.close();
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
