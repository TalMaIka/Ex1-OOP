package observer;

import java.util.ArrayList;
/**
 * This class is the direct continue to the last project 'UndoableStringBuilder'.
 * Following the DP "Observer" we can compare 'GroupAdmin' to -> 'Observer' who
 * creates updates to the 'ConcreteMember' -> 'Observable'.
 *
 * @author Tal.M / Yann.C
 * @version 1.0 20.12.2022
 */
public class GroupAdmin implements Sender{
    /**
     * The two main object that integrated in every 'GroupAdmin' created and constructor.
     */
    private ArrayList<Member> MemberList;
    public int size;

    public UndoableStringBuilder getUSB() {
        return USB;
    }

    private UndoableStringBuilder USB;

    public GroupAdmin(){
       this.MemberList = new ArrayList<>();
       this.USB = new UndoableStringBuilder();
    }
    /**
     * Register method used for registering ConcreteMembers to the GroupAdmins.
     * to be followed with the updates that occur under that exact 'GroupAdmin'.
     */
    @Override
    public void register(Member obj) {
        if(obj==null){
            throw new NullPointerException();
        }
        MemberList.add(obj);
        size = MemberList.size();
    }
    /**
     * unRegister method used for unregistering ConcreteMembers to the GroupAdmins.
     * (When unRegistering an 'ConcreteMember' from 'GroupAdmin', this exact member's USB will turn null.)
     */

    @Override
    public void unregister(Member obj) {
        if(obj==null){
            throw new NullPointerException();
        }
        MemberList.remove(obj);
        obj.update(null);
        size = MemberList.size();
    }

    /**
     * This function inserts the specified string into this character sequence,
     * Right after the insertion 'notifyMembers()' notifies all the members intact.
     *
     * @param offset - the offset to insert the specified string
     * @param obj    - the specified String obj
     */
    @Override
    public void insert(int offset, String obj) {
        USB.insert(offset,obj);
        notifyMembers();
    }
    /**
     * This function appends the specified string to this character sequence
     * Right after the appending 'notifyMembers()' notifies all the members intact.
     *
     * @param obj - the specified String obj
     */
    @Override
    public void append(String obj) {
        USB.append(obj);
        notifyMembers();
    }

    /**
     * This function removes the characters in a substring sequence
     * the sequence begin at start to end-1
     * if the start equal to end no change are made
     * Right after the deletion 'notifyMembers()' notifies all the members intact.
     *
     * @param start - the index of the beginning
     * @param end   - the index of the ending
     */
    @Override
    public void delete(int start, int end) {
        USB.delete(start,end);
        notifyMembers();
    }

    /**
     * This function erases the last change done to the object,
     * reverting it to an older state
     * Throw an exception if my stack is empty
     * Right after undoing, 'notifyMembers()' notifies all the members intact.
     *
     */
    @Override
    public void undo() throws Exception {
        USB.undo();
        notifyMembers();
    }

    /**
     * This function notifies all the members intact on the changes that's been done.
     */
    public void notifyMembers(){
        for (Member m:MemberList) {
            m.update(USB);
        }
    }

}
