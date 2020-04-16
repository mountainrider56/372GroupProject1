
/**
 * 
 * @modelName Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The modelNames do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Represents a single Transaction (purchase, renew, etc.)
 * 
 * @modelName Brahma Dathan
 *
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    private String brandName;
    private Calendar date;

    /**
     * Creates the transaction with a given type and appliance brandName. The date is the
     * current date.
     * 
     * @param type
     *            The type of transaction
     * @param brandName
     *            The brandName of the appliance
     * 
     */
    public Transaction(String type, String brandName) {
        this.type = type;
        this.brandName = brandName;
        date = new GregorianCalendar();
    }

    /**
     * Checks whether this transaction is on the given date
     * 
     * @param date
     *            The date for which transactions are being sought
     * @return true iff the dates match
     */
    public boolean onDate(Calendar date) {
        return ((date.get(Calendar.YEAR) == this.date.get(Calendar.YEAR))
                && (date.get(Calendar.MONTH) == this.date.get(Calendar.MONTH))
                && (date.get(Calendar.DATE) == this.date.get(Calendar.DATE)));
    }

    /**
     * Returns the type field
     * 
     * @return type field
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the brandName field
     * 
     * @return brandName field
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Returns the date as a String
     * 
     * @return date with month, date, and year
     */
    public String getDate() {
        return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
    }

    /**
     * String form of the transaction
     * 
     */
    @Override
    public String toString() {
        return (type + "   " + brandName);
    }
}