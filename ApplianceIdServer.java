
/**
 * 
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Generates appliance ids.
 * 
 *
 */
public class ApplianceIdServer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idCounter;
    private static ApplianceIdServer server;

    /*
     * Private constructor for singleton pattern
     * 
     */
    private ApplianceIdServer() {
        idCounter = 1;
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static ApplianceIdServer instance() {
        if (server == null) {
            return (server = new ApplianceIdServer());
        } else {
            return server;
        }
    }

    /**
     * Getter for id
     * 
     * @return id of the appliance
     */
    public int getId() {
        return idCounter++;
    }

    /**
     * String form of the collection
     * 
     */
    @Override
    public String toString() {
        return ("IdServer" + idCounter);
    }

    /**
     * Retrieves the server object
     * 
     * @param input
     *            inputstream for deserialization
     */
    public static void retrieve(ObjectInputStream input) {
        try {
            server = (ApplianceIdServer) input.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

}
