import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonHandler {

    public static String entry(InputStream in) {
        try {
            JsonReader reader = createJsonReader(in);
            ArrayList<Map<String, Object>> arrayList = readStream(reader);
            String output = decode(arrayList);
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
    public static ArrayList<Map<String, Object>>  readStream(JsonReader reader) throws IOException {
        ArrayList<Map<String, Object>> array = new ArrayList<>();
        // pretty print
        Gson gson = new GsonBuilder().create(); //.serializeNulls().setPrettyPrinting().create();
        while (reader.hasNext()) {
            JsonElement json = gson.fromJson(reader, JsonElement.class);
            Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
            System.out.println(map);
            //Other.JsonValue value = new Gson().fromJson(reader, Other.JsonValue.class);
            array.add(map);
        }
        return array;
    }

    //Converts a list of Map<String, Object> to a string of "┘", "└", "┐", and/or "┌"
    private static String decode(ArrayList<Map<String, Object>> deserialized) {
        HashMap<String, String> map = new HashMap();
        map.put("UPLEFT", "┘");
        map.put("UPRIGHT", "└");
        map.put("DOWNLEFT", "┐");
        map.put("DOWNRIGHT", "┌");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String[] list = new String[deserialized.size()];

        for (int i = 0; i < deserialized.size(); i ++) {
            Map<String, Object> value = deserialized.get(i);
            String decodedValue = map.get( (String) value.get("vertical") + (String) value.get("horizontal"));
            list[i] = decodedValue;
        }
        String output = gson.toJson(list);
        return output;
    }
}
