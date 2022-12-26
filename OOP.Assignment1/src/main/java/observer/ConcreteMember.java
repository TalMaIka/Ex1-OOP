package observer;
/**
 * This class is the direct continue to the last project 'UndoableStringBuilder'.
 * The class 'ConcreteMember' implements 'Member' interface and used for defining user
 * to be the ones that will be notified in every change of the Group they are registered to.
 * @author Tal.M / Yann.C
 * @version 1.0 20.12.2022
 */


/**
 * The two main fields that integrated in every 'ConcreteMember' made.
 */
public class ConcreteMember implements Member{
        private String name;
        UndoableStringBuilder MemberUSB;
    /**
     * Two constructors for every 'ConcreteMember'.
     */
    public ConcreteMember(String name) {
        this.name = name;
        this.MemberUSB = null;
    }
    public ConcreteMember() {
        this.MemberUSB = null;
    }
    /**
     * The main method of 'ConcreteMember', update(), updates 'UndoableStringBuilder' object
     * to every 'ConcreteMember' registered to the same 'GroupAdmin'.
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.MemberUSB = usb;
    }
}
