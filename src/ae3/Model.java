package ae3;

import org.apache.commons.codec.binary.Base64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Sorts;

import static com.mongodb.client.model.Filters.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Model {
	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> collecioRecords;
	private static MongoCollection<Document> collecioImatges;
	private static MongoCollection<Document> collecioUsuaris;
	private static ArrayList<String> rutasDeImages = new ArrayList<>();

	private static Timer timer;
	private static int duracioTotal;
	private static String timestamp;

	public ArrayList<String> getRutaDeImages() {
		return rutasDeImages;
	}

	/**
	 * Establece la conexión con la base de datos MongoDB utilizando la
	 * configuración especificada en el archivo "conexion.json".
	 * 
	 * @throws IOException Se lanza si hay un error durante la lectura del archivo
	 *                     JSON.
	 */
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

	/**
	 * Inserta un nuevo registro de partida en la colección de registros de la base
	 * de datos MongoDB.
	 * 
	 * @param usuari     Nombre de usuario asociado al registro de la partida.
	 * @param dificultat Nivel de dificultad de la partida.
	 */
	public void insertRecord(String usuari, int dificultat) {
		Document record = new Document();
		record.append("usuario", usuari).append("dificultad", dificultat).append("timestamp", timestamp)
				.append("duracion", duracioTotal);

		collecioRecords.insertOne(record);
	}

	/**
	 * Selecciona y devuelve una lista de registros en formato JSON desde la
	 * colección de registros.
	 * 
	 * @return ArrayList de objetos JSON que representan los registros.
	 */
	public ArrayList<JSONObject> selectRecords() {
		ArrayList<JSONObject> llistaRecordsObjects = new ArrayList<JSONObject>();

		MongoCursor<Document> cursor = collecioRecords.find().iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			llistaRecordsObjects.add(obj);
		}
		return llistaRecordsObjects;
	}

	/**
	 * Recupera registros de la base de datos y los formatea como una cadena para
	 * ser insertada en un JTextArea.
	 * 
	 * @param usuari     Nombre de usuario asociado al registro de la partida.
	 * @param dificultat Nivel de dificultad de la partida.
	 * @return String que representa los registros formateados para JTextArea.
	 */
	public static String insertRecordEnJTextArea(String usuari, int dificultat) {
		FindIterable<Document> results = collecioRecords.find();

		StringBuilder recordEnJtextPane = new StringBuilder();

		for (Document result : results) {
			recordEnJtextPane.append(result.getString("usuario")).append(result.getInteger("dificultad"))
					.append(result.getString("timestamp"));

			Number duracion = result.get("duracion", Number.class);

			if (duracion != null) {
				recordEnJtextPane.append(duracion.longValue());
			}

			recordEnJtextPane.append("\n");
		}
		return recordEnJtextPane.toString();
	}

	/**
	 * Inserta un nuevo usuario en la colección de usuarios de la base de datos
	 * MongoDB, verificando previamente si el usuario ya existe.
	 * 
	 * @param usuari Nombre de usuario a ser insertado.
	 * @param pass   Contraseña asociada al nuevo usuario.
	 * @return true si la inserción es exitosa, false si el usuario ya existe.
	 */
	public static boolean insertUsuari(String usuari, String pass) {
		conexioDBMongo();
		Document user = new Document();
		Bson filter = Filters.eq("user", usuari);
		Document result = collecioUsuaris.find(filter).first();
		if (result != null) {
			return false;
		} else {
			user.append("user", usuari).append("pass", hashPassword(pass));
			collecioUsuaris.insertOne(user);
			return true;
		}
	}

	/**
	 * Verifica las credenciales de un usuario en la base de datos MongoDB.
	 * 
	 * @param usuari Nombre de usuario a ser verificado.
	 * @param pass   Contraseña asociada al usuario.
	 * @return true si las credenciales son válidas, false si el usuario no existe o
	 *         la contraseña no coincide.
	 */
	public static boolean iniciUsuari(String usuari, String pass) {
		conexioDBMongo();
		Document user = new Document();
		Bson filter = Filters.eq("user", usuari);
		Document result = collecioUsuaris.find(filter).first();
		Bson filterContra = Filters.eq("pass", hashPassword(pass));
		Document resultContra = collecioUsuaris.find(filterContra).first();
		if (result != null && resultContra != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Inicia un contador de duración de la partida, generando un timestamp y
	 * configurando un temporizador para incrementar la duración total cada segundo.
	 */
	public void iniciarContadorPartida() {
		generateTimestamp();
		System.out.print("Timestamp generado: " + timestamp);
		duracioTotal = 0;
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				duracioTotal++;
			}
		};
		timer = new Timer(1000, actionListener);
		timer.start();
	}

	/**
	 * Detiene el contador de duración de la partida si está en ejecución, e imprime
	 * información adicional.
	 */
	public void detindreContador() {
		if (timer != null && timer.isRunning()) {
			timer.stop();
			// Lógica adicional cuando detienes el contador (puedes ajustarla según tus
			// necesidades)
			System.out.println("Contador detenido. Tiempo total: " + duracioTotal + " segundos");
		}
	}

	/**
	 * Extrae imágenes de la base de datos, las convierte de formato Base64 a
	 * archivos de imagen y las guarda en la carpeta "img".
	 * 
	 * @throws IOException Si ocurre un error de E/S al procesar las imágenes.
	 */
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

	/**
	 * Verifica si la duración total de la partida es menor que la duración de un
	 * récord existente.
	 * 
	 * @param duracioRecord Duración del récord existente a ser comparada.
	 * @return true si la duración total es menor que la duración del récord, false
	 *         en caso contrario.
	 */
	public boolean newRecord(int duracioRecord) {
		boolean newRecord = false;
		if (duracioTotal < duracioRecord)
			newRecord = true;

		return newRecord;
	}

	/**
	 * Selecciona y devuelve la duración del récord más bajo para la dificultad
	 * especificada.
	 * 
	 * @param dificultat Nivel de dificultad para el cual se busca el récord.
	 * @return Duración del récord más bajo para la dificultad especificada, o 0 si
	 *         no hay récord disponible.
	 */
	public int selectBestRecord(int dificultat) {
		Bson filtro = Filters.eq("dificultad", dificultat);
		Bson orden = Sorts.ascending("duracion");

		Document record = collecioRecords.find(filtro).sort(orden).first();
		JSONObject obj = new JSONObject(record.toJson());
		int duracioRecord = obj.getInt("duracion");

		return duracioRecord;
	}

	/**
	 * Selecciona y devuelve una lista de objetos JSON representando imágenes desde
	 * la colección de imágenes.
	 * 
	 * @return ArrayList de objetos JSON que representan las imágenes.
	 * @throws IOException Si ocurre un error de E/S al procesar las imágenes.
	 */
	private ArrayList<JSONObject> selectImatgesJSon() throws IOException {
		ArrayList<JSONObject> llistaImatgesJson = new ArrayList<JSONObject>();
		MongoCursor<Document> cursor = collecioImatges.find().iterator();

		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			llistaImatgesJson.add(obj);
		}
		return llistaImatgesJson;
	}

	/**
	 * Genera un timestamp con el formato "yyyyMMdd_HHmmss" y lo almacena en la
	 * variable de clase 'timestamp'.
	 */
	private static void generateTimestamp() {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		LocalDateTime fechaActual = LocalDateTime.now();
		timestamp = formatoFecha.format(fechaActual);
	}

	/**
	 * Genera un hash SHA-256 a partir de la contraseña proporcionada.
	 * 
	 * @param password Contraseña a ser hasheada.
	 * @return Representación hexadecimal del hash SHA-256.
	 */
	private static String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes());

			// Convertir el hash a una representació hexadecimal
			StringBuilder hexHash = new StringBuilder();
			for (byte b : hash) {
				hexHash.append(String.format("%02x", b));
			}

			return hexHash.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
