package observer;
import java.util.Stack;
/**
 * This class main target is to add the "Undo" function
 * to the mighty StringBuilder - (SB).
 *
 * @author Tal.M / Yann.C
 * @version 0.1 01.11.2022
 */

public class UndoableStringBuilder {

    /**
     * The two main object that integrated in every SB function and the constructor
     */
    private Stack<String> st;
    private StringBuilder sb;

    /**
     * Constructs and initializes a UndoableStringBuilder without input parameters
     */
    public UndoableStringBuilder() {
        this.st = new Stack<String>();
        this.sb = new StringBuilder();
    }

    /**
     * This function get access to the stack for tests in JUNIT
     * @return
     */
    public Stack<String> getSt() {
        return st;
    }

    /**
     * This function appends the specified string to this character sequence
     *
     * @param str - the specified string
     * @return the UndoableStringBuilder object after the change made by the function
     */
    public  UndoableStringBuilder append(String str) { // there is no need to catch exception here
        st.push(sb.append(str).toString());           // we can have only compilations errors
        return this;
    }

    /**
     * This function removes the characters in a substring sequence
     * the sequence begin at start to end-1
     * if the start equal to end no change are made
     *
     * @param start - the index of the beginning
     * @param end   - the index of the ending
     * @return the UndoableStringBuilder object after the change made by the function
     */
    public UndoableStringBuilder delete(int start, int end) {// we want to catch the exception if :
            try {       // start is negative ,greater than length(),or greater than end
                st.push(sb.delete(start, end).toString());
            } catch (StringIndexOutOfBoundsException err) {
                System.out.println(err);
                err.printStackTrace();
            }
        return this;
    }

    /**
     * This function inserts the specified string into this character sequence
     *
     * @param offset - the offset to insert the specified string
     * @param str    - the specified string
     * @return the UndoableStringBuilder object after the change made by the function
     */
    public UndoableStringBuilder insert(int offset, String str) { // we want to catch the exception if :
        try {       // the offset is not correct
            st.push(sb.insert(offset, str).toString());
        } catch (StringIndexOutOfBoundsException err) {
            System.out.println(err);
            err.printStackTrace();
        }
        return this;
    }

    /**
     * This function replaces the characters in a substring sequence
     * from start to end-1
     *
     * @param start - The beginning index, inclusive
     * @param end   - The ending index, exclusive
     * @param str   - String that will replace previous contents
     * @return the UndoableStringBuilder object after the change made by the function
     */
    public UndoableStringBuilder replace(int start, int end, String str) { // we want to catch the exception if :
        try {       // start is negative, greater than length(), or greater than end
            st.push(sb.replace(start, end, str).toString());
        } catch (StringIndexOutOfBoundsException err) {
            System.out.println(err);
            err.printStackTrace();
        }
        return this;
    }

    /**
     * This function causes this character sequence to be replaced by
     * the reverse of it
     *
     * @return the UndoableStringBuilder object after the change made by the function
     */
    public UndoableStringBuilder reverse() { // there is no need to catch exception here
        st.push(sb.reverse().toString());
        return this;
    }

    /**
     * This function erases the last change done to the object,
     * reverting it to an older state
     * Throw an exception if my stack is empty
     *
     * @return the UndoableStringBuilder object after the change made by the function
     */
    public void undo() throws Exception {
        if (st.size() == 1) { // if the size of my stack is equal to 1 there isn't an old version of him
            throw new Exception(" There is no old version of your string ");
        } else {
            st.pop();
            sb = new StringBuilder(st.peek());
        }
    }

    /**
     * This function returns a string representing the data in this sequence
     *
     * @return a string representation of this sequence of characters
     */
    public String toString() {
        return sb.toString();
    }


}