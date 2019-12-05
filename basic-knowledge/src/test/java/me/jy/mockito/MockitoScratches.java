package me.jy.mockito;
// tag::demo[]

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.exceptions.verification.VerificationInOrderFailure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author jy
 */
@SuppressWarnings("unchecked")
public class MockitoScratches {

    @Mock
    private List<String> mockedList;

    @BeforeEach
    public void initMock() {
        mockedList = mock(List.class); // option 1
//        MockitoAnnotations.initMocks(this); // option 2 with @Mock
    }

    @Test
    public void testStub() {

        mockedList.add("one");
        mockedList.clear();

        // Once created, a mock will remember all interactions.
        verify(mockedList).add("one"); // <1>
        verify(mockedList).clear();

        when(mockedList.get(0)).thenReturn("w"); // stub
        when(mockedList.get(1)).thenThrow(RuntimeException.class);

        assertEquals("w", mockedList.get(0));
//        mockedList.get(1); // A RuntimeException occurs

        // By default, for all methods that return a value, a mock will return either null, a primitive value, or an empty collection
        assertNull(mockedList.get(333)); // <2>

        when(mockedList.get(anyInt())).thenReturn("a");
        // last one wins
        when(mockedList.get(anyInt())).thenReturn("b");
        assertEquals("b", mockedList.get(1)); // <3>

    }

    @Test
    public void testArgumentMatcher() {

        // ArgumentMatcher used when stubbing
        when(mockedList.get(anyInt())).thenReturn("element"); // <1>
        assertEquals("element", mockedList.get(223));

        // ArgumentMatcher used when verifying behaviour
        verify(mockedList).get(anyInt()); // <2>

        mockedList.add(1, "example");
        // Multiple ArgumentMatcher used when verifying behaviour
        verify(mockedList).add(anyInt(), anyString()); // <3>

    }

    @Test
    public void testVerifyInvocationTimes() {

        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        // following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once"); // <1>
        verify(mockedList, times(1)).add("once");

        // exact number of invocations verification
        verify(mockedList, times(2)).add("twice"); // <2>
        verify(mockedList, times(3)).add("three times");

        // verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened"); // <3>
        verify(mockedList, times(0)).add("never happened");

        // verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times"); // <4>
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");

        List secondMockedList = mock(List.class);
        // make sure the mocked obj never used
        verifyNoInteractions(secondMockedList); // <5>
    }

    @Test
    public void testStubException() {

        // Stub void method
        doNothing().when(mockedList).clear();
        mockedList.clear();

        doReturn("a").when(mockedList).get(anyInt());
        assertEquals("a", mockedList.get(-1));

        assertThrows(RuntimeException.class, () -> {
            // Stub void method with exceptions
            doThrow(RuntimeException.class).when(mockedList).clear(); // <1>
            mockedList.clear();
        });

    }

    @Test
    public void testIsInOrder() {

        mockedList.add("a");
        mockedList.add("b");

        InOrder inOrder = inOrder(mockedList);

        assertThrows(VerificationInOrderFailure.class, () -> {
            // verify the order of invocation
            inOrder.verify(mockedList).add("b"); // <1>
            inOrder.verify(mockedList).add("a");
        });
    }

    @Test
    public void testStubConsecutiveCall() {

        // Stub consecutive call
        when(mockedList.get(anyInt()))
            .thenReturn("a", "b")
            .thenThrow(RuntimeException.class); // <1>

        assertEquals("a", mockedList.get(1));
        assertEquals("b", mockedList.get(1));
        assertThrows(RuntimeException.class, () -> {
            mockedList.get(1); // Exception occurs
        });
    }

    @Test
    public void testStubWithAnswer() {

        // Stub with callback
        when(mockedList.get(anyInt()))
            .thenAnswer(invocation -> "I am groot! " + Arrays.toString(invocation.getArguments())); // <1>

        assertEquals("I am groot! [1]", mockedList.get(1));
    }

    @Test
    public void testSpyOnRealObject() {
        List originList = new LinkedList();
        List list = spy(originList);

        doReturn(100).when(list).get(0);
//        when(list.get(0)).thenReturn(100); // trouble

        assertEquals(100, list.get(0));
    }

    @Test
    public void testStubDefaultReturnValue() {
        List mockedList = mock(List.class, RETURNS_DEFAULTS);

        assertNull(mockedList.get(1));

        mockedList.add(1);

        // still return 0 even if elements added in the list
        assertEquals(0, mockedList.size());

    }

    @Test
    public void testCaptureArguments() {
        ArgumentCaptor<List> listArgumentCaptor = ArgumentCaptor.forClass(List.class);

//        verify(mockedList).get(listArgumentCaptor.capture());
    }

    @Test
    public void testPartialMock() {

    }


}

// end::demo[]
