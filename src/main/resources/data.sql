-- drop schema flight;

-- create schema flight;

select * from flight.ticket;

select * from flight.place;

select * from flight.airline;

select * from flight.flight;

select * from flight.flight_travel_details;

select * from flight.passenger;

insert into flight.trip_type(name) values('one-way'), ('round-trip');

insert into flight.place(name) values('Coimbatore'), ('Chennai'), ('Mumbai'), ('Pune'), ('Kochi');
-- drop table flight.schedule
insert into flight.schedule(days) values('Daily'), ('Weekends'), ('Weekdays'), ('Monday'), ('tuesday'),('wed'), ('thr'), ('fri'), ('sat'),('sun');

insert into flight.role(name) values('ADMIN'), ('USER');

insert into flight.meal_type(name) values('Veg'), ('Non-Veg');

insert into flight.gender(name) values('Male'), ('Female'), ('Trans');

insert into flight.seat_Number(number) values('1B'), ('2B'), ('3B'), ('4B'), ('5B'), ('1NB'), ('2NB'), ('3NB'), ('4NB'), ('5NB');



select * from flight.ticket;

select a.name,f.number,ftd.flight_id, ftd.from_time, ftd.to_time,
ftd.estimate_journey_duration, ftd.ticket_cost
from flight.flight f
inner join flight.flight_travel_details ftd on f.id = ftd.flight_id
left join flight.airline a on a.id = f.airline_id
where ftd.from_place_id =2 and ftd.to_place_id = 3 and ftd.trip_type_id=1
and t.schedule_id in ();

