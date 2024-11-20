package persistence;

import org.json.JSONObject;

// Source: In class Workroom App Example
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
