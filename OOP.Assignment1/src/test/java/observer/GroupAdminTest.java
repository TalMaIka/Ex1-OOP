package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {

    @Test
    void register() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember Member1 = new ConcreteMember("Mem1");
        ConcreteMember Member2 = new ConcreteMember("Mem2");
        ConcreteMember Member3 = new ConcreteMember("Mem3");
        ConcreteMember Member4 = new ConcreteMember("Mem4");
        ConcreteMember Member5 = new ConcreteMember("Mem5");
        assertNull(Member1.MemberUSB);
        assertNull(Member2.MemberUSB);
        GA.register(Member1);
        GA.register(Member2);
        GA.register(Member3);
        GA.register(Member4);
        GA.register(Member5);
        GA.append("Say Yes");
        assertEquals(Member1.MemberUSB.toString(),"Say Yes");
        assertEquals(Member2.MemberUSB.toString(),Member3.MemberUSB.toString());
        assertEquals(Member2.MemberUSB,Member3.MemberUSB);
        assertEquals(Member4.MemberUSB,Member5.MemberUSB);
        assertEquals(GA.USB,Member3.MemberUSB);
        GA.insert(2,"Yes");
        GA.undo();
        assertEquals(Member2.MemberUSB.toString(),GA.USB.toString());
        assertEquals(Member5.MemberUSB.toString(),"Say Yes");
    }

    @Test
    void unregister() {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember Member1 = new ConcreteMember();
        ConcreteMember Member2 = new ConcreteMember();
        ConcreteMember Member3 = new ConcreteMember();
        ConcreteMember Member4 = new ConcreteMember();
        ConcreteMember Member5 = new ConcreteMember();
        GA.register(Member1);
        GA.register(Member2);
        GA.register(Member3);
        GA.register(Member4);
        GA.register(Member5);
        GA.insert(0,"Say Yes");
        GA.unregister(Member1);
        assertNull(Member1.MemberUSB);
        assertNotEquals(Member2.MemberUSB.toString(),null);
        GA.unregister(Member3);
        assertEquals(GA.size,3);
    }

    @Test
    void insert() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember Member1 = new ConcreteMember();
        ConcreteMember Member2 = new ConcreteMember();
        GA.register(Member1);
        GA.register(Member2);
        GA.insert(0,"Say");
        GA.insert(3," Yes");
        assertEquals(Member1.MemberUSB.toString(),Member2.MemberUSB.toString());
        assertEquals(GA.USB.toString(),"Say Yes");
        GA.undo();
        assertEquals(GA.USB.toString(),Member2.MemberUSB.toString());
    }

    @Test
    void append() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember Member1 = new ConcreteMember();
        ConcreteMember Member2 = new ConcreteMember();
        GA.register(Member1);
        GA.register(Member2);
        GA.append("Say");
        GA.append(" Yes");
        assertEquals(Member1.MemberUSB.toString(),Member2.MemberUSB.toString());
        assertEquals(GA.USB.toString(),"Say Yes");
        GA.undo();
        assertEquals(GA.USB.toString(),Member2.MemberUSB.toString());
    }

    @Test
    void delete() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember Member1 = new ConcreteMember();
        ConcreteMember Member2 = new ConcreteMember();
        GA.register(Member1);
        GA.register(Member2);
        GA.insert(0,"Say");
        GA.insert(3," Yes");
        assertEquals(Member1.MemberUSB.toString(),Member2.MemberUSB.toString());
        GA.delete(0,3);
        assertEquals(" Yes",GA.USB.toString());
        GA.undo();
        assertEquals(Member1.MemberUSB.toString(),Member2.MemberUSB.toString());
        GA.delete(0,Integer.MAX_VALUE);
        assertEquals(Member2.MemberUSB.toString(),"");
    }

    @Test
    void undo() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember Member1 = new ConcreteMember();
        ConcreteMember Member2 = new ConcreteMember();
        GA.register(Member1);
        GA.register(Member2);
        GA.append("Say");
        GA.append(" Yes");
        GA.undo();
        assertEquals(Member1.MemberUSB.toString(),Member2.MemberUSB.toString());
        assertEquals(Member2.MemberUSB.toString(),GA.USB.toString());
        assertEquals(Member2.MemberUSB.toString(),"Say");
    }
}