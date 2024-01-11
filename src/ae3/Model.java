package ae3;

import org.apache.commons.codec.binary.Base64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Model {
	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> collecioRecords;
	private static MongoCollection<Document> collecioImatges;
	private static MongoCollection<Document> collecioUsuaris;
	private static ArrayList<String> rutasDeImages = new ArrayList<>();
	private static Instant iniciPartida;
	private static long duracioTotal;
	private static String timestamp;

	public Instant getIniciPartida() {
		return iniciPartida;
	}

	public ArrayList<String> getRutaDeImages() {
		return rutasDeImages;
	}

	public static void conexioDBMongo() {
		try {
			
			String jsonString = new String(Files.readAllBytes(Paths.get("conexion.json")));
		
			JSONObject configDoc = new JSONObject(jsonString);
			String ip = configDoc.getString("ip");
			int port = configDoc.getInt("port");
			
			String databaseName = configDoc.getString("database");
			
			mongoClient = MongoClients.create(String.format("mongodb://%s:%d", ip, port));
			database = mongoClient.getDatabase(databaseName);
			
			collecioRecords = database.getCollection("records");
			collecioImatges = database.getCollection("img");
			collecioUsuaris = database.getCollection("usuarios");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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

	public void extraureImatges() {
		try {
			ArrayList<JSONObject> objImatges = selectImatgesJSon();

			File carpetaImg = new File("img");
			if (!carpetaImg.exists()) {
				carpetaImg.mkdir();
			}

			for (int i = 0; i < objImatges.size(); i++) {
				String string64 = objImatges.get(i).getString("base64");
				String id = objImatges.get(i).getString("id");
				String rutaFile = "img/" + id;
				rutasDeImages.add(rutaFile);

				byte[] btDataFile = Base64.decodeBase64(string64);

				BufferedImage imatge = ImageIO.read(new ByteArrayInputStream(btDataFile));
				Image imatgeEscalada = imatge.getScaledInstance(-1, 400, Image.SCALE_SMOOTH);

				File imatgeFile = new File(rutaFile);
				ImageIO.write(imatge, "jpg", imatgeFile);
			}
		} catch (IOException e) {
			System.err.println(e);
		}

	}

	private ArrayList<JSONObject> selectImatgesJSon() throws IOException {
		ArrayList<JSONObject> llistaImatgesJson = new ArrayList<JSONObject>();
		MongoCursor<Document> cursor = collecioImatges.find().iterator();

		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			llistaImatgesJson.add(obj);
		}
		return llistaImatgesJson;
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
