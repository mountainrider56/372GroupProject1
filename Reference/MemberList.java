
/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
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
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The collection class for Member objects
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class MemberList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List members = new LinkedList();
    private static MemberList memberList;

    /*
     * Private constructor for singleton pattern
     * 
     */
    private MemberList() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static MemberList instance() {
        if (memberList == null) {
            return (memberList = new MemberList());
        } else {
            return memberList;
        }
    }

    /**
     * Checks whether a member with a given member id exists.
     * 
     * @param memberId
     *            the id of the member
     * @return true iff member exists
     * 
     */
    public Member search(String memberId) {
        for (Iterator iterator = members.iterator(); iterator.hasNext();) {
            Member member = (Member) iterator.next();
            if (member.getId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    /**
     * Inserts a member into the collection
     * 
     * @param member
     *            the member to be inserted
     * @return true iff the member could be inserted. Currently always true
     */
    public boolean insertMember(Member member) {
        members.add(member);
        return true;
    }

    /**
     * String form of the collection
     * 
     */
    @Override
    public String toString() {
        return members.toString();
    }
}