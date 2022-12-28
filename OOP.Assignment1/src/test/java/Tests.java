import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests extends GroupAdminTest {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }
}

class GroupAdminTest {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    @Test
    void register() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        logger.info(()->JvmUtilities.objectTotalSize(GA));
        logger.info(()->JvmUtilities.objectFootprint(GA));
        ConcreteMember Member1 = new ConcreteMember("Mem1");
        ConcreteMember Member2 = new ConcreteMember("Mem2");
        ConcreteMember Member3 = new ConcreteMember("Mem3");
        ConcreteMember Member4 = new ConcreteMember("Mem4");
        ConcreteMember Member5 = new ConcreteMember("Mem5");
        assertNull(Member1.getMemberUSB());
        assertNull(Member2.getMemberUSB());
        GA.register(Member1);
        GA.register(Member2);
        GA.register(Member3);
        GA.register(Member4);
        GA.register(Member5);
        logger.info(()->JvmUtilities.objectFootprint(GA));
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        GA.append("Say Yes");
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        assertEquals(Member1.getMemberUSB().toString(), "Say Yes");
        assertEquals(Member2.getMemberUSB().toString(), Member3.getMemberUSB().toString());
        assertEquals(Member2.getMemberUSB(), Member3.getMemberUSB());
        assertEquals(Member4.getMemberUSB(), Member5.getMemberUSB());
        assertEquals(GA.getUSB(), Member3.getMemberUSB());
        GA.insert(2, "Yes");
        GA.undo();
        assertEquals(Member2.getMemberUSB().toString(), GA.getUSB().toString());
        assertEquals(Member5.getMemberUSB().toString(), "Say Yes");
        logger.info(()->JvmUtilities.objectTotalSize(GA));
    }

    @Test
    void unregister() {
        GroupAdmin GA = new GroupAdmin();
        logger.info(()->JvmUtilities.objectTotalSize(GA));
        ConcreteMember Member1 = new ConcreteMember();
        ConcreteMember Member2 = new ConcreteMember();
        ConcreteMember Member3 = new ConcreteMember();
        ConcreteMember Member4 = new ConcreteMember();
        ConcreteMember Member5 = new ConcreteMember();
        logger.info(()->JvmUtilities.objectFootprint(GA));
        GA.register(Member1);
        GA.register(Member2);
        GA.register(Member3);
        GA.register(Member4);
        GA.register(Member5);
        logger.info(()->JvmUtilities.objectFootprint(GA));
        GA.insert(0, "Say Yes");
        GA.unregister(Member1);
        logger.info(()->JvmUtilities.objectFootprint(GA));
        assertNull(Member1.getMemberUSB());
        assertNotEquals(Member2.getMemberUSB().toString(), null);
        GA.unregister(Member3);
        logger.info(()->JvmUtilities.objectFootprint(GA));
        assertEquals(GA.size, 3);
        logger.info(()->JvmUtilities.objectTotalSize(GA));
    }

    @Test
    void insert() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        logger.info(()->JvmUtilities.objectTotalSize(GA.getUSB()));
        ConcreteMember Member1 = new ConcreteMember();
        ConcreteMember Member2 = new ConcreteMember();
        GA.register(Member1);
        GA.register(Member2);
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        GA.insert(0, "Say");
        GA.insert(3, " Yes");
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        assertEquals(Member1.getMemberUSB().toString(), Member2.getMemberUSB().toString());
        assertEquals(GA.getUSB().toString(), "Say Yes");
        GA.undo();
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        assertEquals(GA.getUSB().toString(), Member2.getMemberUSB().toString());
        logger.info(()->JvmUtilities.objectTotalSize(GA.getUSB()));
    }

    @Test
    void append() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        logger.info(()->JvmUtilities.objectTotalSize(GA.getUSB()));
        ConcreteMember Member1 = new ConcreteMember();
        ConcreteMember Member2 = new ConcreteMember();
        GA.register(Member1);
        GA.register(Member2);
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        GA.append("Say");
        GA.append(" Yes");
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        assertEquals(Member1.getMemberUSB().toString(), Member2.getMemberUSB().toString());
        assertEquals(GA.getUSB().toString(), "Say Yes");
        GA.undo();
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        assertEquals(GA.getUSB().toString(), Member2.getMemberUSB().toString());
        logger.info(()->JvmUtilities.objectTotalSize(GA.getUSB()));
    }

    @Test
    void delete() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        logger.info(()->JvmUtilities.objectTotalSize(GA.getUSB()));
        ConcreteMember Member1 = new ConcreteMember();
        ConcreteMember Member2 = new ConcreteMember();
        GA.register(Member1);
        GA.register(Member2);
        GA.insert(0, "Say");
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        GA.insert(3, " Yes");
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        assertEquals(Member1.getMemberUSB().toString(), Member2.getMemberUSB().toString());
        GA.delete(0, 3);
        assertEquals(" Yes", GA.getUSB().toString());
        GA.undo();
        logger.info(()->JvmUtilities.objectFootprint(GA.getUSB()));
        assertEquals(Member1.getMemberUSB().toString(), Member2.getMemberUSB().toString());
        GA.delete(0, Integer.MAX_VALUE);
        assertEquals(Member2.getMemberUSB().toString(), "");
        logger.info(()->JvmUtilities.objectTotalSize(GA.getUSB()));
    }

    @Test
    void undo() throws Exception {
        GroupAdmin GA = new GroupAdmin();
        logger.info(()->JvmUtilities.objectTotalSize(GA.getUSB()));
        ConcreteMember Member1 = new ConcreteMember();
        ConcreteMember Member2 = new ConcreteMember();
        GA.register(Member1);
        GA.register(Member2);
        GA.append("Say");
        GA.append(" Yes");
        GA.undo();
        assertEquals(Member1.getMemberUSB().toString(), Member2.getMemberUSB().toString());
        assertEquals(Member2.getMemberUSB().toString(), GA.getUSB().toString());
        assertEquals(Member2.getMemberUSB().toString(), "Say");
        logger.info(()->JvmUtilities.objectTotalSize(GA));
    }

}
    class ConcreteMemberTest extends Tests {

        @Test
        void update() throws Exception {
            GroupAdmin GA = new GroupAdmin();
            ConcreteMember Member1 = new ConcreteMember("Mem1");
            ConcreteMember Member2 = new ConcreteMember("Mem2");
            ConcreteMember Member4 = new ConcreteMember("Mem4");
            ConcreteMember Member5 = new ConcreteMember("Mem5");
            logger.info(()->JvmUtilities.objectTotalSize(Member1));
            logger.info(()->JvmUtilities.objectTotalSize(Member2));
            logger.info(()->JvmUtilities.objectTotalSize(Member5));
            GA.register(Member1);
            GA.register(Member2);
            GA.register(Member4);
            GA.register(Member5);
            assertEquals(Member1.getMemberUSB(), Member2.getMemberUSB());
            GA.insert(0, "Say");
            GA.append(" Yes");
            logger.info(()->JvmUtilities.objectTotalSize(Member1));
            logger.info(()->JvmUtilities.objectTotalSize(Member2));
            logger.info(()->JvmUtilities.objectTotalSize(Member5));
            assertEquals(Member4.getMemberUSB().toString(), "Say Yes");
            GA.unregister(Member4);
            Member4.update(GA.getUSB());
            assertEquals(Member4.getMemberUSB().toString(), "Say Yes");
            UndoableStringBuilder USBtemp = new UndoableStringBuilder();
            USBtemp.append("Yes");
            logger.info(()->JvmUtilities.objectTotalSize(Member1));
            logger.info(()->JvmUtilities.objectTotalSize(Member2));
            logger.info(()->JvmUtilities.objectTotalSize(Member5));
            Member1.update(USBtemp);
            assertEquals(Member1.getMemberUSB().toString(), USBtemp.toString());
            GA.notifyMembers();
            assertNotEquals(Member1.getMemberUSB().toString(), USBtemp.toString());
            assertEquals(Member1.getMemberUSB().toString(), "Say Yes");
        }
    }
