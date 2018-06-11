package dsa.dsa_app.map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import dsa.dsa_app.map.celdas.Celda;

public class CeldaDeserializer extends StdDeserializer<Celda> {

    protected CeldaDeserializer(Class<?> vc) {
        super(vc);
    }

    public CeldaDeserializer(){
        this(null);
    }

    @Override
    public Celda deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectNode on = jsonParser.getCodec().readTree(jsonParser);
        String nombreCelda = on.findValue("nombre").asText();
        try {
            Class c = Class.forName("celdas." + nombreCelda);
            switch (nombreCelda) {
                case "Cofre":
                    String tipo = on.findValue("tipo").asText();
                    Field f = c.getDeclaredField("tipo");
                    Object o = Arrays.stream(f.getType().getDeclaredFields()).filter(x -> x.getName().equals(tipo))
                            .findFirst().get().get(f);
                    Celda cel = (Celda) c.getDeclaredConstructor(f.getType()).newInstance(o);
                    return cel;
                default:
                Constructor con = c.getDeclaredConstructor(null);
                return (Celda) con.newInstance(null);
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
