package e3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClockTest {
        @BeforeEach
        void setUp() {

        }

        @Test
        void stringConstructors() {
            Clock c = new Clock("13:18:05");
            assertEquals( 1, c.getHours12());
            assertEquals(13, c.getHours24());
            assertEquals(18, c.getMinutes());
            assertEquals( 5, c.getSeconds());
            assertEquals(Clock.Period.PM, c.getPeriod());

            c = new Clock("07:18:05");
            assertEquals( 7, c.getHours12());
            assertEquals(7, c.getHours24());
            assertEquals(18, c.getMinutes());
            assertEquals( 5, c.getSeconds());
            assertEquals(Clock.Period.AM, c.getPeriod());

            c = new Clock("05:30:00 PM");
            assertEquals( 5, c.getHours12());
            assertEquals(17, c.getHours24());
            assertEquals(30, c.getMinutes());
            assertEquals( 0, c.getSeconds());
            assertEquals(Clock.Period.PM, c.getPeriod());

            c = new Clock("12:00:00 AM");
            assertEquals(12, c.getHours12());
            assertEquals( 0, c.getHours24());
            assertEquals( 0, c.getMinutes());
            assertEquals( 0, c.getSeconds());
            assertEquals(Clock.Period.AM, c.getPeriod());

            assertThrows(IllegalArgumentException.class, () -> new Clock("Hola Mundo"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("AB:CD:EF"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("28:15:40"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("12:00:00 FM"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("13:00:00 AM"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("13:00:00 AM"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("14:00:00 PM"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("00:-02:00 AM"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("24:02:00 PM"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("12:0:01 PM"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("28:70:40"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("28:15:61"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("-12:0:01 AM"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("-12:0:01 PM"));
            assertThrows(IllegalArgumentException.class, () -> new Clock("-22:15:59"));
        }

        @Test
        void integerConstructors() {
            Clock c = new Clock(17, 25, 32);
            assertEquals( 5, c.getHours12());
            assertEquals(17, c.getHours24());
            assertEquals(25, c.getMinutes());
            assertEquals(32, c.getSeconds());
            assertEquals(Clock.Period.PM, c.getPeriod());

            c = new Clock(11, 30, 45, Clock.Period.PM);
            assertEquals(11, c.getHours12());
            assertEquals(23, c.getHours24());
            assertEquals(30, c.getMinutes());
            assertEquals(45, c.getSeconds());
            assertEquals(Clock.Period.PM, c.getPeriod());

            c = new Clock(12, 0, 0, Clock.Period.AM);
            assertEquals(12, c.getHours12());
            assertEquals( 0, c.getHours24());
            assertEquals( 0, c.getMinutes());
            assertEquals( 0, c.getSeconds());
            assertEquals(Clock.Period.AM, c.getPeriod());

            assertThrows(IllegalArgumentException.class, () -> new Clock(25, 0, 0));
            assertThrows(IllegalArgumentException.class, () -> new Clock(6,-5,8));
            assertThrows(IllegalArgumentException.class, () -> new Clock(-6,5,8));
            assertThrows(IllegalArgumentException.class, () -> new Clock(6,5,-8));
            assertThrows(IllegalArgumentException.class, () -> new Clock(24,5,8));
            assertThrows(IllegalArgumentException.class, () -> new Clock(6,70,8));
            assertThrows(IllegalArgumentException.class, () -> new Clock(6,5,61));
            assertThrows(IllegalArgumentException.class, () -> new Clock(13,5,50,Clock.Period.PM));
            assertThrows(IllegalArgumentException.class, () -> new Clock(-11,5,50,Clock.Period.AM));
            assertThrows(IllegalArgumentException.class, () -> new Clock(11,-5,50,Clock.Period.AM));
            assertThrows(IllegalArgumentException.class, () -> new Clock(11,5,68,Clock.Period.AM));

        }

        @Test
        void printHour24() {
            Clock c = new Clock(18, 45, 32);
            assertEquals("18:45:32", c.printHour24());

            c = new Clock(9, 45, 32);
            assertEquals("09:45:32", c.printHour24());


            c = new Clock(10, 30, 45, Clock.Period.PM);
            assertEquals("22:30:45", c.printHour24());

            c = new Clock(12, 0, 0, Clock.Period.AM);
            assertEquals("00:00:00", c.printHour24());
        }

        @Test
        void printHour12() {
            Clock c = new Clock(18, 45, 32);
            assertEquals("06:45:32 PM", c.printHour12());

            c = new Clock(10, 30, 45, Clock.Period.PM);
            assertEquals("10:30:45 PM", c.printHour12());

            c = new Clock(12, 0, 0, Clock.Period.AM);
            assertEquals("12:00:00 AM", c.printHour12());
        }

        @Test
        void testEquals() {
            Clock c1 = new Clock(17, 30, 30);
            Clock c2 = new Clock( 5, 30, 30, Clock.Period.PM);
            Clock c3 = new Clock( 5 ,30, 30, Clock.Period.AM);
            Clock c4 = new Clock( 5 ,20, 30);
            Clock c5 = new Clock( 5 ,30, 31,Clock.Period.AM);
            StringBuilder t=new StringBuilder();
            assertEquals(c3, c3);

            assertEquals(c2, c1);
            assertEquals(c1, c2);

            assertNotEquals(c3, c1);
            assertNotEquals(c3, c2);
            assertNotEquals(c4, t);
            assertNotEquals(c4, c3);
            assertNotEquals(c5, c3);

            assertNotEquals(c1, null);
            assertNotEquals(new Object(), c1);
        }

        @Test
        void testHashCode() {
            Clock c1 = new Clock(17, 30, 30);
            Clock c2 = new Clock( 5, 30, 30, Clock.Period.PM);
            Clock c3 = new Clock( 5 ,30, 30, Clock.Period.AM);

            assertEquals(c2.hashCode(), c1.hashCode());
            assertTrue(c1.hashCode() != c3.hashCode());
        }
    }

