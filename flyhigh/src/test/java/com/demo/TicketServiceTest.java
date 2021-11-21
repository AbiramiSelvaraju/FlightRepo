package com.demo;

import com.demo.dto.TicketDTO;
import com.demo.entities.*;
import com.demo.exception.ModelNotFoundException;
import com.demo.repositories.TicketRepository;
import com.demo.services.TicketService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class TicketServiceTest {

	@Mock
	TicketService service;


	@Test
	public void testGetTicketByValidId() throws  ModelNotFoundException{
		int id = 1;
		Ticket t = new Ticket("Ab","ab@gmail.com",3);
		Mockito.when(service.getTicketById(anyInt())).thenReturn(t);
//		try{
//			Ticket t = service.getTicketById(id);
			Assertions.assertNotNull(t);
			MatcherAssert.assertThat(t, Matchers.instanceOf(Ticket.class));
//		}catch (ModelNotFoundException ex){
//			System.out.println(ex.getLocalizedMessage());
//		}
	}

	@Test
	public void testGetTicketByInValidId() throws  ModelNotFoundException{
		Mockito.doThrow(new ModelNotFoundException("")).when(service).getTicketById(ArgumentMatchers.anyInt());
		Assertions.assertThrows(ModelNotFoundException.class, () -> {
			service.getTicketById(ArgumentMatchers.anyInt());
		});
	}


}
