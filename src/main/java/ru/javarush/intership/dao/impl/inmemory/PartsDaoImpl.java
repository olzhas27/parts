//package ru.javarush.intership.dao.impl.inmemory;
//
//import ru.javarush.intership.dao.PartsDao;
//import ru.javarush.intership.model.Part;
//import ru.javarush.intership.model.PartsFactory;
//
//import java.util.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class PartsDaoImpl implements PartsDao {
//    private static final Map<Integer, Part> parts = new HashMap<Integer, Part>() {
//        {
//            AtomicInteger x = new AtomicInteger(0);
//            put(x.incrementAndGet(), PartsFactory.newPart("материнская плата",  true , 3));
//            put(x.incrementAndGet(), PartsFactory.newPart("звуковая карта",  false , 2));
//            put(x.incrementAndGet(), PartsFactory.newPart("процессор",  true , 2));
//            put(x.incrementAndGet(), PartsFactory.newPart("подсветка корпуса",  false , 0));
//            put(x.incrementAndGet(), PartsFactory.newPart("HDD диск",  false , 1));
//            put(x.incrementAndGet(), PartsFactory.newPart("корпус",  true , 10));
//            put(x.incrementAndGet(), PartsFactory.newPart("память",  true , 10));
//            put(x.incrementAndGet(), PartsFactory.newPart("SSD диск",  true , 15));
//            put(x.incrementAndGet(), PartsFactory.newPart("видеокарта",  false , 7));
//        }
//    };
//
//    public List<Part> getAllParts() {
//        return Collections.unmodifiableList(new ArrayList<>(parts.values()));
//    }
//}
