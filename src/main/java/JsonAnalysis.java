import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonAnalysis {

    public static List<ItemRecord> analysis(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode listNode = rootNode.path("data").path("list");

        List<ItemRecord> itemRecords = new ArrayList<>();
        for (JsonNode itemNode : listNode) {
            ItemRecord itemRecord = objectMapper.treeToValue(itemNode, ItemRecord.class);
            itemRecords.add(itemRecord);
        }
        System.out.println(itemRecords.size());
        for (ItemRecord itemRecord : itemRecords) {
            System.out.println(itemRecord);
        }
        return itemRecords;
    }
}
