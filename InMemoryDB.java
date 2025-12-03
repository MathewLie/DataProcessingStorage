import java.util.HashMap;
import java.util.Map;

public class InMemoryDB {
    // Create in-memory key-value database using hashmaps to store with key and
    // value - where the key is a string and the value is an int
    private Map<String, Integer> mainDB = new HashMap<>();
    private Map<String, Integer> tempDB = null;

    // Functions provided by assignment document in Fig 1:

    // get(key) will return the value associated with the key or null if the key
    // doesn't exist.
    // Can be called anytime even when a transaction is not in progress
    // get() won't see changes to keys in a transaction until the transaction is
    // committed
    public Integer get(String key) { // ← Changed to Integer to return null
        return mainDB.get(key); // ← Returns actual null when key missing
    }

    // put(key, val) will create a new key with the provied value if a key doesn't
    // exist. Otherwise it will update the value of an existing key.
    // If put(key, val) is called when a transaction is not in progress - throw an
    // exception
    public void put(String key, int val) {
        if (tempDB == null) {
            throw new IllegalStateException();
        }
        tempDB.put(key, val);
    }

    // begin_transaction() will start a new transaction
    // Only one transaction can exist at a time - if transaction already in progress
    // and it's called then throw an exception
    public void begin_transaction() {
        if (tempDB != null) {
            throw new IllegalStateException();
        }
        tempDB = new HashMap<>();
    }

    // commit() applies changes made within the transaction to the main state.
    // Allowing any future gets() to "see" the changes made within the transaction
    // transaction ends with commit is called
    public void commit() {
        if (tempDB == null) {
            throw new IllegalStateException();
        }
        mainDB.putAll(tempDB);
        tempDB = null;
    }

    // rollback() shoudl abort all the changes made within the transaction and
    // everything should go back to the way it was before
    // Transaction will end when rollback is called
    public void rollback() {
        if (tempDB == null) {
            throw new IllegalStateException();
        }
        tempDB = null;
    }

    // Main method to test the functionality based on Fig 2
    public static void main(String[] args) {
        InMemoryDB inmemoryDB = new InMemoryDB();

        // Should return null, because A doesn't exist in the DB yet.
        Integer result = inmemoryDB.get("A");
        System.out.println(result == null ? "null" : result);

        // Should throw an error because a transaction is not in progress
        try {
            inmemoryDB.put("A", 5);
        } catch (IllegalStateException e) {
            System.out.println("Error");
        }

        // Starts a new transaction
        inmemoryDB.begin_transaction();

        // Set’s value of A to 5, but its not committed yet
        inmemoryDB.put("A", 5);

        // Should return null, because updates to A are not committed yet
        result = inmemoryDB.get("A");
        System.out.println(result == null ? "null" : result);

        // Update A’s value to 6 within the transaction
        inmemoryDB.put("A", 6);

        // Commits the open transaction
        inmemoryDB.commit();

        // Should return 6, that was the last value of A to be committed
        result = inmemoryDB.get("A");
        System.out.println(result == null ? "null" : result);

        // Throws an error, because there is no open transaction
        try {
            inmemoryDB.commit();
        } catch (IllegalStateException e) {
            System.out.println("Error");
        }

        // Throws an error because there is no ongoing transaction
        try {
            inmemoryDB.rollback();
        } catch (IllegalStateException e) {
            System.out.println("Error");
        }

        // Should return null because B does not exist in the database
        result = inmemoryDB.get("B");
        System.out.println(result == null ? "null" : result);

        // Starts a new transaction
        inmemoryDB.begin_transaction();

        // Set key B’s value to 10 within the transaction
        inmemoryDB.put("B", 10);

        // Rollback the transaction - revert any changes made to B
        inmemoryDB.rollback();

        // Should return null because changes to B were rolled back
        result = inmemoryDB.get("B");
        System.out.println(result == null ? "null" : result);
    }
}
