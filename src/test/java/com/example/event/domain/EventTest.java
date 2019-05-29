package com.example.event.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest {

	@Test
	public void testGetEventId() {
		Event e = new Event();
		e.setEventId(100);
		assertEquals((Integer)100, e.getEventId());
	}

}
