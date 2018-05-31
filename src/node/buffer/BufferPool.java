package node.buffer;

import model.record.Record;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BufferPool {
    private static List<Record> bufferPool = new ArrayList<>();

    private static Set<Record> set = new HashSet<>();

    public static synchronized List<Record> generateBlockRecord(){
        List<Record> result = new ArrayList<>(bufferPool);
        bufferPool = new ArrayList<>();
        return result;
    }

    public static synchronized void add(Record record){
        bufferPool.add(record);
        set.add(record);
    }

    public static boolean isContain(Record record){
        return set.contains(record);
    }
}