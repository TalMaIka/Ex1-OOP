package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {

    @Test
    void update() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember Member1 = new ConcreteMember("Mem1");
        ConcreteMember Member2 = new ConcreteMember("Mem2");
        ConcreteMember Member4 = new ConcreteMember("Mem4");
        ConcreteMember Member5 = new ConcreteMember("Mem5");
        GA.register(Member1);
        GA.register(Member2);
        GA.register(Member4);
        GA.register(Member5);
        assertEquals(Member1.MemberUSB,Member2.MemberUSB);
        GA.insert(0,"Say");
        GA.append(" Yes");
        assertEquals(Member4.MemberUSB.toString(),"Say Yes");
        GA.unregister(Member4);
        Member4.update(GA.USB);
        assertEquals(Member4.MemberUSB.toString(),"Say Yes");
        UndoableStringBuilder USBtemp = new UndoableStringBuilder();
        USBtemp.append("Yes");
        Member1.update(USBtemp);
        assertEquals(Member1.MemberUSB.toString(),USBtemp.toString());
        GA.notifyMembers();
        assertNotEquals(Member1.MemberUSB.toString(),USBtemp.toString());
        assertEquals(Member1.MemberUSB.toString(),"Say Yes");

    }
}