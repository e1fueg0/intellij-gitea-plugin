/*
 * Copyright © 2019 by elfuego.biz
 */
package biz.elfuego.idea.issues.gitea.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Roman Pedchenko <elfuego@elfuego.biz>
 * @date 2018.06.30
 */
public class Utils {
    private static final String OK = "ok";
    private static final String DATA = "data";

    public static String getString(JsonObject object, String fieldName, String defaultValue) {
        String result = defaultValue;
        if (object.has(fieldName) && object.get(fieldName).isJsonPrimitive()) {
            result = object.get(fieldName).getAsJsonPrimitive().getAsString();
        }

        return result;
    }

    public static Date getDate(JsonObject object, String fieldName) {
        Date result = null;
        if (object.has(fieldName) && object.get(fieldName).isJsonPrimitive()) {
            result = parseDateISO8601(object.get(fieldName).getAsJsonPrimitive().getAsString());
        }
        return result;
    }

    private static Date parseDateISO8601(String input) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        if (input.endsWith("Z")) {
            input = input.substring(0, input.length() - 1) + "GMT-00:00";
        } else {
            int inset = 6;

            String s0 = input.substring(0, input.length() - inset);
            String s1 = input.substring(input.length() - inset);

            input = s0 + "GMT" + s1;
        }
        try {
            return df.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonObject getObject(JsonElement el) throws Exception {
        if (el.isJsonObject()) {
            return el.getAsJsonObject();
        }
        throw new Exception(Consts.ERROR + ", getObject: " + el.toString());
    }

    public static JsonObject getObject(JsonObject object, String fieldName) throws Exception {
        if (object.has(fieldName) && object.get(fieldName).isJsonObject()) {
            return object.get(fieldName).getAsJsonObject();
        }
        throw new Exception(Consts.ERROR + ", getObject2: " + object.toString());
    }

    public static JsonArray getArray(JsonElement el) throws Exception {
        if (el.isJsonArray()) {
            return el.getAsJsonArray();
        }
        throw new Exception(Consts.ERROR + ", getArray: " + el.toString());
    }

    public static JsonArray getOkData(JsonElement el) throws Exception {
        if (el.isJsonObject()) {
            JsonObject obj = el.getAsJsonObject();
            if (obj.has(OK) && obj.get(OK).isJsonPrimitive() && obj.get(OK).getAsBoolean()) {
                if (obj.has(DATA) && obj.get(DATA).isJsonArray())
                return obj.get(DATA).getAsJsonArray();
            }
        }
        throw new Exception(Consts.ERROR + ", getOkData: " + el.toString());
    }
}
