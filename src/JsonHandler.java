import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonHandler {

    public static String entry(InputStream in) {
        try {
            JsonReader reader = createJsonReader(in);
            ArrayList<JsonValue> arrayList = readStream(reader);
            String output = decode(arrayList);
            System.out.println(output);
            return output;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //configure GSON reader
    public static JsonReader createJsonReader(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        reader.setLenient(true);
        return reader;
    }

    public static void writeJsonStream(OutputStream out, String messages) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("    ");
        writer.setLenient(true);
    }

    //Parses stream for JSON
    public static ArrayList<JsonValue> readStream(JsonReader reader) throws IOException {
        ArrayList<JsonValue> array = new ArrayList<>();
        while (reader.hasNext()) {
            JsonValue value = new Gson().fromJson(reader, JsonValue.class);
            array.add(value);
            //System.out.println(decode(value));
        }
        return array;
    }

    //Converts a list of JsonValue to a string of "┘", "└", "┐", and/or "┌"
    private static String decode(ArrayList<JsonValue> deserialized) {
        HashMap<String, String> map = new HashMap();
        map.put("UPLEFT", "┘");
        map.put("UPRIGHT", "└");
        map.put("DOWNLEFT", "┐");
        map.put("DOWNRIGHT", "┌");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String[] list = new String[deserialized.size()];

        for (int i = 0; i < deserialized.size(); i ++) {
            JsonValue value = deserialized.get(i);
            System.out.println(value.getVertical() + value.getHorizontal());
            String decodedValue = map.get(value.getVertical() + value.getHorizontal());
            list[i] = decodedValue;
        }
        String output = gson.toJson(list);
        return output;
    }
}